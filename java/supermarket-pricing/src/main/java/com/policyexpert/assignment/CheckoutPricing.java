package com.policyexpert.assignment;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;

public class CheckoutPricing {

    private final ImmutableList<Offer> offers;

    CheckoutPricing(ImmutableList<Offer> offers) {
        this.offers = offers;
    }

    public BigDecimal totalPrice(ImmutableList<BasketItem> basketItems) {
        return basketItems.stream()
                .map(BasketItem::totalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add).subtract(offers(basketItems));
    }

    private BigDecimal offers(ImmutableList<BasketItem> items) {
        return offers.stream().map(offer -> offer.applyOn(items)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
