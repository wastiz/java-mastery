package com.javamastery.basics.b08_interfaces;

/**
 * B08 · Функциональный интерфейс + default и static методы.
 *
 * <p>Скидка — функция «цена в центах → цена со скидкой в центах». Цена после скидки никогда не меньше 0.
 */
@FunctionalInterface
public interface Discount {

    long apply(long priceCents);

    /** Сначала применить эту скидку, потом next. Реализуй как default-метод. */
    default Discount andThen(Discount next) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Никакой скидки. */
    static Discount none() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Процентная скидка (0..100), округление результата вниз до цента. Процент вне диапазона → IllegalArgumentException. */
    static Discount percent(int percent) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Фиксированная скидка в центах, цена не уходит ниже 0. */
    static Discount fixed(long cents) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Скидка применяется, только если цена &gt;= threshold, иначе цена без изменений. */
    static Discount ifAtLeast(long thresholdCents, Discount discount) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Из нескольких скидок выбрать самую выгодную для покупателя (наименьшую итоговую цену). */
    static Discount best(Discount... discounts) {
        throw new UnsupportedOperationException("TODO");
    }
}
