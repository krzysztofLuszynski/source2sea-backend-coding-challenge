package com.wrist.source2sea.controller;

import com.wrist.source2sea.domain.Watch;
import com.wrist.source2sea.repository.WatchRepository;
import com.wrist.source2sea.service.WatchPriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

@RestController
public class CheckoutController {
    @Autowired
    private WatchRepository watchRepository;

    @Autowired
    private WatchPriceCalculator watchPriceCalculator;

    @PostMapping("/checkout")
    BigDecimal checkout(@RequestBody List<String> watchIds) {
        Map<String, Long> idToCount = watchIds.stream()
                .collect(groupingBy(identity(), counting()));

        Map<Watch, Long> watchToCount = idToCount.entrySet().stream()
                .collect(toMap(
                        (entry) -> watchRepository.getById(entry.getKey()),
                        Map.Entry::getValue));

        return watchPriceCalculator.calculatePrice(watchToCount);
    }
}
