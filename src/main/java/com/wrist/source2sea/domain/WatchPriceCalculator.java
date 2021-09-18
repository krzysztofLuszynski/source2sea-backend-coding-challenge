package com.wrist.source2sea.domain;

import java.math.BigDecimal;

public class WatchPriceCalculator {
    private static final String QUANTITY_LESS_THEN_ZERO_MESSAGE = "Quantity: %d must be greater or equal to 0 !";

    BigDecimal calculatePrice(Watch watch, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(String.format(QUANTITY_LESS_THEN_ZERO_MESSAGE, quantity));
        }

        Discount discount = watch.getDiscount();
        if (discount == null) {
            return watch.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
        } else {
            int normalPriceNumber = quantity % discount.getQuantity();
            int discountedPriceNumber = quantity / discount.getQuantity();

            BigDecimal notDiscountedPartOfPrice = watch.getUnitPrice().multiply(BigDecimal.valueOf(normalPriceNumber));
            BigDecimal discountedPartOfPrice = discount.getPriceForQuantity().multiply(BigDecimal.valueOf(discountedPriceNumber));

            return notDiscountedPartOfPrice.add(discountedPartOfPrice);
        }
    }
}
