package com.mercadolibre.manager;

import com.mercadolibre.controller.response.IpResponse;

public interface TraceIpManager {

	IpResponse traceIp(String ip);

}
