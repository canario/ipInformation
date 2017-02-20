package com.mercadolibre.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.List;

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
import com.mercadolibre.controller.response.CountryInformation;
import com.mercadolibre.service.CountryInformationService;

@RunWith(SpringRunner.class)
@RestClientTest(CountryInformationService.class)
public class CountryInformationServiceImplTest {

	@Autowired
	private CountryInformationService countryInformationService;

	@Mock
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private static String URL = "http://restcountries.eu/rest/v1/alpha/ar";

	@Autowired
    private MockRestServiceServer server;

	@Test
	public void countryInformationTest() throws JsonProcessingException {

		CountryInformation countryInformationResponse = new CountryInformation();
		List<String> currencies = new ArrayList<>();
		currencies.add("EUR");
		countryInformationResponse.setCurrencies(currencies);
		String detailsString = objectMapper.writeValueAsString(countryInformationResponse);
		server.expect(requestTo(URL)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));

		CountryInformation countryInformation = countryInformationService.getCountryInformation("AR");
		server.verify();

		assertThat(countryInformation.getCurrencies().get(0)).isEqualTo("EUR");
	}

}
