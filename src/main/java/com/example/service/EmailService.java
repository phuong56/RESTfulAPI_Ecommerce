package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.model.VerificationToken;

@Service
public class EmailService {
	
	@Value("${app.frontend.url}")
	String url;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${email.from}")
	private String fromAddress;
	
	private SimpleMailMessage makeSimpleMail() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromAddress);
		return simpleMailMessage;
	}
	
	public void sendVerificationEmail(VerificationToken verificationToken) {
		SimpleMailMessage message = makeSimpleMail();
		message.setTo(verificationToken.getLocalUser().getEmail());
		message.setSubject("verifity to active your account");
		message.setText("Pls follow the link below to verify your email "+url+"/auth/verify?token="+verificationToken.getToken() );
		try {
			javaMailSender.send(message);
		}catch(MailException ex) {
			System.out.println("send fail try again");
		}
	}
	
}
