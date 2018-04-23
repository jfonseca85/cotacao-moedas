//package com.shangrila.microservices.currency.exchange.proxy;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.shangrila.microservices.currency.exchange.model.CurrencyConversionBean;
//
////@FeignClient(name="netflix-zuul-api-gateway-server")
////@RibbonClient(name="currency-conversion-update")
//public interface CurrencyExchangeServiceProxy {
//	@GetMapping("/currency-conversion-update/valueSet/batchUpload/{quotation}")
//	public CurrencyConversionBean retrieveExchangeValue (@PathVariable String quotation);
//}
