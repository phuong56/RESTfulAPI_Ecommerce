package com.example.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.api.model.LoginBody;
import com.example.api.model.RegisterBody;
import com.example.exception.EmailFailureException;
import com.example.exception.UserNotVerifiedException;
import com.example.model.LocalUser;
import com.example.model.VerificationToken;
import com.example.repository.LocalUserRepository;
import com.example.repository.VerificationTokenRepository;

import jakarta.transaction.Transactional;

@Service
public class MainService {
	
	@Autowired
	VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	LocalUserRepository repository;
	@Autowired
	private EncryptionService encryptionService;
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private EmailService emailService;
	
	public VerificationToken createVerificationToken(LocalUser user) {
		VerificationToken verificationToken= new VerificationToken();
		verificationToken.setToken(jwtService.generateVerification(user));
		verificationToken.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		verificationToken.setLocalUser(user);
		user.getVerificationTokens().add(verificationToken);
		return verificationToken;
	}



	public LocalUser registerUser(RegisterBody registerBody) {
		LocalUser localUser=new LocalUser();
		localUser.setUsername(registerBody.getUsername());
		localUser.setPassword(encryptionService.encryptPassword(registerBody.getPassword()));
		localUser.setFirstname(registerBody.getFirstname());
		localUser.setLastname(registerBody.getLastname());
		localUser.setEmail(registerBody.getEmail());
		localUser.setCity(registerBody.getCity());
		localUser.setCountry(registerBody.getCountry());
		VerificationToken verificationToken =new VerificationToken();
		verificationToken.setLocalUser(localUser);
		emailService.sendVerificationEmail(verificationToken);
		verificationTokenRepository.save(verificationToken);
		repository.save(localUser);
		return localUser;
	}
	
	
	
	public void SaveLocalUser(LocalUser localUser) {
		repository.save(localUser);
	}
	
	public String loginUser(LoginBody loginBody) throws UserNotVerifiedException, EmailFailureException  {
		Optional<LocalUser>opUser=repository.findByUsername(loginBody.getUsername());
		if(opUser.isPresent()) {
			LocalUser localUser= opUser.get();
			if(encryptionService.verifyPassword(loginBody.getPassword(), localUser.getPassword())) {
				if(localUser.getEmailVerified())
					return jwtService.generateJWT(localUser);
				else {
					List<VerificationToken> verificationTokens =localUser.getVerificationTokens();
					boolean resend =(verificationTokens.size()==0||
							verificationTokens.get(0).getCreatedTimestamp().before(new Timestamp(System.currentTimeMillis()-(60*60*1000))));
					if(resend) {
						System.out.println("resend");
						VerificationToken verificationToken = createVerificationToken(localUser);
						verificationTokenRepository.save(verificationToken);
						emailService.sendVerificationEmail(verificationToken);
					}
					throw new UserNotVerifiedException(resend);
				}
			}
				
		}
			
		return null;
	}
	
	@Transactional
	public Boolean verifyUser(String token) {
		Optional<VerificationToken> opToken =verificationTokenRepository.findByToken(token);
		if(opToken.isPresent()) {
			VerificationToken verificationToken=opToken.get();
			LocalUser user =verificationToken.getLocalUser();
			if(!user.getEmailVerified()) {
				user.setEmailVerified(true);
				repository.save(user);
				verificationTokenRepository.deleteByLocalUser(user);
				return true;
			}
		}
		return false;
	}
}
