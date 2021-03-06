package com.sinhaanurag.fraud.controller;

import com.sinhaanurag.clients.fraud.FraudCheckResponse;
import com.sinhaanurag.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/fraud-check")
public class FraudController {
	private final FraudCheckService fraudCheckService;
	public FraudController(FraudCheckService fraudCheckService){
		this.fraudCheckService=fraudCheckService;
	}
	@GetMapping(path="{customerId}")
	public FraudCheckResponse isFraudster(@PathVariable("customerId")Integer customerId){
		boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
		log.info("fraud check request for customer {}",customerId);
		return FraudCheckResponse.builder()
				.isFraudulentCustomer(isFraudulentCustomer)
				.build();
	}

}
