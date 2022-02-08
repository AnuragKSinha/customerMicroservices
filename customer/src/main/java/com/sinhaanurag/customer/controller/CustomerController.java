package com.sinhaanurag.customer.controller;

import com.sinhaanurag.customer.request.CustomerRequest;
import com.sinhaanurag.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/app/v1/customers")
public class CustomerController {
	private final CustomerService customerService;
	public CustomerController(CustomerService customerService){
		this.customerService=customerService;
	}
	@PostMapping
	public void registerCustomer(@RequestBody CustomerRequest customerRequest){
		log.info("new customer registration {}",customerRequest);
		customerService.registerCustomer(customerRequest);
	}

}
