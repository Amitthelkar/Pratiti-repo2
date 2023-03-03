package com.pratiti.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pratiti.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	public boolean existsByEmail(String email);
	
	public Optional<Customer> findByEmail(String email);
	
}
