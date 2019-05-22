package com.policyexpert.assignment;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;

import static com.policyexpert.assignment.Item.Oranges;

public class CheckoutPricing {
    public BigDecimal totalPrice(ImmutableList<BasketItem> basketItems) {
        return basketItems.stream()
                .map(this::calculatePrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculatePrice(BasketItem basketItem) {
        if (basketItem.item() == Oranges) {
            return basketItem.unitCostPrice().divide(new BigDecimal(1000))
                    .multiply(BigDecimal.valueOf(basketItem.quantity())).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        return basketItem.unitCostPrice().multiply(new BigDecimal(basketItem.quantity()));
    }
}
