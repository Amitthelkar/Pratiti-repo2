package com.pratiti.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratiti.entity.Customer;
import com.pratiti.entity.Customer.Status;
import com.pratiti.exception.CustomerServiceException;
import com.pratiti.repository.AddressRepository;
import com.pratiti.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	public int register(Customer customer) {

		if (!customerRepository.existsByEmail(customer.getEmail())) {
			customer.setStatus(Status.INACTIVE);

			customer.getAddress().setCustomer(customer);
			customerRepository.save(customer);

			return customer.getId();
		} else {
			throw new CustomerServiceException("user already Present");
		}
	}

	public void activate(int id) {

		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			Customer customerdata = customer.get();
			if (customerdata.getStatus() != Status.LOCKED) {
				customerdata.setStatus(Status.ACTIVE);
				customerRepository.save(customerdata);
			} else
				throw new CustomerServiceException("Account is locked");

		} else
			throw new CustomerServiceException("Account is not present");

	}

	public Customer login(String email, String password) {

		if (customerRepository.existsByEmail(email)) {
			Optional<Customer> customer1 = customerRepository.findByEmail(email);
			Customer customerdata1 = customer1.get();

			if (password.equals(customerdata1.getPassword())) {
				if (customerdata1.getStatus() != Status.LOCKED) {
					System.out.println("successfully login");
					return customerdata1;
				} else {
					throw new CustomerServiceException("Account is loceked");

				}
			} else {
				throw new CustomerServiceException("Wrong Password");
			}
		} else {
			throw new CustomerServiceException("User is not present");
		}

	}
}
