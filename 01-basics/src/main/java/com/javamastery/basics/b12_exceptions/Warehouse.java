package com.javamastery.basics.b12_exceptions;

import java.util.Map;

/**
 * B12 · Работа с исключениями: checked vs unchecked, атомарность операций.
 *
 * <ul>
 *   <li>add(sku, qty): qty &lt;= 0 → IllegalArgumentException (unchecked — ошибка программиста);</li>
 *   <li>take(sku, qty): не хватает → InsufficientStockException (checked — ожидаемая бизнес-ситуация);</li>
 *   <li>takeAll(order): заказ из нескольких позиций — ВСЁ ИЛИ НИЧЕГО. Если хоть одной позиции не хватает,
 *       склад должен остаться в исходном состоянии, а исключение — про первую (в порядке итерации order) нехватку.</li>
 *   <li>stock(sku): текущий остаток (0 для неизвестного).</li>
 * </ul>
 */
public class Warehouse {
    // TODO: хранилище остатков

    public void add(String sku, int qty) {
        throw new UnsupportedOperationException("TODO");
    }

    public void take(String sku, int qty) throws InsufficientStockException {
        throw new UnsupportedOperationException("TODO");
    }

    public void takeAll(java.util.LinkedHashMap<String, Integer> order) throws InsufficientStockException {
        throw new UnsupportedOperationException("TODO");
    }

    public int stock(String sku) {
        throw new UnsupportedOperationException("TODO");
    }
}
