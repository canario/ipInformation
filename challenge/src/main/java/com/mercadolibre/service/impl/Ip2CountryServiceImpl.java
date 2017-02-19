package com.mercadolibre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.controller.response.Ip2Country;
import com.mercadolibre.service.Ip2CountryService;

@Service
public class Ip2CountryServiceImpl implements Ip2CountryService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Ip2Country getIpInformation(String ip) {
		Ip2Country ip2Country = restTemplate.getForObject("https://api.ip2country.info/ip?" + ip, Ip2Country.class);
		return ip2Country;
	}

}
