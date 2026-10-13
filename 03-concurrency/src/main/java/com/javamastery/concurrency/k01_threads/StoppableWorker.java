package com.javamastery.concurrency.k01_threads;

/**
 * K01 · volatile и видимость (visibility).
 *
 * <p>Воркер в отдельном потоке крутит цикл и увеличивает счётчик итераций, пока его не остановят.
 * {@code stop()} вызывается из ДРУГОГО потока — без volatile JIT может «закэшировать» флаг, и поток не остановится никогда.
 * Не используй Thread.interrupt — именно флаг.
 */
public class StoppableWorker implements Runnable {
    // TODO: флаг и счётчик

    @Override
    public void run() {
        throw new UnsupportedOperationException("TODO");
    }

    public void stop() {
        throw new UnsupportedOperationException("TODO");
    }

    public long iterations() {
        throw new UnsupportedOperationException("TODO");
    }
}
