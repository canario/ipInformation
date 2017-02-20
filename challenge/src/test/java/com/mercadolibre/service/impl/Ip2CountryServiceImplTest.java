package com.mercadolibre.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

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
import com.mercadolibre.controller.response.Ip2Country;
import com.mercadolibre.service.Ip2CountryService;

@RunWith(SpringRunner.class)
@RestClientTest(Ip2CountryService.class)
public class Ip2CountryServiceImplTest {

	@Autowired
	private Ip2CountryService ip2CountryService;

	@Mock
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private static String URL = "https://api.ip2country.info/ip?5.6.7.8";

	@Autowired
    private MockRestServiceServer server;

	@Test
	public void countryInformationTest() throws JsonProcessingException {

		Ip2Country ip2CountryResponse = new Ip2Country();
		ip2CountryResponse.setCountryCode("AR");;
		String detailsString = objectMapper.writeValueAsString(ip2CountryResponse);
		server.expect(requestTo(URL)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));

		Ip2Country ip2Country = ip2CountryService.getIpInformation("5.6.7.8");
		server.verify();

		assertThat(ip2Country.getCountryCode()).isEqualTo("AR");
	}

}
