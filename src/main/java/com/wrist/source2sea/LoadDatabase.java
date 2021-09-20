package com.wrist.source2sea;

import com.wrist.source2sea.persistence.Discount;
import com.wrist.source2sea.persistence.Watch;
import com.wrist.source2sea.repository.WatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Slf4j
@Configuration
class LoadDatabase {
    private static final Watch ROLEX = new Watch("Rolex", BigDecimal.valueOf(100),
            new Discount(3L, BigDecimal.valueOf(200)));
    private static final Watch MICHAEL_KORS = new Watch("Michael Kors", BigDecimal.valueOf(80),
            new Discount(2L, BigDecimal.valueOf(120)));
    private static final Watch SWATCH = new Watch("Swatch", BigDecimal.valueOf(50), null);
    private static final Watch CASIO = new Watch("Casio", BigDecimal.valueOf(30), null);

    @Bean
    CommandLineRunner initDatabase(WatchRepository watchRepository) {

        return args -> {
            log.info("Preloading " + watchRepository.save(ROLEX));
            log.info("Preloading " + watchRepository.save(MICHAEL_KORS));
            log.info("Preloading " + watchRepository.save(SWATCH));
            log.info("Preloading " + watchRepository.save(CASIO));
        };
    }
}