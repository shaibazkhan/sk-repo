package com.policyexpert.assignment;

import java.math.BigDecimal;

public class CheckoutPricing {
    public BigDecimal totalPrice(Item item) {
        return item.unitPrice();
    }
}
