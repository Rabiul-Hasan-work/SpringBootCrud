package com.mycode.loginService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycode.loginService.entity.Customer;
import com.mycode.loginService.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Customer> customerList = service.listAll();
		model.addAttribute("getAllEmployee", customerList);
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("customer", new Customer());	
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(Customer customer) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodePassword);
		
		service.save(customer);
		
		return "register_success";
	}
	
	@GetMapping("/dashboard")
	public String dashBoard(Model model) {
		List<Customer> customerList= service.listAll();
		model.addAttribute("getAllEmployee", customerList);
		return "dashboard";
	}
	
	@GetMapping("/customer")
	public String listCustomer(Model model, String keyword) {
		List<Customer> listCustomers = service.listAll();
		model.addAttribute("listCustomer", listCustomers);
		
		if(keyword != null) {
			
			model.addAttribute("listCustomer", service.findByKeyword(keyword));
		}
		else
		{	
			model.addAttribute("listCustomer", service.listAll());
		}
		
		return "customer";
	}
	
	@RequestMapping("/new_add")
	public String viewNewCustomerForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "insert";
	}
	
	@RequestMapping(value = "/save_customer", method = RequestMethod.POST)
	public String addNewCustomer(@ModelAttribute("customer") Customer customer) {
		service.create(customer);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView viewEditCustomerForm(@PathVariable(name = "id") long id) {
		
		ModelAndView mav = new ModelAndView("update");
		Customer customer = service.updateid(id);
		mav.addObject("customer", customer);
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable(name = "id") long id) {
		
		service.delete(id);
		return "redirect:/";
	}
		

}
