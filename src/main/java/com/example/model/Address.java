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
import jakarta.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique = true)
	private Long id;
	@Column(name="address_line_1", nullable = false, length=512)
	private String addressline1;
	@Column(name="address_line_2", nullable = false, length=512)
	private String addressline2;
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private LocalUser user;
	
	@OneToMany(mappedBy = "address", cascade = CascadeType.REMOVE, orphanRemoval = true)
//	@JoinColumn(name="id_webOrder", nullable = true)
	List<WebOrder> webOrder=new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	
	

}
