package com.sinhaanurag.fraud.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FraudCheckResponse {
	private Boolean isFraudulentCustomer;
}
