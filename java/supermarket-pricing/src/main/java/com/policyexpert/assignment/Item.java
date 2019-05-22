package com.policyexpert.assignment;

import java.math.BigDecimal;

public enum Item {

    Beans(new BigDecimal("0.50")),
    Coke(new BigDecimal("0.70"));

    private final BigDecimal unitPrice;

    Item(BigDecimal unitPrice) {
        this.unitPrice = unitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);;
    }

    public BigDecimal unitPrice() {
        return unitPrice;
    }
}
