package com.mercadolibre.controller.response;

public class Coin {

	private String currency;
	
	private Double cotizacion;

	public Coin(String currency, Double cotizacion) {
		this.cotizacion = cotizacion;
		this.currency = currency;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Double cotizacion) {
		this.cotizacion = cotizacion;
	}
}
