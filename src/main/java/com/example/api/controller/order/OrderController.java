package com.example.api.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.LocalUser;
import com.example.model.WebOrder;
import com.example.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public List<WebOrder >getOrder(@AuthenticationPrincipal LocalUser user){
		System.out.println(user.getUsername());
		System.out.println("ok order");
		List<WebOrder> x= orderService.getOrder(user);
		return x;
	}
}
