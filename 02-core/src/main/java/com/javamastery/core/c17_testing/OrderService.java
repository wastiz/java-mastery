package com.javamastery.core.c17_testing;

/**
 * C17 · Тестируемый дизайн: зависимости — через конструктор и интерфейсы (порты).
 *
 * <p>Тесты к этому классу написаны на «ручных» фейках. Задание из Notion: после того как пройдут тесты,
 * перепиши {@code OrderServiceTest} на Mockito (when/thenReturn, verify, ArgumentCaptor, InOrder).
 *
 * <p>Логика placeOrder(customerEmail, sku, qty):
 * <ol>
 *   <li>qty &lt;= 0 → IllegalArgumentException, никого не вызываем;</li>
 *   <li>inventory.reserve(sku, qty) — false → вернуть Result.OUT_OF_STOCK (платёж не вызывается);</li>
 *   <li>цена = pricing.priceCents(sku) * qty;</li>
 *   <li>payments.charge(customerEmail, цена) — бросил PaymentException → inventory.release(sku, qty), вернуть Result.PAYMENT_FAILED;</li>
 *   <li>успех → notifier.send(customerEmail, "Order confirmed: " + sku + " x" + qty), вернуть Result.OK;</li>
 *   <li>если notifier упал (RuntimeException) — заказ всё равно OK (уведомление не критично).</li>
 * </ol>
 */
public class OrderService {

    public enum Result { OK, OUT_OF_STOCK, PAYMENT_FAILED }

    public interface Inventory {
        boolean reserve(String sku, int qty);

        void release(String sku, int qty);
    }

    public interface Pricing {
        long priceCents(String sku);
    }

    public static class PaymentException extends Exception {
        public PaymentException(String msg) {
            super(msg);
        }
    }

    public interface Payments {
        void charge(String email, long cents) throws PaymentException;
    }

    public interface Notifier {
        void send(String email, String text);
    }

    private final Inventory inventory;
    private final Pricing pricing;
    private final Payments payments;
    private final Notifier notifier;

    public OrderService(Inventory inventory, Pricing pricing, Payments payments, Notifier notifier) {
        this.inventory = inventory;
        this.pricing = pricing;
        this.payments = payments;
        this.notifier = notifier;
    }

    public Result placeOrder(String customerEmail, String sku, int qty) {
        throw new UnsupportedOperationException("TODO");
    }
}
