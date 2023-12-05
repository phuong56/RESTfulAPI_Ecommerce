package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.LocalUser;

@Repository
public interface LocalUserRepository extends JpaRepository<LocalUser, Long> {
	Optional<LocalUser> findByUsername(String username);
}
