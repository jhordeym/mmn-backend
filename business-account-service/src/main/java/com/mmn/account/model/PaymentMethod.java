package com.mmn.account.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PaymentMethod {

	@Id
	private Long id;
}
