package com.example.api.controller.auth;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.LoginBody;
import com.example.api.model.LoginResponse;
import com.example.api.model.RegisterBody;
import com.example.exception.EmailFailureException;
import com.example.exception.UserNotVerifiedException;
import com.example.model.LocalUser;
import com.example.service.MainService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	MainService mainService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterBody registerBody, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println(result);
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		LocalUser localUser = mainService.registerUser(registerBody);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginBody loginBody, BindingResult result){
		String jwt=null;
		if(result.hasErrors()) {
			System.out.println(result); //check why issue happen
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		LoginResponse loginResponse =new LoginResponse();
		try {
			jwt =mainService.loginUser(loginBody);
		}catch (UserNotVerifiedException e) {	
			loginResponse.setSuccess(false);
			loginResponse.setFailureReason("User is not verified");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(loginResponse);
		}catch(EmailFailureException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		if(jwt==null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //sai mk or tk
		loginResponse.setJwt(jwt);
		loginResponse.setSuccess(true);
		return ResponseEntity.ok(loginResponse); // ok chay ngon va tra ve token cai ma chua thong tin user
	}
	@GetMapping("/me")
	public LocalUser GetLoggedInUserProfile(@AuthenticationPrincipal LocalUser user) {
		System.out.println(user);
		return user;
	}
	
	@PostMapping("/verify")
	public ResponseEntity<?> verifyEmail(@RequestParam String token){
		System.out.println(token);
		System.out.println("sadad");
		if(mainService.verifyUser(token))
			return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
}
