package com.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	//getConversionFactor
	@GetMapping("/currency-exchange-value/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to)
	{
      
			ExchangeValue exchangeValue =  repository.findByFromAndTo(from, to);
			
			exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			logger.info("{}", exchangeValue);
			return exchangeValue;
}
	//updateConversionFactor
	@PutMapping(path = "/update-exchange-value/from/{from}/to/{to}/conversionFactor/{conversionFactor}")
	public ExchangeValue updateExchangeValue(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal conversionFactor ) {
		ExchangeValue exchangeValue =  repository.findByFromAndTo(from, to);
		
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		exchangeValue.setConversionFactor(conversionFactor);
		logger.info("{}", exchangeValue);
		return repository.save(exchangeValue);
}

	@RequestMapping(path = "/get-all-factors", method = RequestMethod.GET)
	public List<ExchangeValue> getAllConversionFactor() {
		return repository.findAll();
	}

	@PostMapping(value = "/add_exchange_detail")
    public ResponseEntity<?> addConversionFactor(@RequestBody ExchangeValue cf) {
		;
      logger.info("Inside addConversionFactor() :: add conversion factor for a country ");
      repository.save(cf);
      return ResponseEntity.ok("Factor Saved !");
  }
	
}
