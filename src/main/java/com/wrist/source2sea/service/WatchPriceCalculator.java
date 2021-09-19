package com.wrist.source2sea.service;

import com.wrist.source2sea.domain.Discount;
import com.wrist.source2sea.domain.Watch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Service
public class WatchPriceCalculator {
    private static final String QUANTITY_LESS_THEN_ZERO_MESSAGE = "Quantity: %d must be greater or equal to 0 !";

    public BigDecimal calculatePrice(Map<Watch, Long> watchToQuantity) {
        return watchToQuantity.entrySet().stream()
            .map((entry) -> calculatePrice(entry.getKey(), entry.getValue()))
            .reduce(BigDecimal.ZERO, (firstPrice, secondPrice) -> firstPrice.add(secondPrice));
    }

    BigDecimal calculatePrice(Watch watch, long quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(String.format(QUANTITY_LESS_THEN_ZERO_MESSAGE, quantity));
        }

        Discount discount = watch.getDiscount();
        if (discount == null) {
            return watch.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
        } else {
            long normalPriceNumber = quantity % discount.getQuantity();
            long discountedPriceNumber = quantity / discount.getQuantity();

            log.debug("Discount for watch {} - normalPriceNumber: {}, discoutedPriceNumber: {}",
                    watch, normalPriceNumber, discountedPriceNumber);

            BigDecimal notDiscountedPartOfPrice = watch.getUnitPrice().multiply(BigDecimal.valueOf(normalPriceNumber));
            BigDecimal discountedPartOfPrice = discount.getPriceForQuantity().multiply(BigDecimal.valueOf(discountedPriceNumber));

            return notDiscountedPartOfPrice.add(discountedPartOfPrice);
        }
    }
}
