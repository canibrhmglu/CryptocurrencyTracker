package com.gamesys.task.service;

import com.gamesys.task.integration.model.Cryptocurrency;
import com.gamesys.task.integration.model.CryptocurrencyValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    public CryptocurrencyServiceImpl(JdbcTemplate jdbcTemplate, @Value("${query.limitValue}") String queryLimitValue) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryLimitValue = queryLimitValue;
    }

    private final JdbcTemplate jdbcTemplate;
    private final String queryLimitValue;

    public List<Cryptocurrency> getCryptocurrencies() {

        String sql = "SELECT * FROM cryptocurrency order by createddate desc limit " + queryLimitValue;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->{
                    List<CryptocurrencyValue> cryptoCurrencies = new ArrayList();

                    cryptoCurrencies.add(CryptocurrencyValue.builder().id(rs.getString("id")).rank(rs.getInt("rank"))
                            .type(rs.getString("type"))
                            .symbol(rs.getString("symbol"))
                            .marketCapUsd(rs.getBigDecimal("marketCapUsd"))
                            .priceUsd(rs.getBigDecimal("priceUsd"))
                            .changePercent24Hr(rs.getBigDecimal("changePercent24Hr"))
                            .website(rs.getString("website")).build());
                    Cryptocurrency cryptoCurrency = new Cryptocurrency(cryptoCurrencies,rs.getTimestamp("createdDate"));
                    return cryptoCurrency;}
        );

    }
}
