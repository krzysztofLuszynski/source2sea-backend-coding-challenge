package com.wrist.source2sea.repository;

import com.wrist.source2sea.domain.Discount;
import com.wrist.source2sea.domain.Watch;

import java.math.BigDecimal;
import java.util.Map;

public class WatchRepository {
    private static final String INVALID_WATCH_ID_MESSAGE = "Invalid watch id %s !";

    private static final Watch ROLEX = new Watch("001", "Rolex", BigDecimal.valueOf(100),
            new Discount(3, BigDecimal.valueOf(200)));
    private static final Watch MICHAEL_KORS = new Watch("002", "Michael Kors", BigDecimal.valueOf(80),
            new Discount(3, BigDecimal.valueOf(120)));
    private static final Watch SWATCH = new Watch("003", "Michael Kors", BigDecimal.valueOf(50), null);
    private static final Watch CASIO = new Watch("004", "Casio", BigDecimal.valueOf(30), null);

    private static final Map<String, Watch> WATCHES = Map.of(
            ROLEX.getId(), ROLEX,
            MICHAEL_KORS.getId() ,MICHAEL_KORS,
            SWATCH.getId(), SWATCH,
            CASIO.getId(), CASIO
    );

    public Watch getById(String id) {
        if (WATCHES.containsKey(id)) {
            return WATCHES.get(id);
        } else {
            throw new IllegalArgumentException(String.format(INVALID_WATCH_ID_MESSAGE, id));
        }
    }
}
