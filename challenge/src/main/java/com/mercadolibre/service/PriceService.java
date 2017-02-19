package com.mercadolibre.service;

import com.mercadolibre.controller.response.Price;

public interface PriceService {

	Price getPrices(String currency);

}
