package com.gamesys.task;

import com.gamesys.task.integration.CoinCapConnector;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class CoinCapConnectorTest {

    private final String resourceUrl = "https://api.coincap.io/v2/assets";
    private final String cryptocurrencyId = "funfair";

    @InjectMocks
    private final CoinCapConnector coinCapConnector = new CoinCapConnector(  resourceUrl,
            cryptocurrencyId);

    @Test
    public void sendRequestTest() {
        Assert.assertEquals(HttpStatus.OK, coinCapConnector.getCurrencyData().getStatusCode());
    }
}
