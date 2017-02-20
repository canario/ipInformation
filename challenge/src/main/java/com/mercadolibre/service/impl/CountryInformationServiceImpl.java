package com.mercadolibre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.controller.response.CountryInformation;
import com.mercadolibre.service.CountryInformationService;

@Service
public class CountryInformationServiceImpl implements CountryInformationService {
	
	@Value("${app.countryInformationUrl}")
	private String countryInformationUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public CountryInformation getCountryInformation(String countryCode) {
		CountryInformation countryInformation = restTemplate.getForObject(
				countryInformationUrl + countryCode.toLowerCase(), CountryInformation.class);
		return countryInformation;
	}

}
