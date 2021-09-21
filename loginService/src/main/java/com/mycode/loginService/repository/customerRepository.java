package com.mycode.loginService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycode.loginService.entity.Customer;

public interface customerRepository extends JpaRepository<Customer, Long> {
	
	@Query(value="select * from customer e where e.first_name like %:keyword% or e.last_name like %:keyword%", nativeQuery=true)
	List<Customer> findByKeyword(@Param("keyword") String keyword);
	
	@Query("SELECT u FROM Customer u WHERE u.email = ?1")
	public Customer findByEmail(String email);

}
