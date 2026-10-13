package com.javamastery.basics.b11_records;

/**
 * B11 · Pattern matching для instanceof и switch по sealed-иерархии с guard'ами.
 */
public final class Shipping {
    private Shipping() {}

    public sealed interface Parcel permits Letter, Box, Pallet {}

    public record Letter(int grams) implements Parcel {}

    public record Box(int grams, int widthCm, int heightCm, int depthCm) implements Parcel {}

    public record Pallet(int kg, boolean fragile) implements Parcel {}

    /**
     * Стоимость доставки в центах:
     * <ul>
     *   <li>Letter: до 20 г включительно — 150; тяжелее — 300;</li>
     *   <li>Box: 500 + 100 за каждый начатый килограмм; если любая сторона &gt; 100 см — ещё +2000 (негабарит);</li>
     *   <li>Pallet: 5000 + 50 за кг; fragile — итог × 2.</li>
     * </ul>
     * Используй switch с record patterns и {@code when}.
     */
    public static long cost(Parcel p) {
        throw new UnsupportedOperationException("TODO");
    }
}
