package com.mmn.payment.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Subscription {
	
	@Id
	private String accountId;
	@ManyToOne
	private Product product;
	private LocalDate start;
	private LocalDate current;
	private LocalDate next;
	private Integer renovationTimes;
	
	private Integer invitationLeft;
	
}
