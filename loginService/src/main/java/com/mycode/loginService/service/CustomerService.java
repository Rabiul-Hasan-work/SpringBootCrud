package com.mycode.loginService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycode.loginService.entity.Customer;
import com.mycode.loginService.repository.customerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private customerRepository repository;
	
	//Return list of customer
	public List<Customer> listAll() {
		
		return repository.findAll();
	}
	
	//Save new customer
	public void create(Customer customer) {
		
		repository.save(customer);
	}
	
	//get by Id
	public Customer updateid(Long id) {
		
		return repository.findById(id).get();
	}
	
	//delete Id
	public void delete(Long id) {
		
		repository.deleteById(id);
	}
	
	//Get employees by keyword
	public List<Customer> findByKeyword(String keyword) {
		return repository.findByKeyword(keyword);
	}

	public void save(Customer customer) {
		repository.save(customer);
		
	}

}
