package com.shangrila.microservices.currency.conversion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document
public class CotacaoMoeda {
	
	@Id
	private CotacaoMoedaId id;
	
	private String tipo;
	
	private int codMoeda;
	
	private String taxaCompra;

	private String TaxaVenda;

	private String paridadeCompra;

	private String paridadeVenda;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTaxaCompra() {
		return taxaCompra;
	}

	public void setTaxaCompra(String taxaCompra) {
		this.taxaCompra = taxaCompra;
	}

	public String getTaxaVenda() {
		return TaxaVenda;
	}

	public void setTaxaVenda(String taxaVenda) {
		TaxaVenda = taxaVenda;
	}

	public String getParidadeCompra() {
		return paridadeCompra;
	}

	public void setParidadeCompra(String paridadeCompra) {
		this.paridadeCompra = paridadeCompra;
	}

	public String getParidadeVenda() {
		return paridadeVenda;
	}

	public void setParidadeVenda(String paridadeVenda) {
		this.paridadeVenda = paridadeVenda;
	}

	public CotacaoMoedaId getId() {
		return id;
	}

	public void setId(CotacaoMoedaId id) {
		this.id = id;
	}

	public int getCodMoeda() {
		return codMoeda;
	}

	public void setCodMoeda(int codMoeda) {
		this.codMoeda = codMoeda;
	}

}
