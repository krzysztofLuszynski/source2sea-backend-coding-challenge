package com.wrist.source2sea.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WatchTest {
    @Test
    void validDiscountQuantityNull() {
        new Watch("id", "name", BigDecimal.valueOf(100), null, null);
    }

    @Test
    void validDiscountQuantity2() {
        new Watch("id", "name", BigDecimal.valueOf(100), 2, BigDecimal.valueOf(150));
    }

    @Test
    void invalidDiscountQuantity1() {
        assertThatThrownBy(() ->
            new Watch("id", "name", BigDecimal.valueOf(100), 1, BigDecimal.valueOf(150)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid discount quantity: 1. It must be less than 2 !");
    }
}
