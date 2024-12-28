package com.example.countries;

import com.example.countries.wsdl.GetCountryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountriesFullApplicationIT {

    @LocalServerPort
    private int localPort;

    @Value("classpath:/wsdl/countries.wsdl")
    private Resource countriesWsdl;

    private final CountryClient countryClient = new CountryClient();

    @BeforeEach
    void init() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.countries.wsdl");
        countryClient.setMarshaller(marshaller);
        countryClient.setUnmarshaller(marshaller);
        countryClient.setDefaultUri(String.format("http://localhost:%s/ws", localPort));
    }

    @Test
    void mustReturnWsdl() {
        RestTemplate restTemplate = new RestTemplate();
        String wsdl = restTemplate.getForObject("http://localhost:" + localPort + "/ws/countries.wsdl", String.class);
        assertThat(wsdl).isEqualToIgnoringWhitespace(asString(countriesWsdl).replace("localhost:8080/", String.format("localhost:%d/", localPort)));
    }

    @Test
    void mustReturnCountry() {
        GetCountryResponse response = countryClient.getCountry("Spain");
        assertThat(response.getCountry().getCapital()).isEqualTo("Madrid");
    }

    @Test
    void mustReturnNullForUnknownCountry() {
        GetCountryResponse response = countryClient.getCountry("Australia");
        assertThat(response.getCountry()).isNull();
    }

    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
