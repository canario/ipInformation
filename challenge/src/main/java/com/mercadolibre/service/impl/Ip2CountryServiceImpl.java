package com.mercadolibre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.controller.response.Ip2Country;
import com.mercadolibre.service.Ip2CountryService;

@Service
public class Ip2CountryServiceImpl implements Ip2CountryService {
	
	@Value("${app.ip2CountryUrl}")
	private String ip2CountryUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Ip2Country getIpInformation(String ip) {
		Ip2Country ip2Country = restTemplate.getForObject(ip2CountryUrl+ ip, Ip2Country.class);
		return ip2Country;
	}

}
