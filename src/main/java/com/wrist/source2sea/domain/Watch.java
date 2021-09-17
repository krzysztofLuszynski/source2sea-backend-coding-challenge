package com.wrist.source2sea.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Watch {
    String id;
    String name;
    BigDecimal unitPrice;
    Integer discountQuantity;
    BigDecimal discountPriceForQuantity;

    public Watch(String id, String name, BigDecimal unitPrice,
                 Integer discountQuantity, BigDecimal discountPriceForQuantity) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;

        if (discountQuantity != null && discountQuantity < 2) {
            throw new IllegalArgumentException(String.format("Invalid discount quantity: %d. It must be less than 2 !", discountQuantity));
        }
        this.discountQuantity = discountQuantity;
        this.discountPriceForQuantity = discountPriceForQuantity;
    }
}
