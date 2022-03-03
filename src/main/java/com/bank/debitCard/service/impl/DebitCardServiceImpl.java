package com.bank.debitCard.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.debitCard.model.dao.Account;
import com.bank.debitCard.model.dao.DebitCard;
import com.bank.debitCard.proxy.DebitCardProxy;
import com.bank.debitCard.repository.DebitCardRepository;
import com.bank.debitCard.service.DebitCardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DebitCardServiceImpl implements DebitCardService{
	
	@Autowired
	private DebitCardRepository debitCardRepo;
	
	private DebitCardProxy debitCardProxy = new DebitCardProxy();

	@Override
	public Flux<DebitCard> findAll() {
		return debitCardRepo.findAll();
	}

	@Override
	public Mono<DebitCard> findById(String id) {
		return debitCardRepo.findById(id);
	}
	
	@Override
	public Mono<DebitCard> findByIdClient(String idClient) {
		return debitCardRepo.findByIdClient(idClient);
	}
	
	@Override
	public Mono<DebitCard> save(DebitCard debitCard) {
		return clientCheck(debitCard).flatMap(this::productExist)
									 .flatMap(debitCardRepo::save);
	}

	@Override
	public void delete(String id) {
		debitCardRepo.deleteById(id).subscribe();
	}
	
	//DEBITCARD UTIL METHODS
	public Mono<DebitCard> clientCheck(DebitCard debitCard){
		return debitCardProxy.getClient(debitCard.getIdClient())
							 .switchIfEmpty(Mono.error(()->new IllegalArgumentException("Client doesn't exist")))
							 .flatMap(resp->findByIdClient(resp.getId()).switchIfEmpty(Mono.just(new DebitCard())))
							 .flatMap(resp->{
								 return resp.getId()==null ? Mono.just(debitCard)
										 				   : Mono.error(()->new IllegalArgumentException("Client can have only one debit card"));
							 });
	}
	
	public Mono<DebitCard> productExist(DebitCard debitCard){
		return debitCardProxy.getAccount(debitCard.getIdProduct())
							 .switchIfEmpty(Mono.just(new Account()))
							 .flatMap(resp -> {
								 return resp.getId()==null ? Mono.error(()->new IllegalArgumentException("Product associated to this debid card doesn't exist"))
										 				   : Mono.just(debitCard);
							 });
	}
	
}
