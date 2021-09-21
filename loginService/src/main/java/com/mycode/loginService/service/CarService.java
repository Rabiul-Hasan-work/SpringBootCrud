package com.mycode.loginService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycode.loginService.entity.Car;
import com.mycode.loginService.repository.carRepository;

@Service
public class CarService {
	
	@Autowired
	private carRepository crepository;
	
	// Return list of cars
	public List<Car> listAll() {
		
		return crepository.findAll();
	}
	
	// Save new Car
	public void create(Car car) {
		
		crepository.save(car);
	}
	
	// get by Id
	public Car updateid(Long id) {
		
		return crepository.findById(id).get();
	}
	
	// delete Id
	public void delete(Long id) {
		
		crepository.deleteById(id);
	}
	

}
