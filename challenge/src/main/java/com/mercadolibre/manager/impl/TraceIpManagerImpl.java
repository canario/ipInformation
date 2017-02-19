package com.mercadolibre.manager.impl;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.controller.response.CountryInformation;
import com.mercadolibre.controller.response.Ip2Country;
import com.mercadolibre.controller.response.IpResponse;
import com.mercadolibre.manager.TraceIpManager;
import com.mercadolibre.service.CountryInformationService;
import com.mercadolibre.service.Ip2CountryService;

@Service
public class TraceIpManagerImpl implements TraceIpManager {

	@Autowired
	private Ip2CountryService ip2CountryService;

	@Autowired
	private CountryInformationService countryInformationService;

	@Override
	public IpResponse traceIp(String ip) {

		Ip2Country ip2Country = ip2CountryService.getIpInformation(ip);
		IpResponse ipResponse = new IpResponse();
		ipResponse.setIp(ip);
		ipResponse.setCountryCode(ip2Country.getCountryCode());
		ipResponse.setPais(ip2Country.getCountryName());
		CountryInformation countryInformation = countryInformationService
				.getCountryInformation(ip2Country.getCountryCode());
		ipResponse.setPais(countryInformation.getTranslations().get("es"));
		ipResponse.setLanguages(countryInformation.getLanguages());
		ipResponse.setCurrencies(countryInformation.getCurrencies());
		LocalTime currentTime = LocalTime.now(ZoneId.of(countryInformation.getTimezones().get(0)));
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		ipResponse.setHora(currentTime.format(dateTimeFormatter));
		double distance = distance(countryInformation.getLatlng().get(0), -34, countryInformation.getLatlng().get(1),
				-64);
		ipResponse.setDistance(distance);
		return ipResponse;
	}

	public double distance(double lat1, double lat2, double lon1, double lon2) {

		final int R = 6371; // Radius of the earth

		Double latDistance = Math.toRadians(lat2 - lat1);
		Double lonDistance = Math.toRadians(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c; // convert to meters

		distance = Math.pow(distance, 2);

		return Math.sqrt(distance);
	}

}
