package com.wrist.source2sea.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CheckoutController {
    @PostMapping("/checkout")
    BigDecimal checkout(@RequestBody List<String> watchIds) {
        return BigDecimal.ZERO;
    }
}
