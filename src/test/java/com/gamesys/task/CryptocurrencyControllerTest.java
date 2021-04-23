package com.gamesys.task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamesys.task.integration.model.Cryptocurrency;
import com.gamesys.task.integration.model.CryptocurrencyValue;
import com.gamesys.task.service.CryptocurrencyServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
public class CryptocurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CryptocurrencyServiceImpl cryptocurrencyService;

    @Test
    public void getCryptoCurrenciesTest() {
        List<CryptocurrencyValue> cryptocurrencyValues = new ArrayList();
        CryptocurrencyValue cryptocurrencyValue = CryptocurrencyValue.builder().id("funfair")
                .type("token")
                .rank(99)
                .symbol("FUN")
                .marketCapUsd(new BigDecimal(286811700))
                .priceUsd(new BigDecimal(0.027749))
                .changePercent24Hr(new BigDecimal(-17.27))
                .website("https://etherscan.io/token/FunFair").build();
        cryptocurrencyValues.add(cryptocurrencyValue);

        Cryptocurrency cryptocurrency = new Cryptocurrency(cryptocurrencyValues, Timestamp.valueOf(LocalDateTime.now()));
        List<Cryptocurrency> cryptocurrencyList = new ArrayList();
        cryptocurrencyList.add(cryptocurrency);
        Mockito.when(cryptocurrencyService.getCryptocurrencies()).thenReturn(cryptocurrencyList);

        try {
            String response = mockMvc.perform(get("/cryptoCurrencies")
                    .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse().getContentAsString();
            List<Cryptocurrency> cryptoCurrencies = objectMapper.readValue(response, new TypeReference<List<Cryptocurrency>>(){});

            Assert.assertEquals(cryptoCurrencies.get(0).getCryptocurrencyValue().getChangePercent24Hr(),
                    cryptocurrency.getCryptocurrencyValue().getChangePercent24Hr());

        } catch (Exception e) {

        }

    }

}
