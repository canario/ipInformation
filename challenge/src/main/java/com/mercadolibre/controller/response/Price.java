package com.mercadolibre.controller.response;

import java.util.Map;

public class Price {
	
	private Map<String, Double> rates;

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

}
