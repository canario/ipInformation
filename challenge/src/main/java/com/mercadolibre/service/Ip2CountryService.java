package com.mercadolibre.service;

import com.mercadolibre.controller.response.Ip2Country;

public interface Ip2CountryService {

	Ip2Country getIpInformation(String ip);

}
