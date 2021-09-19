package com.wrist.source2sea.service;

import com.wrist.source2sea.domain.Discount;
import com.wrist.source2sea.domain.Watch;

import java.math.BigDecimal;
import java.util.Map;

public class WatchPriceCalculator {
    private static final String QUANTITY_LESS_THEN_ZERO_MESSAGE = "Quantity: %d must be greater or equal to 0 !";

    public BigDecimal calculatePrice(Map<Watch, Integer> watchToQuantity) {
        return watchToQuantity.entrySet().stream()
            .map((entry) -> calculatePrice(entry.getKey(), entry.getValue()))
            .reduce(BigDecimal.ZERO, (firstPrice, secondPrice) -> firstPrice.add(secondPrice));
    }

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
