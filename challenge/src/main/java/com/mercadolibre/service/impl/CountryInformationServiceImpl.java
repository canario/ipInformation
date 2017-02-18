package com.mercadolibre.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.controller.response.CountryInformation;
import com.mercadolibre.service.CountryInformationService;

@Service
public class CountryInformationServiceImpl implements CountryInformationService {

	@Override
	public CountryInformation getCountryInformation(String countryCode) {
		RestTemplate restTemplate = new RestTemplate();
		CountryInformation countryInformation = restTemplate.getForObject("https://api.ip2country.info/ip?",
				CountryInformation.class);
		return countryInformation;
	}

}
