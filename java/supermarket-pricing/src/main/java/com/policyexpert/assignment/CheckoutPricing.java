package com.policyexpert.assignment;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;

import static com.policyexpert.assignment.Item.Beans;

public class CheckoutPricing {
    public BigDecimal totalPrice(ImmutableList<BasketItem> basketItems) {
        return basketItems.stream()
                .map(BasketItem::totalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add).subtract(offers(basketItems));
    }

    private BigDecimal offers(ImmutableList<BasketItem> items) {
        long count = items
                .stream().filter(basketItem -> basketItem.item() == Beans)
                .map(BasketItem::quantity).findAny().orElse(0);

        return new BigDecimal(count/3).multiply(Beans.unitPrice());
    }
}
