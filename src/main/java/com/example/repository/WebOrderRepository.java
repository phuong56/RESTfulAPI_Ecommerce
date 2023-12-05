package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.LocalUser;
import com.example.model.WebOrder;

public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {
	List<WebOrder> findByUser(LocalUser user);
}
