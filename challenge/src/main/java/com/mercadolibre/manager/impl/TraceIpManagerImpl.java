package com.mercadolibre.manager.impl;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mercadolibre.controller.response.Coin;
import com.mercadolibre.controller.response.CountryInformation;
import com.mercadolibre.controller.response.Ip2Country;
import com.mercadolibre.controller.response.IpResponse;
import com.mercadolibre.controller.response.Price;
import com.mercadolibre.manager.TraceIpManager;
import com.mercadolibre.service.CountryInformationService;
import com.mercadolibre.service.Ip2CountryService;
import com.mercadolibre.service.PriceService;

@Component
public class TraceIpManagerImpl implements TraceIpManager {
	
	@Value("${app.latitude}")
	private double latitude;
	
	@Value("${app.longitud}")
	private double longitud;

	@Autowired
	private Ip2CountryService ip2CountryService;

	@Autowired
	private CountryInformationService countryInformationService;

	@Autowired
	private PriceService priceService;

	private static final Pattern PATTERN = Pattern
			.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	private boolean validate(final String ip) {
		return PATTERN.matcher(ip).matches();
	}

	@Override
	public IpResponse traceIp(String ip) {
		IpResponse ipResponse = new IpResponse();
		if (validate(ip)) {
			Ip2Country ip2Country = ip2CountryService.getIpInformation(ip);
			
			ipResponse.setIp(ip);
			ipResponse.setCountryCode(ip2Country.getCountryCode());
			ipResponse.setPais(ip2Country.getCountryName());
			CountryInformation countryInformation = countryInformationService
					.getCountryInformation(ip2Country.getCountryCode());
			ipResponse.setPais(countryInformation.getTranslations().get("es"));
			ipResponse.setLanguages(countryInformation.getLanguages());
			LocalTime currentTime = LocalTime.now(ZoneId.of(countryInformation.getTimezones().get(0)));
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			ipResponse.setHora(currentTime.format(dateTimeFormatter));
			double distance = distance(countryInformation.getLatlng().get(0), latitude,
					countryInformation.getLatlng().get(1), longitud);
			ipResponse.setDistance(distance);
			List<Coin> coins = new ArrayList<>();
			for (String currency : countryInformation.getCurrencies()) {
				Price price = priceService.getPrices(currency);
				coins.add(new Coin(currency, price.getRates().get("USD")));
			}
			ipResponse.setCoins(coins);
		}
		return ipResponse;
	}

	private double distance(double lat1, double lat2, double lon1, double lon2) {

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
