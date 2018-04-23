package com.shangrila.microservices.currency.exchange.util;

import java.math.BigDecimal;

import com.shangrila.microservices.currency.exchange.exception.CotacaoMoedaInternalServerErrorException;

public class BigDecimalUtil {
	public static BigDecimal parseString(String string) {
		try {
			return new BigDecimal(string.replace(",", "."));
		} catch (NullPointerException | NumberFormatException e) {
			 throw new CotacaoMoedaInternalServerErrorException(" Internal Server Error");
		}
	}
}
