package com.mercadolibre.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.controller.response.Price;
import com.mercadolibre.service.PriceService;

@RunWith(SpringRunner.class)
@RestClientTest(PriceService.class)
public class PriceServiceImplTest {

	@Autowired
	private PriceService priceService;

	@Mock
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private static String URL = "http://api.fixer.io/latest?base=ARS";

	@Autowired
    private MockRestServiceServer server;

	@Test
	public void countryInformationTest() throws JsonProcessingException {

		Price priceResponse = new Price();
		Map<String, Double> rates = new HashMap<>();
		rates.put("ARS", new Double(16));
		priceResponse.setRates(rates);
		String detailsString = objectMapper.writeValueAsString(priceResponse);
		server.expect(requestTo(URL)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));

		Price price = priceService.getPrices("ARS");
		server.verify();

		assertThat(price.getRates().get("ARS")).isEqualTo(new Double(16));
	}

}
