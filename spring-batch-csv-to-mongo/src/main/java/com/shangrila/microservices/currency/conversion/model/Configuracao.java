package com.shangrila.microservices.currency.conversion.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("currency-conversion-service")
public class Configuracao {
		
	private int diasDeCache;

	public int getDiasDeCache() {
		return diasDeCache;
	}

	public void setDiasDeCache(int diasDeCache) {
		this.diasDeCache = diasDeCache;
	}

}
