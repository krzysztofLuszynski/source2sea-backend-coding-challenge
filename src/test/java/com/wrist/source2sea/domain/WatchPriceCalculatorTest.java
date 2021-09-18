package com.wrist.source2sea.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WatchPriceCalculatorTest {
    private static final Watch ROLEX = new Watch("001", "Rolex", BigDecimal.valueOf(100),
            new Discount(3, BigDecimal.valueOf(200)));

    private static final Watch SWATCH = new Watch("003", "Swatch", BigDecimal.valueOf(50), null);


    private final WatchPriceCalculator watchPriceCalculator = new WatchPriceCalculator();

    @Test
    void calculatePriceQuantityNegative() {
        assertThatThrownBy(() ->
                watchPriceCalculator.calculatePrice(SWATCH, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quantity: -1 must be greater or equal to 0 !");
    }

    @Test
    void calculatePriceQuantityZero() {
        BigDecimal price = watchPriceCalculator.calculatePrice(SWATCH, 0);

        assertThat(price).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void calculatePriceQuantityOneNoDiscount() {
        BigDecimal price = watchPriceCalculator.calculatePrice(SWATCH, 1);

        assertThat(price).isEqualTo(BigDecimal.valueOf(50));
    }

    @Test
    void calculatePriceQuantityFourNoDiscount() {
        BigDecimal price = watchPriceCalculator.calculatePrice(SWATCH, 4);

        assertThat(price).isEqualTo(BigDecimal.valueOf(200));
    }

    @Test
    void calculatePriceQuantityOneDiscount3For200() {
        BigDecimal price = watchPriceCalculator.calculatePrice(ROLEX, 1);

        assertThat(price).isEqualTo(BigDecimal.valueOf(100));
    }

    @Test
    void calculatePriceQuantityTwoDiscount3For200() {
        BigDecimal price = watchPriceCalculator.calculatePrice(ROLEX, 2);

        assertThat(price).isEqualTo(BigDecimal.valueOf(200));
    }

    @Test
    void calculatePriceQuantityThreeDiscount3For200() {
        BigDecimal price = watchPriceCalculator.calculatePrice(ROLEX, 3);

        assertThat(price).isEqualTo(BigDecimal.valueOf(200));
    }

    @Test
    void calculatePriceQuantityFourDiscount3For200() {
        BigDecimal price = watchPriceCalculator.calculatePrice(ROLEX, 4);

        assertThat(price).isEqualTo(BigDecimal.valueOf(300));
    }

    @Test
    void calculatePriceQuantityThirteenDiscount3For200() {
        BigDecimal price = watchPriceCalculator.calculatePrice(ROLEX, 13);

        assertThat(price).isEqualTo(BigDecimal.valueOf(900));
    }

    @Test
    void calculatePriceEmptyMap() {
        BigDecimal price = watchPriceCalculator.calculatePrice(Collections.emptyMap());

        assertThat(price).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void calculatePriceThirteenRolexesOnly() {
        BigDecimal price = watchPriceCalculator.calculatePrice(Map.of(ROLEX, 13));

        assertThat(price).isEqualTo(BigDecimal.valueOf(900));
    }

    @Test
    void calculatePriceZeroRolexesZeroSwatches() {
        BigDecimal price = watchPriceCalculator.calculatePrice(Map.of(ROLEX, 0, SWATCH, 0));

        assertThat(price).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void calculatePriceThirteenRolexesSixSwatches() {
        BigDecimal price = watchPriceCalculator.calculatePrice(Map.of(ROLEX, 13, SWATCH, 6));

        assertThat(price).isEqualTo(BigDecimal.valueOf(1200));
    }
}
