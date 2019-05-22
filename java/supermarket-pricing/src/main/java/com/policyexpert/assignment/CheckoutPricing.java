package com.policyexpert.assignment;

import java.math.BigDecimal;

public class CheckoutPricing {
    public BigDecimal totalPrice(String item) {
        if (item.equals("Beans")) {
            return new BigDecimal("0.50");
        }

        return new BigDecimal("0.70");
    }
}
