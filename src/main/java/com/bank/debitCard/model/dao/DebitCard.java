package com.bank.debitCard.model.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("debitCard")
public class DebitCard {

	@Id
	private String id;
	private String idClient;
	private String cardNumber;
	private String idProduct;
}
