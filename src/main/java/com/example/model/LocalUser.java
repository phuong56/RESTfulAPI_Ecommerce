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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name="local_user")
public class LocalUser {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false, unique = true)
    private Long id;
	@Column(name="username", nullable = false, unique=true)
    private String username;
    @Column(name="password", nullable =false, length=1000)
    private String password;
    @Column(name="email", nullable = false, length=1000, unique=true)
    private String email;
    @Column(name = "firstname", nullable =false)
    private String firstname;
    @Column(name="lastname", nullable=false)
    private String lastname;
    @Column(name="city", nullable=false, length=75)
    private String city;
    @Column(name="country", nullable = false, length=75)
    private String country;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Address> addresses=new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade =CascadeType.REMOVE, orphanRemoval = true)
    List<WebOrder> webOrder=new ArrayList<WebOrder>();
    
    @OneToMany(mappedBy = "localUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id desc")
    List<VerificationToken> verificationTokens =new ArrayList<VerificationToken>();
    
    @Column(name="email_verified", nullable=false)
    private Boolean emailVerified=false;
    
    public Boolean getEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
    
    
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public List<WebOrder> getWebOrder() {
		return webOrder;
	}
	public void setWebOrder(List<WebOrder> webOrder) {
		this.webOrder = webOrder;
	}
	public List<VerificationToken> getVerificationTokens() {
		return verificationTokens;
	}
	public void setVerificationTokens(List<VerificationToken> verificationTokens) {
		this.verificationTokens = verificationTokens;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
    


}
