package com.policyexpert.assignment;

import java.math.BigDecimal;

public class BasketItem {

    private final Item item;
    private final int quantity;

    BasketItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item item() {
        return item;
    }

    public BigDecimal unitCostPrice() {
        return item.unitPrice();
    }

    public int quantity() {
        return quantity;
    }
}
