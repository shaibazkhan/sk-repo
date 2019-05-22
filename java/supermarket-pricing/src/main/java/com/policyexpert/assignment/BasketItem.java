package com.policyexpert.assignment;

import java.math.BigDecimal;

public class BasketItem {

    private final Item item;
    private final int quantity;
    private final PricingStrategy pricingStrategy;

    BasketItem(Item item, int quantity, PricingStrategy pricingStrategy) {
        this.item = item;
        this.quantity = quantity;
        this.pricingStrategy = pricingStrategy;
    }

    Item item() {
        return item;
    }

    BigDecimal unitCostPrice() {
        return item.unitPrice();
    }

    int quantity() {
        return quantity;
    }

    public BigDecimal totalCost() {
        return pricingStrategy.calculateCost(item, quantity);
    }
}
