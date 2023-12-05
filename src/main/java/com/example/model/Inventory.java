package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="inventory")
public class Inventory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable = false, unique = true)
	private Long id;
	@Column(name="quantities", nullable =false, unique=true)
	private int quantities;
	@OneToOne(orphanRemoval = true, optional = false)
	@JoinColumn(name="id_product", nullable=false, unique=true)
	private Product product;
}
