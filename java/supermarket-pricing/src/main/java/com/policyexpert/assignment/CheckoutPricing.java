package com.policyexpert.assignment;

import java.math.BigDecimal;

import static com.policyexpert.assignment.Item.Oranges;

public class CheckoutPricing {
    public BigDecimal totalPrice(BasketItem basketItem) {
        if (basketItem.item() == Oranges) {
            return basketItem.unitCostPrice().divide(new BigDecimal(1000))
                    .multiply(BigDecimal.valueOf(basketItem.quantity())).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        return basketItem.unitCostPrice();
    }
}
