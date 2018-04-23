package com.shangrila.microservices.currency.conversion.config;

import org.springframework.stereotype.Component;

@Component
public class LimitConfiguration {
	private int diasDeCache;
	
	public LimitConfiguration() {
	}

	public LimitConfiguration(int diasDeCache) {
		this.diasDeCache = diasDeCache;
	}

	public int getDiasDeCache() {
		return diasDeCache;
	}

}
