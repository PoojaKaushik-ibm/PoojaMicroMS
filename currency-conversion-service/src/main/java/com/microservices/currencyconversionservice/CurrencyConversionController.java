package com.microservices.currencyconversionservice;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//import com.microservices.currencyexchangeservice.ExchangeValue;


@RestController
public class CurrencyConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/amount/{amount}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal amount) {
		
		
		//Feign - Problem 1
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		
			ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange-value/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);
			CurrencyConversionBean response= responseEntity.getBody();
		
		
			return new CurrencyConversionBean(response.getId(),from, to, 
					response.getConversionFactor(), amount, 
					amount.multiply(response.getConversionFactor()),response.getPort());
				
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/amount/{amount}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal amount) {

		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		logger.info("response -->{}",response.toString());
		
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionFactor(), 
				amount, amount.multiply(response.getConversionFactor()), response.getPort());
	}
	
	
}
