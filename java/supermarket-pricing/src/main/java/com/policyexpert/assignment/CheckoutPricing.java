package com.policyexpert.assignment;

import java.math.BigDecimal;

import static com.policyexpert.assignment.Item.Oranges;

public class CheckoutPricing {
    public BigDecimal totalPrice(Item item, int quantity) {
        if (item == Oranges) {
            return item.unitPrice().divide(new BigDecimal(1000))
                    .multiply(BigDecimal.valueOf(quantity)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        return item.unitPrice();
    }
}
