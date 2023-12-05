package com.example.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.model.LocalUser;

import jakarta.annotation.PostConstruct;

@Service
public class JWTService {

	@Value("${jwt.algorithm.key}")
	private String algorithmKey;
	@Value("${jwt.issuer}")
	private String issuer;
	@Value("${jwt.expiryInSeconds}")
	private int expiryInSecounds;
	private Algorithm algorithm;
	private static final String USERNAME_KEY="USERNAME";
	private static final String EMAIL_KEY="EMAIL";
	@PostConstruct
	public void postContruct() {
		algorithm=Algorithm.HMAC256(algorithmKey);
	}
	
	public String generateJWT(LocalUser localUser) {
		return JWT.create()
				.withClaim(USERNAME_KEY, localUser.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+(1000*expiryInSecounds)))
				.withIssuer(issuer).sign(algorithm);
	}
	
	public String generateVerification(LocalUser user) {
		return JWT.create()
				.withClaim(EMAIL_KEY, user.getEmail())
				.withExpiresAt(new Date(System.currentTimeMillis()+(1000*expiryInSecounds)))
				.withIssuer(issuer).sign(algorithm);
	}
	
	public String getUsername(String token) {
		return JWT.decode(token).getClaim(USERNAME_KEY).asString();
	}
}
