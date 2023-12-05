package com.example.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "veritication_token")
public class VerificationToken {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false, unique = true)
	private Long id;

	@Lob
	@Column(name="token", nullable =false)
	private String token;
	
	@Column(name="created_timestamp", nullable= false)
	private Timestamp createdTimestamp;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="local_use_id", nullable = false)
	private LocalUser localUser;
	
	public LocalUser getLocalUser() {
		return localUser;
	}

	public void setLocalUser(LocalUser localUser) {
		this.localUser = localUser;
	}
	
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
