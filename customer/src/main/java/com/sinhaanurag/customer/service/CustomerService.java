package com.sinhaanurag.customer.service;

import com.sinhaanurag.clients.fraud.FraudCheckResponse;
import com.sinhaanurag.clients.fraud.FraudClient;
import com.sinhaanurag.clients.notification.NotificationClient;
import com.sinhaanurag.clients.notification.NotificationRequest;
import com.sinhaanurag.customer.model.Customer;
import com.sinhaanurag.customer.dao.CustomerRepository;
import com.sinhaanurag.customer.request.CustomerRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository repo;
	@Autowired
	FraudClient client;
	@Autowired
	NotificationClient notificationClient;
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
		//todo: make it async: i.e. add it to Queue
		notificationClient.sendNotification(NotificationRequest.builder()
				.toCustomerId(customer.getId())
				.toCustomerName(customer.getEmail())
				.message(String.format("Hi %s, Welcome to SinhaServices",customer.getFirstName()))
				.build());

	}
}
