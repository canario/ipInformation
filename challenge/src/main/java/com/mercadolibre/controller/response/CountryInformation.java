package com.mercadolibre.controller.response;

import java.util.List;
import java.util.Map;

public class CountryInformation {
	
	private Map<String, String> translations;
	
	private List<String> languages;
	
	private List<String> currencies;
	
	private List<String> timezones;
	
	private List<Integer> latlng;

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public Map<String, String> getTranslations() {
		return translations;
	}

	public void setTranslations(Map<String, String> translations) {
		this.translations = translations;
	}

	public List<String> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<String> currencies) {
		this.currencies = currencies;
	}

	public List<Integer> getLatlng() {
		return latlng;
	}

	public void setLatlng(List<Integer> latlng) {
		this.latlng = latlng;
	}

	public List<String> getTimezones() {
		return timezones;
	}

	public void setTimezones(List<String> timezones) {
		this.timezones = timezones;
	}

}
