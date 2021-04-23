package com.gamesys.task;

import com.gamesys.task.integration.CoinCapConnector;
import com.gamesys.task.integration.model.Cryptocurrency;
import com.gamesys.task.service.CryptocurrencyServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CryptocurrencyServiceTest {

    private final String resourceUrl = "https://api.coincap.io/v2/assets";
    private final String cryptoCurrencyId = "funfair";

    @InjectMocks
    private final CoinCapConnector coinCapConnector = new CoinCapConnector( resourceUrl,
            cryptoCurrencyId);

    DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:schema.sql")
            .addScript("classpath:test-data.sql")
            .build();

    @InjectMocks
    private final CryptocurrencyServiceImpl cryptoCurrencyService = new CryptocurrencyServiceImpl(new JdbcTemplate(dataSource), "10");

    @Test
    public void sendRequestTest() {
        List<Cryptocurrency> cryptoCurrencies = cryptoCurrencyService.getCryptocurrencies();
        Assert.assertEquals(cryptoCurrencies.get(0).getCryptocurrencyValue().getId(),"funfair");
    }
}
