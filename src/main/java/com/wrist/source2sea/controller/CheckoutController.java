package com.wrist.source2sea.controller;

import com.wrist.source2sea.domain.Watch;
import com.wrist.source2sea.repository.WatchRepository;
import com.wrist.source2sea.service.WatchPriceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

@Slf4j
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

        log.debug("IdToCount: {}", idToCount);

        try {
            Map<Watch, Long> watchToCount = idToCount.entrySet().stream()
                    .collect(toMap(
                            (entry) -> watchRepository.getById(entry.getKey()),
                            Map.Entry::getValue));

            return watchPriceCalculator.calculatePrice(watchToCount);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }
}
