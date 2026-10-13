package com.javamastery.concurrency.k04_completable;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * K04 · CompletableFuture: supplyAsync, thenApply, thenCompose, thenCombine, allOf, orTimeout, exceptionally.
 *
 * <p>Агрегатор цен: опрашивает несколько магазинов параллельно.
 */
public class PriceAggregator {

    /** Клиент магазина — медленный и иногда падает. */
    public interface ShopClient {
        String name();

        BigDecimal price(String product) throws Exception;
    }

    public record Offer(String shop, BigDecimal price) {}

    private final List<ShopClient> shops;
    private final Executor executor;

    public PriceAggregator(List<ShopClient> shops, Executor executor) {
        this.shops = shops;
        this.executor = executor;
    }

    /**
     * Запросить цену у одного магазина асинхронно (в executor). Если магазин бросил исключение
     * или не ответил за timeout — результат Optional.empty() (а не исключение).
     */
    public CompletableFuture<Optional<Offer>> offerFrom(ShopClient shop, String product, Duration timeout) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Опросить ВСЕ магазины параллельно, дождаться всех (allOf) и вернуть лучшее (минимальное) предложение.
     * Общее время ≈ время самого медленного (но не больше timeout), а не сумма.
     */
    public CompletableFuture<Optional<Offer>> bestOffer(String product, Duration timeout) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Цена корзины = сумма лучших цен двух товаров, посчитанная параллельно (thenCombine).
     * Если хоть для одного товара нет предложений — Optional.empty().
     */
    public CompletableFuture<Optional<BigDecimal>> basketPrice(String p1, String p2, Duration timeout) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Последовательная зависимость: сначала асинхронно узнать «аналог» товара через lookup (тоже асинхронный),
     * затем найти лучшую цену на аналог. thenCompose (а не thenApply — почему?).
     */
    public CompletableFuture<Optional<Offer>> bestOfferForAlternative(String product,
                                                                      java.util.function.Function<String, CompletableFuture<String>> lookup,
                                                                      Duration timeout) {
        throw new UnsupportedOperationException("TODO");
    }
}
