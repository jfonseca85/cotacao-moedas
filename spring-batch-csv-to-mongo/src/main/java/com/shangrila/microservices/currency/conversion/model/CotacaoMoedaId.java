package com.shangrila.microservices.currency.conversion.model;

import java.io.Serializable;

public class CotacaoMoedaId implements Serializable {

	private static final long serialVersionUID = 1L;
	private String siglaMoeda;
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSiglaMoeda() {
		return siglaMoeda;
	}

	public void setSiglaMoeda(String siglaMoeda) {
		this.siglaMoeda = siglaMoeda;
	}

}
