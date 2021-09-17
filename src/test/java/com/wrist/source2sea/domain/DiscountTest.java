package com.wrist.source2sea.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DiscountTest {
    @Test
    void invalidQuantity1() {
        assertThatThrownBy(() ->
                new Discount(1, BigDecimal.valueOf(200)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid discount quantity: 1. It must be less than 2 !");
    }

    @Test
    void invalidPriceForQuantityZero() {
        assertThatThrownBy(() ->
                new Discount(2, BigDecimal.ZERO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid discount price for quantity: 0,00. It must be greater than 0 !");
    }

    @Test
    void validQuantity() {
        Discount discount = new Discount(3, BigDecimal.valueOf(200));

        assertThat(discount).hasNoNullFieldsOrProperties();
    }
}
