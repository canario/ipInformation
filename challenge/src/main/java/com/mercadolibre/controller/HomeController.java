package com.mercadolibre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mercadolibre.controller.response.Ip;
import com.mercadolibre.controller.response.IpResponse;
import com.mercadolibre.manager.TraceIpManager;

@Controller
public class HomeController {

	@Autowired
	private TraceIpManager traceIpmanager;

	@GetMapping(value = { "/greeting", "/" })
	public String greetingForm(Model model) {
		model.addAttribute("ip", new Ip());
		return "home";
	}

	@PostMapping("/")
	public String greetingSubmit(@ModelAttribute Ip ip, Model model) {
		IpResponse ipResponse = traceIpmanager.traceIp(ip.getIp());
		ipResponse.setIp(ip.getIp());
		model.addAttribute("ipResponse", ipResponse);
		return "result";
	}
}
