package com.shangrila.microservices.currency.exchange.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shangrila.microservices.currency.exchange.model.CotacaoMoeda;
import com.shangrila.microservices.currency.exchange.model.CotacaoMoedaId;

public interface CotacaoRepository extends MongoRepository<CotacaoMoeda, CotacaoMoedaId> {

//	List<Cotacaomoeda> findByFromAndToAndQuotation(String from, String to, String quotation);

}
