package com.example.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.model.LocalUser;
import com.example.repository.LocalUserRepository;
import com.example.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JWTRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private LocalUserRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String tokenHeader =request.getHeader("Authorization");
		if(tokenHeader!=null&&tokenHeader.startsWith("Bearer ")) {
			String token =tokenHeader.substring(7);
			try {
				String username =jwtService.getUsername(token);
				Optional<LocalUser>opUser=repository.findByUsername(username);
				if(opUser.isPresent()) {
					LocalUser localUser=opUser.get();
					UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(localUser, null, new ArrayList<GrantedAuthority>());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
			catch(JWTDecodeException ex) {
				System.out.println(ex);
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}
	

}
