package com.mercadolibre.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.controller.response.Ip2Country;
import com.mercadolibre.controller.response.IpResponse;
import com.mercadolibre.manager.TraceIpManager;
import com.mercadolibre.service.Ip2CountryService;

@Service
public class TraceIpManagerImpl implements TraceIpManager {

	@Autowired
	private Ip2CountryService ip2CountryService;

	@Override
	public IpResponse traceIp(String ip) {

		Ip2Country ip2Country = ip2CountryService.getIpInformation(ip);
		IpResponse ipResponse = new IpResponse();
		ipResponse.setIp(ip);
		ipResponse.setCountryCode(ip2Country.getCountryCode());
		ipResponse.setPais(ip2Country.getCountryName());
		return ipResponse;
	}

}
