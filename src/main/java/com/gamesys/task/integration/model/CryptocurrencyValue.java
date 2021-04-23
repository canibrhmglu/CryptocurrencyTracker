package com.gamesys.task.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CryptocurrencyValue {

    enum Type {
        token,
        coin
    }

    @NotBlank(message = "Id is mandatory")
    private String id;
    private String type;
    private int rank;
    @NotBlank(message = "Id is mandatory")
    private String symbol;
    private BigDecimal marketCapUsd;
    private BigDecimal priceUsd;
    private BigDecimal changePercent24Hr;
    @JsonProperty("explorer")
    private String website;

    public void setMarketCapUsd(BigDecimal marketCapUsd) {
        this.marketCapUsd = marketCapUsd.setScale(0, BigDecimal.ROUND_HALF_EVEN);
    }

    public void setPriceUsd(BigDecimal priceUsd) {
        this.priceUsd = priceUsd.setScale(6, BigDecimal.ROUND_HALF_EVEN);
    }

    public void setChangePercent24Hr(BigDecimal changePercent24Hr) {
        this.changePercent24Hr = changePercent24Hr.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public void setType(String type) {
        this.type = type;
    }

}

