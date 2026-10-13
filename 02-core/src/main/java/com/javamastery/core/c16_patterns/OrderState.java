package com.javamastery.core.c16_patterns;

/**
 * C16 · State + Strategy через enum и sealed-иерархию.
 *
 * <p>Жизненный цикл заказа: NEW → PAID → SHIPPED → DELIVERED; из NEW и PAID можно в CANCELLED.
 * Любой другой переход → IllegalStateException("Cannot go from X to Y").
 * Отмена оплаченного заказа должна вернуть флаг refundRequired() == true.
 */
public final class OrderState {
    private OrderState() {}

    public enum Status { NEW, PAID, SHIPPED, DELIVERED, CANCELLED }

    public static final class Order {
        private Status status = Status.NEW;
        private boolean refund;

        public Status status() {
            return status;
        }

        public boolean refundRequired() {
            return refund;
        }

        public void moveTo(Status next) {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
