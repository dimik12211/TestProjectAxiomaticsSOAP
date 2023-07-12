package org.example.Application.producingwebservice;

import org.example.Application.Service.ServiceXml;
import org.example.application.producingwebservice.GetCountryRequest;
import org.example.application.producingwebservice.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://www.example.org/Application/producingwebservice";

    @Autowired
    private ServiceXml serviceXml;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest xml) {
        GetCountryResponse getCountryResponse = null;
        try {
            getCountryResponse = new GetCountryResponse();
            getCountryResponse.setXml(serviceXml.xmlDeleteCDATA(xml.getXml()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getCountryResponse;
    }
}