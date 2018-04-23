package com.shangrila.microservices.currency.exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CotacaoMoedaInternalServerErrorException extends RuntimeException {
	public CotacaoMoedaInternalServerErrorException(String message) {
		super(message);
	}
}
