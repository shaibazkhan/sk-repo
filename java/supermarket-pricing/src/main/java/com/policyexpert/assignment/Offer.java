package com.policyexpert.assignment;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;

public interface Offer {

    BigDecimal applyOn(ImmutableList<BasketItem> items);

    static Offer threeForTwoOfferOn(Item item) {
        return (items) -> {
            long count = items
                    .stream().filter(basketItem -> basketItem.item() == item)
                    .map(BasketItem::quantity).findFirst().orElse(0);

            return new BigDecimal(count/3).multiply(item.unitPrice());
        };
    }

    static Offer twoForAPrice(Item item, BigDecimal discount) {
        return (items) -> {
            long count = items.stream().filter(basketItem -> basketItem.item() == item)
                    .map(BasketItem::quantity).findFirst().orElse(0);

            if (count > 1) {
                return new BigDecimal(count).multiply(discount);
            }

            return BigDecimal.ZERO;
        };
    }
}
