package com.wrist.source2sea.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class WatchTest {
    @Test
    void validDiscountNull() {
        Watch watch = new Watch("003", "Swatch", BigDecimal.valueOf(50), null);

        assertThat(watch).hasNoNullFieldsOrPropertiesExcept("discount");
    }

    @Test
    void validDiscountNotNull() {
        Discount discount = new Discount(3, BigDecimal.valueOf(200));
        Watch watch = new Watch("001", "Rolex", BigDecimal.valueOf(100), discount);

        assertThat(watch).hasNoNullFieldsOrProperties();
    }
}
