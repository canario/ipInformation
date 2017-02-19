package com.mercadolibre.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.controller.response.Price;
import com.mercadolibre.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

	@Override
	public Price getPrices() {
		RestTemplate restTemplate = new RestTemplate();
		Price price = restTemplate.getForObject("http://api.fixer.io/latest?base=USD", Price.class);
		return price;
	}

}
