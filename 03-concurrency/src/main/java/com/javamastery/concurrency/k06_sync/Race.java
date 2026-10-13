package com.javamastery.concurrency.k06_sync;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * K06 · CountDownLatch: «стартовый пистолет».
 *
 * <p>Запустить каждого участника в своём потоке так, чтобы все они начали выполнять call() ОДНОВРЕМЕННО:
 * потоки создаются и ждут на latch, потом один countDown — и все стартуют. Второй latch — дождаться финиша всех.
 * Вернуть имена в порядке финиша (кто первый вернулся из call()). Участник, бросивший исключение, в результат не попадает.
 */
public final class Race {
    private Race() {}

    public record Runner(String name, Callable<Void> run) {}

    public static List<String> start(List<Runner> runners) throws InterruptedException {
        throw new UnsupportedOperationException("TODO");
    }
}
