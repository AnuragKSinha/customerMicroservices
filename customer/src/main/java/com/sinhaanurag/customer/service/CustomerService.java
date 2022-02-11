package com.sinhaanurag.customer.service;

import com.sinhaanurag.clients.fraud.FraudCheckResponse;
import com.sinhaanurag.clients.fraud.FraudClient;
import com.sinhaanurag.customer.model.Customer;
import com.sinhaanurag.customer.dao.CustomerRepository;
import com.sinhaanurag.customer.request.CustomerRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository repo;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	FraudClient client;
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
		FraudCheckResponse fraudCheckResponse = client.isFraudster(customer.getId());
		if(fraudCheckResponse.getIsFraudulentCustomer()){
			throw new IllegalStateException("fraudster");
		}
		//todo: send notification
	}
}
