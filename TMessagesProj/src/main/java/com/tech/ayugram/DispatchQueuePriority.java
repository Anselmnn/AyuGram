package com.tech.ayugram;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;

public class DispatchQueuePriority {
    private final String name;
    private final ExecutorService executor;

    public DispatchQueuePriority(String name) {
        this.name = name;
        this.executor = createExecutor();
    }

    private ExecutorService createExecutor() {
        return new ThreadPoolExecutor(
            1, 1,
            60L, TimeUnit.SECONDS,
            new PriorityBlockingQueue<>(),
            new PriorityThreadFactory(name)
        );
    }

    public void postRunnable(Runnable runnable) {
        executor.execute(runnable);
    }

    public void cancelRunnable(Runnable runnable) {
        // No-op for stub
    }

    public void shutdown() {
        executor.shutdown();
    }

    private static class PriorityThreadFactory implements ThreadFactory {
        private final String name;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        PriorityThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, name + "-" + threadNumber.getAndIncrement());
            t.setDaemon(true);
            return t;
        }
    }
}