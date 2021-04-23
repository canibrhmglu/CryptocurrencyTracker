package com.gamesys.task.integration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Cryptocurrency {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Istanbul")
    private Timestamp createdDate;
    @JsonProperty("value")
    private CryptocurrencyValue cryptocurrencyValue;

    @JsonCreator
    public Cryptocurrency(@JsonProperty("data") List<CryptocurrencyValue> cryptoCurrencies, @JsonProperty("timestamp") Timestamp timestamp){
        this.createdDate = timestamp;
        this.cryptocurrencyValue = cryptoCurrencies.get(0);

        if(this.cryptocurrencyValue.getWebsite().contains(CryptocurrencyValue.Type.token.toString())) {
            this.cryptocurrencyValue.setType(CryptocurrencyValue.Type.token.toString());
        } else {
            this.cryptocurrencyValue.setType(CryptocurrencyValue.Type.coin.toString());
        }
    }

}

