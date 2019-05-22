package com.policyexpert.assignment;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;

public class CheckoutPricing {

    private final Offer offer;

    CheckoutPricing(Offer offer) {
        this.offer = offer;
    }

    public BigDecimal totalPrice(ImmutableList<BasketItem> basketItems) {
        return basketItems.stream()
                .map(BasketItem::totalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add).subtract(offers(basketItems));
    }

    private BigDecimal offers(ImmutableList<BasketItem> items) {
        long count = items.stream().filter(basketItem -> basketItem.item() == Item.Coke)
                .map(BasketItem::quantity).findFirst().orElse(0);

        if (count > 1) {
            return new BigDecimal(count).multiply(new BigDecimal("0.20"));
        }

        return offer.apply(items);
    }
}
