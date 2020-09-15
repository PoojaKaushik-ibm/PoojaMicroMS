package com.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//only using Feign to connect to the exchange service, mention the name n url
//@FeignClient(name="currency-exchange-service", url="localhost:8000")
// when u use ribbon, dont need to mention the url, since it will be LB'd 
//@FeignClient(name="currency-exchange-service") 

@FeignClient(name="zuul-api-gateway")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@GetMapping("/currency-exchange-service/currency-exchange-value/from/{from}/to/{to}")
		public CurrencyConversionBean retrieveExchangeValue (
				@PathVariable("from") String from, @PathVariable("to") String to);

}
