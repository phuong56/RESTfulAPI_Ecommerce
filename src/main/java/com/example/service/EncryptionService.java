package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class EncryptionService {
	@Value("${encryption.salt.rounds}")
	private int saltRounds;
	private String salt;
	
	@PostConstruct
	public void postConstruct() {
		salt=BCrypt.gensalt(saltRounds);
	}
	
	public String encryptPassword(String passWord) {
		return BCrypt.hashpw(passWord, salt);
	}
	public Boolean verifyPassword(String passWord, String hash) {
		return BCrypt.checkpw(passWord, hash);
	}
}
