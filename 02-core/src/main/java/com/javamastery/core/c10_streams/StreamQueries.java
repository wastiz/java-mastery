package com.javamastery.core.c10_streams;

import com.javamastery.core.c10_streams.Shop.*;

import java.math.BigDecimal;
import java.time.Month;
import java.util.*;

/**
 * C10 · Stream API. Каждый метод — ОДНА цепочка стримов (без циклов for/while).
 * «Отменённые» (CANCELLED) заказы учитываются только там, где это явно сказано.
 */
public final class StreamQueries {
    private StreamQueries() {}

    /** Имена клиентов из города city, по алфавиту, без повторов. */
    public static List<String> customerNamesIn(List<Order> orders, String city) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Выручка (сумма total) по всем НЕотменённым заказам. */
    public static BigDecimal revenue(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Количество заказов по статусам. В карте должны быть ВСЕ статусы (даже с 0) — EnumMap. */
    public static Map<Status, Long> countByStatus(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Самый дорогой заказ (по total) среди неотменённых; Optional.empty(), если таких нет. */
    public static Optional<Order> mostExpensive(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Сколько штук каждого товара (по имени) продано в неотменённых заказах. TreeMap (по имени). Подсказка: flatMap. */
    public static Map<String, Integer> unitsSoldByProduct(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Выручка по категориям (неотменённые). */
    public static Map<Category, BigDecimal> revenueByCategory(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Топ-n клиентов по сумме покупок (неотменённые), по убыванию суммы; при равенстве — по имени. Вернуть имена. */
    public static List<String> topCustomers(List<Order> orders, int n) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Разделить заказы на «крупные» (total &gt;= threshold → true) и остальные; значения — id заказов. partitioningBy. */
    public static Map<Boolean, List<Long>> partitionBySize(List<Order> orders, BigDecimal threshold) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Выручка по месяцам (неотменённые) в порядке месяцев (TreeMap&lt;Month, ...&gt;). */
    public static Map<Month, BigDecimal> revenueByMonth(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Для каждого города — множество категорий, которые там покупали (все заказы, включая отменённые). */
    public static Map<String, Set<Category>> categoriesByCity(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Строка-отчёт: имена товаров из заказа orderId через ", " в квадратных скобках: "[Book, Mouse]". Нет заказа → "[]". Collectors.joining. */
    public static String productNames(List<Order> orders, long orderId) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Статистика по количеству позиций (lines.size()) в заказе: IntSummaryStatistics. */
    public static IntSummaryStatistics linesStats(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Есть ли хоть один клиент tier 3, у которого ВСЕ заказы отменены? (anyMatch + allMatch) */
    public static boolean anyVipWithOnlyCancelled(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Первые n чисел, которые являются квадратами и делятся на 3: 9, 36, 81... Бесконечный стрим (Stream.iterate / IntStream). */
    public static List<Integer> squaresDivisibleBy3(int n) {
        throw new UnsupportedOperationException("TODO");
    }
}
