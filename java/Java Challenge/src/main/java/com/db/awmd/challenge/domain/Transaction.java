package com.db.awmd.challenge.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class Transaction {

    @NotNull
    @NotEmpty
    private String id;

    private String from;

    private String to;

    @Min(value = 0, message = "Amount to transfer must be positive")
    private BigDecimal amount;

    @JsonCreator
    public Transaction(@JsonProperty("id") String id,
                       @JsonProperty("from") String from,
                       @JsonProperty("to") String to,
                       @JsonProperty("amount") BigDecimal amount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}

