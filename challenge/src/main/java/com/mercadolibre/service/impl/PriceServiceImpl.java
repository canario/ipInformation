package com.mercadolibre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.controller.response.Price;
import com.mercadolibre.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

	@Value("${app.priceUrl}")
	private String priceUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Price getPrices(String currency) {
		Price price = restTemplate.getForObject(priceUrl + currency, Price.class);
		return price;
	}

}
