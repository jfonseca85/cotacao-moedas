package com.shangrila.microservices.currency.exchange.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangrila.microservices.currency.exchange.model.CotacaoMoeda;
import com.shangrila.microservices.currency.exchange.model.CotacaoMoedaId;
import com.shangrila.microservices.currency.exchange.repository.CotacaoRepository;

@Service
public class CotacaoMoedaService {
	@Autowired
	private CotacaoRepository repository;
	
	public Optional<CotacaoMoeda> findId(CotacaoMoedaId cotacaoMoedaId) {
		return repository.findById(cotacaoMoedaId);
	}

}
