package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.LocalUser;
import com.example.model.WebOrder;
import com.example.repository.ProductRepository;
import com.example.repository.WebOrderRepository;

@Service
public class OrderService {
	@Autowired
	private WebOrderRepository webOrderRepository;
	@Autowired
	private ProductRepository productRepository;
	
	public List<WebOrder> getOrder(LocalUser user){
		return webOrderRepository.findByUser(user);
	}
}
