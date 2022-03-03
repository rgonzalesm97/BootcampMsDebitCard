package com.bank.debitCard.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bank.debitCard.model.dao.DebitCard;

import reactor.core.publisher.Mono;


@Repository
public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, String>{
	public Mono<DebitCard> findByIdClient(String idClient);
}
