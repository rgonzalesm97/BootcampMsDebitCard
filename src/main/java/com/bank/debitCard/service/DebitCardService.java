package com.bank.debitCard.service;

import com.bank.debitCard.model.dao.DebitCard;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardService {
	public Flux<DebitCard> findAll();
	public Mono<DebitCard> findById(String id);
	public Mono<DebitCard> findByIdClient(String idClient);
	public Mono<DebitCard> save(DebitCard debitCard);
	public void delete(String id);
}