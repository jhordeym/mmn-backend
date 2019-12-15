package com.mmn.payment.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.payment.model.type.ProductParamType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(length=36)
    private String id;
    private String name;
    private String description;
    private String category;
    private String imageUrl;
    //marca se produto é um subscricao <> None
    @Enumerated(EnumType.STRING)
    private Renovation renovation = Renovation.None;
    private BigDecimal price; 
    private BigDecimal priceTC;
    @OneToMany(targetEntity = ProductParam.class, mappedBy = "product")
    private List<ProductParam> params = new ArrayList<>();
    
    //usado para ser mapeado
    public ProductParam param(ProductParamType type) {
    	return params.stream().collect(
    			Collectors.toMap(ProductParam::getParam, ProductParam::productParam)
    			).get(type);
    }
    
}
