package com.example.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterBody {
	@NotNull
	@NotBlank
	@Size(min=3,max=100) // size [3,100]
	private String username;
	@NotNull
	@NotBlank
	@Size(min=3,max=100)
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$") // mau password
	private String password;
	@NotNull
	@NotBlank
	@Size(min=3,max=100)
	@Email
	private String email;
	@NotNull
	@NotBlank
	@Size(min=3,max=100)
	private String firstname;
	@NotNull
	@NotBlank
	@Size(min=3,max=100)
	private String lastname;
	@NotNull
	@NotBlank
	@Size(min=3,max=100)
	private String city;
	@NotNull
	@NotBlank
	@Size(min=3,max=100)
	private String country;
	
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
