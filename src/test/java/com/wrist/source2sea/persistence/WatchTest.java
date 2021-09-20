package com.wrist.source2sea.persistence;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class WatchTest {
    @Test
    void validDiscountNull() {
        Watch watch = new Watch("Swatch", BigDecimal.valueOf(50), null);

        assertThat(watch).hasNoNullFieldsOrPropertiesExcept("id", "discount");
    }

    @Test
    void validDiscountNotNull() {
        Discount discount = new Discount(3L, BigDecimal.valueOf(200));
        Watch watch = new Watch("Rolex", BigDecimal.valueOf(100), discount);

        assertThat(watch).hasNoNullFieldsOrPropertiesExcept("id");
    }
}
