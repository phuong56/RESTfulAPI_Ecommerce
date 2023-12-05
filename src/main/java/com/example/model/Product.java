package com.example.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable =false, unique=true)
	private Long id;
	@Column(name="name", nullable=false)
	private String name;
	@Column(name="short_description", nullable=false)
	private String shortDescription;
	@Column(name="long_description", length=1000)
	private String longDescription;
	@Column(name="price", nullable=false)
	private Double price;
	
	@OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE,optional = false)
//	@JoinColumn(name="id_inventory", nullable = true)
	Inventory inventory;
	
	@OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.REMOVE)
//	@JoinColumn(name="id_web_order_quantites", nullable = false)
	List<WebOrderQuantities> webOrderQuantites=new ArrayList<>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
