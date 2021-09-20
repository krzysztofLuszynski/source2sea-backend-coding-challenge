package com.wrist.source2sea.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
public class Discount {
    private static final String INVALID_QUANTITY_MESSAGE = "Invalid discount quantity: %d. It must be less than 2 !";
    private static final String INVALID_PRICE_FOR_QUANTITY = "Invalid discount price for quantity: %.2f. It must be greater than 0 !";

    Long quantity;

    @Column(name="price_for_quantity")
    BigDecimal priceForQuantity;

    // Needed for JPA only
    Discount() {
    }

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
