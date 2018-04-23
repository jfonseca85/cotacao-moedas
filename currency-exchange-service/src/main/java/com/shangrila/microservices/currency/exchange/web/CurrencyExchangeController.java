package com.shangrila.microservices.currency.exchange.web;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shangrila.microservices.currency.exchange.exception.CotacaoMoedaBadRequestException;
import com.shangrila.microservices.currency.exchange.exception.CotacaoMoedaNotFoundException;
import com.shangrila.microservices.currency.exchange.model.CotacaoMoeda;
import com.shangrila.microservices.currency.exchange.model.CotacaoMoedaId;
import com.shangrila.microservices.currency.exchange.model.CurrencyConversionBean;
import com.shangrila.microservices.currency.exchange.model.ExchangeValue;
import com.shangrila.microservices.currency.exchange.repository.CotacaoRepository;
import com.shangrila.microservices.currency.exchange.util.BigDecimalUtil;
import com.shangrila.microservices.currency.exchange.util.Constantes;
import com.shangrila.microservices.currency.exchange.util.LocalDateUtil;

@RestController
public class CurrencyExchangeController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;

	// @Autowired
	// private CurrencyExchangeServiceProxy proxy;

	@Autowired
	private CotacaoRepository repository;


	public ExchangeValue retrieveExchangeValue(String from,  String to, String quotation) {
		log.info("Chamando retrieveExchangeValue");
		CotacaoMoedaId IdFrom = new CotacaoMoedaId(from, quotation);
		CotacaoMoedaId IdTo = new CotacaoMoedaId(to, quotation);

		Optional<CotacaoMoeda> findByIdFrom = repository.findById(IdFrom);
		Optional<CotacaoMoeda> findByIdTo = repository.findById(IdTo);

		if (!(findByIdFrom.isPresent() | findByIdTo.isPresent()))
			throw new CotacaoMoedaNotFoundException("Not Found");

		BigDecimal ValueBigDecimalFrom = BigDecimalUtil.parseString(findByIdFrom.get().getTaxaCompra());
		BigDecimal ValueBigDecimalTo = BigDecimalUtil.parseString(findByIdTo.get().getTaxaCompra());

		BigDecimal conversionMultiple = ValueBigDecimalFrom.divide(ValueBigDecimalTo, 5, RoundingMode.HALF_UP);

		ExchangeValue exchangeValue = new ExchangeValue(null, from, to, conversionMultiple);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		return exchangeValue;
	}

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	@HystrixCommand(fallbackMethod = "fallbackConvertCurrencyFeign")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity, @RequestParam(value = "quotation") String quotation) {

		if(quantity.doubleValue() < 0) throw new CotacaoMoedaBadRequestException("A quantidade passado e negativa >> quantity: " + quantity);
		
		// convert String to LocalDate
		LocalDate localDate = LocalDateUtil.parseStringForLocalDate(quotation, Constantes.DATE_FORMATTER);
		int diasSubtrair = LocalDateUtil.finalSemana(localDate);
		LocalDate dateBussines = localDate.minusDays(diasSubtrair);
		String stringDateBussines = LocalDateUtil.parseLocalDateForString(dateBussines, Constantes.DATE_FORMATTER);

		ExchangeValue response = retrieveExchangeValue(from, to, stringDateBussines);

		log.info("{}", response);

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()).setScale(2, RoundingMode.HALF_UP),
				response.getPort(), stringDateBussines);
	}

	//// #tag::CallBackHystrix
	public CurrencyConversionBean fallbackConvertCurrencyFeign(String from, String to, BigDecimal quantity,
			String quotation) {

		log.warn("Cotacao nao encontrada no repositorio Local");
		// convert String to LocalDate
		LocalDate localDate = LocalDateUtil.parseStringForLocalDate(quotation, Constantes.DATE_FORMATTER);

		int diasSubtrair = LocalDateUtil.finalSemana(localDate);

		LocalDate dateBussines = localDate.minusDays(diasSubtrair);
		String stringDateBussines = LocalDateUtil.parseLocalDateForString(dateBussines, Constantes.DATE_FORMATTER);

		// RestTemplate restTemplate = new RestTemplate();
		String stringDateBussinesUpdate = LocalDateUtil.parseLocalDateForString(dateBussines,
				Constantes.DATE_FORMATTER_WITHOUT_SLASH);
		
		// String url =
		// Constantes.BASE_URL_DATA_SOURCE_COTACAO.replace("{stringDateBussinesUpdate}",
		// stringDateBussinesUpdate);
		// String responseUpdate = restTemplate.getForObject(url, String.class);
		// log.debug(responseUpdate);
		log.info("Atualizando o repositorio de Cotacao de Moedas");

		// Feign - Problem 1
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("quotation", stringDateBussinesUpdate);

		ResponseEntity<String> responseEntity = new RestTemplate()
				.getForEntity("http://localhost:8200/valueSet/batchUpload/{quotation}", String.class, uriVariables);

		// proxy.retrieveExchangeValue(stringDateBussinesUpdate);

		ExchangeValue response = retrieveExchangeValue(from, to, stringDateBussines);

		log.info("{}", response);

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()).setScale(2, RoundingMode.HALF_UP),
				response.getPort(), stringDateBussines);
	}

}
