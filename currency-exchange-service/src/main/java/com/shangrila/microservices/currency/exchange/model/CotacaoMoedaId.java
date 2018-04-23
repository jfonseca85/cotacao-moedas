package com.shangrila.microservices.currency.exchange.model;

import java.io.Serializable;


public class CotacaoMoedaId implements Serializable{

	private static final long serialVersionUID = 1L;
	public String siglaMoeda;
	public String data;

	public String getData() {
		return data;
	}
	public CotacaoMoedaId() {}


	public CotacaoMoedaId(String siglaMoeda, String data) {
		this.siglaMoeda = siglaMoeda;
		this.data = data;
	}

	public String getSiglaMoeda() {
		return siglaMoeda;
	}
	public void setSiglaMoeda(String siglaMoeda) {
		this.siglaMoeda = siglaMoeda;
	}
	public void setData(String data) {
		this.data = data;
	}


}
