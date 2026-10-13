package com.javamastery.concurrency.k04_completable;

import com.javamastery.concurrency.k04_completable.PriceAggregator.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class PriceAggregatorTest {

    private final ExecutorService pool = Executors.newFixedThreadPool(8);

    @AfterEach
    void tearDown() {
        pool.shutdownNow();
    }

    static ShopClient shop(String name, long delayMs, Map<String, String> prices) {
        return new ShopClient() {
            public String name() {
                return name;
            }

            public BigDecimal price(String product) throws Exception {
                Thread.sleep(delayMs);
                String p = prices.get(product);
                if (p == null) throw new Exception(name + " has no " + product);
                return new BigDecimal(p);
            }
        };
    }

    final ShopClient fast = shop("Fast", 50, Map.of("book", "12.00", "pen", "1.50"));
    final ShopClient cheap = shop("Cheap", 300, Map.of("book", "9.99"));
    final ShopClient hanging = shop("Hanging", 10_000, Map.of("book", "1.00", "pen", "0.10"));

    @Test
    void singleShopFailureBecomesEmpty() throws Exception {
        var agg = new PriceAggregator(List.of(fast), pool);
        assertEquals(Optional.of(new Offer("Fast", new BigDecimal("12.00"))), agg.offerFrom(fast, "book", Duration.ofSeconds(1)).get());
        assertEquals(Optional.empty(), agg.offerFrom(fast, "laptop", Duration.ofSeconds(1)).get());
        assertEquals(Optional.empty(), agg.offerFrom(hanging, "book", Duration.ofMillis(200)).get());
    }

    @Test
    void bestOfferIsParallelAndIgnoresSlowShop() throws Exception {
        var agg = new PriceAggregator(List.of(fast, cheap, hanging), pool);
        long start = System.nanoTime();
        Optional<Offer> best = agg.bestOffer("book", Duration.ofMillis(800)).get();
        long ms = (System.nanoTime() - start) / 1_000_000;
        assertEquals(Optional.of(new Offer("Cheap", new BigDecimal("9.99"))), best);
        assertTrue(ms < 1500, "запросы должны идти параллельно и упираться в таймаут: " + ms + "ms");
    }

    @Test
    void basket() throws Exception {
        var agg = new PriceAggregator(List.of(fast, cheap), pool);
        assertEquals(Optional.of(new BigDecimal("11.49")), agg.basketPrice("book", "pen", Duration.ofSeconds(2)).get());
        assertEquals(Optional.empty(), agg.basketPrice("book", "laptop", Duration.ofSeconds(2)).get());
    }

    @Test
    void compose() throws Exception {
        var agg = new PriceAggregator(List.of(fast, cheap), pool);
        var r = agg.bestOfferForAlternative("notebook", p -> CompletableFuture.supplyAsync(() -> "book", pool), Duration.ofSeconds(2)).get();
        assertEquals("Cheap", r.orElseThrow().shop());
    }
}
