package com.policyexpert.assignment;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;

public class CheckoutPricing {
    public BigDecimal totalPrice(ImmutableList<BasketItem> basketItems) {
        return basketItems.stream()
                .map(BasketItem::totalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
