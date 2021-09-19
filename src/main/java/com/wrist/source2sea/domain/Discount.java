package com.wrist.source2sea.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Discount {
    private static final String INVALID_QUANTITY_MESSAGE = "Invalid discount quantity: %d. It must be less than 2 !";
    private static final String INVALID_PRICE_FOR_QUANTITY = "Invalid discount price for quantity: %.2f. It must be greater than 0 !";

    Long quantity;
    BigDecimal priceForQuantity;

    public Discount(Long quantity, BigDecimal priceForQuantity) {
        assertDiscountQuantity(quantity);
        assertDiscountPriceForQuantity(priceForQuantity);

        this.quantity = quantity;
        this.priceForQuantity = priceForQuantity;
    }

    private void assertDiscountQuantity(Long quantity) {
        if (quantity < 2) {
            throw new IllegalArgumentException(String.format(INVALID_QUANTITY_MESSAGE, quantity));
        }
    }

    private void assertDiscountPriceForQuantity(BigDecimal priceForQuantity) {
        if (priceForQuantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(String.format(INVALID_PRICE_FOR_QUANTITY, priceForQuantity.doubleValue()));
        }
    }
}
