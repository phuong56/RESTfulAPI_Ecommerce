package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="web_order_quantites")
public class WebOrderQuantities {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;
	
	@Column(name="quantity", nullable = false)
	private Integer quantity;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="product_id", nullable = false)
	private Product product;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="web_order", nullable = false)
	private WebOrder webOrder;
	

}
