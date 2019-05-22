package com.policyexpert.assignment;

import java.math.BigDecimal;

import static com.policyexpert.assignment.PricingStrategy.scaleSet;

public enum Item {

    Beans(new BigDecimal("0.50")),
    Coke(new BigDecimal("0.70")),
    Oranges(new BigDecimal("1.99"));

    private final BigDecimal unitPrice;

    Item(BigDecimal unitPrice) {
        this.unitPrice = scaleSet(unitPrice);
    }

    public BigDecimal unitPrice() {
        return unitPrice;
    }
}
