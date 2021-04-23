package com.gamesys.task.scheduledtasks;

import com.gamesys.task.integration.CoinCapConnector;
import com.gamesys.task.integration.model.Cryptocurrency;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class ScheduledApiCall {

    private final CoinCapConnector coinCapConnector;
    private final JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void sendRequest() {

        Cryptocurrency cryptocurrency = coinCapConnector.getCurrencyData().getBody();

        jdbcTemplate.update(
                "INSERT INTO cryptocurrency VALUES (?,?,?,?,?,?,?,?,?)", cryptocurrency.getCryptocurrencyValue().getId(),
                cryptocurrency.getCryptocurrencyValue().getType(),
                cryptocurrency.getCryptocurrencyValue().getRank(),
                cryptocurrency.getCryptocurrencyValue().getSymbol(),
                cryptocurrency.getCryptocurrencyValue().getMarketCapUsd(),
                cryptocurrency.getCryptocurrencyValue().getPriceUsd(),
                cryptocurrency.getCryptocurrencyValue().getChangePercent24Hr(),
                cryptocurrency.getCryptocurrencyValue().getWebsite(),
                cryptocurrency.getCreatedDate());
    }

}
