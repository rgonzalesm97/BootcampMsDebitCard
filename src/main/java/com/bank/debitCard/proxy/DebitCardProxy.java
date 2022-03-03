package com.bank.debitCard.proxy;

import org.springframework.web.reactive.function.client.WebClient;

import com.bank.debitCard.model.dao.Account;
import com.bank.debitCard.model.dao.Client;

import reactor.core.publisher.Mono;

public class DebitCardProxy {
	
	private final WebClient.Builder webClientBuilder = WebClient.builder();
	
	//get client by id
	public Mono<Client> getClient(String idClient){
		return webClientBuilder.build()
								.get()
								.uri("http://localhost:8090/client/{idClient}", idClient)
								.retrieve()
								.bodyToMono(Client.class);
	}
	
	public Mono<Account> getAccount(String idAccount){
		return webClientBuilder.build()
								.get()
								.uri("http://localhost:8090/account/{idAccount}", idAccount)
								.retrieve()
								.bodyToMono(Account.class);
	}

}
