package com.wrist.source2sea.repository;

import com.wrist.source2sea.domain.Watch;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WatchRepositoryTest {
    private final WatchRepository watchRepository = new WatchRepository();

    @Test
    void getByIdInvalidId() {
        assertThatThrownBy(() ->
                watchRepository.getById("005"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid watch id 005 !");

    }

    @Test
    void getByIdValidId() {
        Watch watch = watchRepository.getById("001");

        assertThat(watch)
                .hasFieldOrPropertyWithValue("id", "001")
                .hasFieldOrPropertyWithValue("name", "Rolex")
                .hasFieldOrPropertyWithValue("unitPrice", BigDecimal.valueOf(100))
                .extracting("discount")
                .hasFieldOrPropertyWithValue("quantity", 3L)
                .hasFieldOrPropertyWithValue("priceForQuantity", BigDecimal.valueOf(200));
    }
}
