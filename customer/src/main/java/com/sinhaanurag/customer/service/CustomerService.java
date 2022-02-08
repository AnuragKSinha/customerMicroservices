package com.sinhaanurag.customer.service;

import com.sinhaanurag.customer.model.Customer;
import com.sinhaanurag.customer.dao.CustomerRepository;
import com.sinhaanurag.customer.request.CustomerRequest;
import com.sinhaanurag.customer.response.FraudCheckResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository repo;
	@Autowired
	RestTemplate restTemplate;
	public void registerCustomer(CustomerRequest request){
		Customer customer = Customer.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.build();
		//todo: check if email is valid
		//todo: check if email not taken
		/**
		 * this is done to save and flush customer Data
		 * So that customer Id can be used else just
		 * by saving the customer will return Id as null
		 */
		repo.saveAndFlush(customer);
		//todo: check if fraudster
		FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
		"http://FRAUD/api/v1/fraud-check/{customerId}",
				FraudCheckResponse.class,
				customer.getId()
		);
		if(fraudCheckResponse.getIsFraudulentCustomer()){
			throw new IllegalStateException("fraudster");
		}
		//todo: send notification
	}
}
