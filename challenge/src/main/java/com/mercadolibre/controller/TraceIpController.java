package com.mercadolibre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.controller.response.IpResponse;
import com.mercadolibre.manager.TraceIpManager;

@RestController
@RequestMapping(value = "/traceip")
public class TraceIpController {
	
	@Autowired
	private TraceIpManager traceIpmanager;
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<IpResponse> getLoan(@RequestParam(required = true) String ip) {

		IpResponse ipResponse = traceIpmanager.traceIp(ip);
		ipResponse.setIp(ip);
		
		return new ResponseEntity<IpResponse>(ipResponse, HttpStatus.OK);
	}


}
