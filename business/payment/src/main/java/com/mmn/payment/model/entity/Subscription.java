package com.mmn.payment.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Subscription {
	
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(length=36)
	private String accountId;
	@ManyToOne
	private Product product;
	private LocalDate start;
	private LocalDate current;
	private LocalDate next;
	private Integer renovationTimes;
	
	private Integer invitationLeft;
		
}
