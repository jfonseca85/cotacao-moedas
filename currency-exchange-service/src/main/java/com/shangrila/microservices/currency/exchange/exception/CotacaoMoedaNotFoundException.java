package com.shangrila.microservices.currency.exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CotacaoMoedaNotFoundException extends RuntimeException {
	public CotacaoMoedaNotFoundException(String message) {
		super(message);
	}
}
