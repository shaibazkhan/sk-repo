package com.policyexpert.assignment;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal KG_GRAMS = new BigDecimal(1000);

    BigDecimal calculateCost(Item item, int quantity);

    static PricingStrategy weighted() {
        return (item, quantity) -> scaleSet(item.unitPrice().divide(KG_GRAMS).multiply(new BigDecimal(quantity)));
    }

    static PricingStrategy quantified() {
        return (item, quantity) -> item.unitPrice().multiply(new BigDecimal(quantity));
    }

    static BigDecimal scaleSet(BigDecimal result) {
        return result.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
