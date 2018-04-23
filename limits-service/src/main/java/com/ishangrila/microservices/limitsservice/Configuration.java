package com.ishangrila.microservices.limitsservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
public class Configuration {
	
	private int minimum;
	private int maximum;
	
	private int diasDeCache;

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public int getDiasDeCache() {
		return diasDeCache;
	}

	public void setDiasDeCache(int diasDeCache) {
		this.diasDeCache = diasDeCache;
	}

}
