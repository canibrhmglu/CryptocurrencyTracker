package com.gamesys.task.integration;

import com.gamesys.task.integration.model.Cryptocurrency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Slf4j
@Component
public class CoinCapConnector {



    public CoinCapConnector( @Value("${resource.url}") String resourceUrl,
                            @Value("${cryptocurrency.id}") String cryptocurrencyId) {
        this.restTemplate = new RestTemplate();
        this.resourceUrl = resourceUrl;
        this.cryptocurrencyId = cryptocurrencyId;
    }

    private final RestTemplate restTemplate;
    private final String resourceUrl;
    private final String cryptocurrencyId;

    public ResponseEntity<Cryptocurrency> getCurrencyData()  {

        String urlWithParameter = resourceUrl + "?search=" + cryptocurrencyId;

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity request = new HttpEntity(headers);

        return restTemplate.exchange(urlWithParameter, HttpMethod.GET,request, Cryptocurrency.class);
    }
}
