package com.shangrila.microservices.currency.exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CotacaoMoedaBadRequestException extends RuntimeException {
	public CotacaoMoedaBadRequestException(String message) {
		super(message);
	}
}
