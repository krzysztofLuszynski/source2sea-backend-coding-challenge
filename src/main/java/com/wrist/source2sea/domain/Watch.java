package com.wrist.source2sea.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Watch {
    String id;
    String name;
    BigDecimal unitPrice;
    Discount discount;
}
