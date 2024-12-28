package com.example.countries;

import com.example.countries.wsdl.GetCountryRequest;
import com.example.countries.wsdl.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.namespace.QName;
import java.io.IOException;

public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    public GetCountryResponse getCountry(String country) {

        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        log.info("Requesting location for " + country);

        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request, new HeaderCallback());
        return response;
    }

    static class HeaderCallback extends SoapActionCallback {

        HeaderCallback() {
            super("http://spring.io/guides/gs-producing-web-service/GetCountryRequest");
        }

        @Override
        public void doWithMessage(WebServiceMessage message) throws IOException {
            super.doWithMessage(message);
            SoapMessage soapMessage = (SoapMessage) message;
            SoapHeaderElement userElement = soapMessage.getSoapHeader().addHeaderElement(new QName("http://spring.io/guides/gs-producing-web-service", "User"));
            userElement.setText("Hugo");
        }
    }
}