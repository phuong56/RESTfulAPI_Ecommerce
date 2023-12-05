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
@Table(name="web_order")
public class WebOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="local_user_id", nullable = false)
	private LocalUser user;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="address_id", nullable = false)
	private Address address;
	
	@OneToMany(mappedBy = "webOrder",orphanRemoval = true, cascade = CascadeType.REMOVE)
//	@JoinColumn(name="web_oq_id", nullable = false)
	private List<WebOrderQuantities> webOrderQuantites=new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalUser getUser() {
		return user;
	}

	public void setUser(LocalUser user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
