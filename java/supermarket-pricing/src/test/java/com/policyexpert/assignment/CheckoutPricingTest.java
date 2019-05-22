package com.policyexpert.assignment;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.math.BigDecimal;

import static com.policyexpert.assignment.Item.Beans;
import static com.policyexpert.assignment.Item.Coke;
import static com.policyexpert.assignment.Item.Oranges;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CheckoutPricingTest {

    private CheckoutPricing pricing = new CheckoutPricing();

    @Test
    public void priceForBeans() {
        BigDecimal price = pricing.totalPrice(ImmutableList.of(new BasketItem(Beans, 1)));
        assertThat(price, is(new BigDecimal("0.50")));
    }

    @Test
    public void priceForCoke() {
        BigDecimal price = pricing.totalPrice(ImmutableList.of(new BasketItem(Coke, 1)));
        assertThat(price, is(new BigDecimal("0.70")));
    }

    @Test
    public void priceForWeightedItem() {
        BigDecimal price = pricing.totalPrice(ImmutableList.of(new BasketItem(Oranges, 200)));
        assertThat(price, is(new BigDecimal("0.40")));
    }

    @Test
    public void priceForMultipleItems() {
        BigDecimal price = pricing.totalPrice(
                ImmutableList.of(new BasketItem(Beans, 2), new BasketItem(Coke, 1))
        );
        assertThat(price, is(new BigDecimal("1.70")));
    }
}