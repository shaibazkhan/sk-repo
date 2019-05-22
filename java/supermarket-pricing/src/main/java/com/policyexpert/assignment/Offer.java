package com.policyexpert.assignment;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;

public interface Offer {

    BigDecimal apply(ImmutableList<BasketItem> items);

    static Offer threeForTwoOfferOn(Item item) {
        return (items) -> {
            long count = items
                    .stream().filter(basketItem -> basketItem.item() == item)
                    .map(BasketItem::quantity).findAny().orElse(0);

            return new BigDecimal(count/3).multiply(item.unitPrice());
        };
    }
}
