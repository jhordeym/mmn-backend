package com.mmn.payment.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.mmn.payment.model.type.ProductParamType;

import lombok.Data;

@Entity
@Data
public class ProductParam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Product product;
	@Enumerated(EnumType.STRING)
	private ProductParamType param;
	private String value;
	
	public Boolean getBooleanValue() {
		try {
			return Boolean.valueOf(this.value);
		} catch (Exception e) {
			return false;
		}
	}
	
	public void setBooleanValue(Boolean value) {
		try {
			this.value = value.toString();
		} catch (Exception e) {
			
		}
	}
	
	public ProductParam productParam() {
		return this;
	}
	
}
