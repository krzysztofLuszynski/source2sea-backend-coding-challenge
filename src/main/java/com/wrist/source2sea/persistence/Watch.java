package com.wrist.source2sea.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@ToString
@EqualsAndHashCode
public class Watch {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name="unit_price")
    private BigDecimal unitPrice;

    @Embedded
    private Discount discount;

    // Needed for JPA only
    Watch() {
    }

    public Watch(String name, BigDecimal unitPrice, Discount discount) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }
}
