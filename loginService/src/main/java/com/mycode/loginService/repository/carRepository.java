package com.mycode.loginService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycode.loginService.entity.Car;

public interface carRepository extends JpaRepository<Car, Long> {

}
