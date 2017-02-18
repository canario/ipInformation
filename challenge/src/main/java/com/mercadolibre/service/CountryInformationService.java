package com.mercadolibre.service;

import com.mercadolibre.controller.response.CountryInformation;

public interface CountryInformationService {

	CountryInformation getCountryInformation(String countryCode);

}
