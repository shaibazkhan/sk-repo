package com.policyexpert.assignment;

import java.math.BigDecimal;

import static com.policyexpert.assignment.Item.Oranges;

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

    public BigDecimal totalCost() {
        if (item() == Oranges) {
            return unitCostPrice().divide(new BigDecimal(1000))
                    .multiply(BigDecimal.valueOf(quantity())).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        return unitCostPrice().multiply(new BigDecimal(quantity()));
    }
}
