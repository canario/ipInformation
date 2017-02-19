package com.mercadolibre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mercadolibre.controller.response.Greeting;
import com.mercadolibre.controller.response.IpResponse;
import com.mercadolibre.manager.TraceIpManager;

@Controller
public class HomeController {

	@Autowired
	private TraceIpManager traceIpmanager;

	@GetMapping(value = { "/greeting", "/" })
	public String greetingForm(Model model) {
		model.addAttribute("greeting", new Greeting());
		return "greeting";
	}

	@PostMapping("/greeting")
	public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
		IpResponse ipResponse = traceIpmanager.traceIp(greeting.getIp());
		ipResponse.setIp(greeting.getIp());
		model.addAttribute("ipResponse", ipResponse);
		return "result";
	}
}
