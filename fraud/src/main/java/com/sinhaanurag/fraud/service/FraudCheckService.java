package com.sinhaanurag.fraud.service;

import java.time.LocalDateTime;

import com.sinhaanurag.fraud.dao.FraudCheckHistoryRepository;
import com.sinhaanurag.fraud.model.FraudCheckHistory;

import org.springframework.stereotype.Service;

@Service
public class FraudCheckService {
	private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

	public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository){
		this.fraudCheckHistoryRepository=fraudCheckHistoryRepository;
	}
	public boolean isFraudulentCustomer(Integer customerId){
		fraudCheckHistoryRepository.save(FraudCheckHistory.builder()
				.customerId(customerId)
				.isFraudster(false)
				.createdAt(LocalDateTime.now())
				.build());
		return false;
	}
}
