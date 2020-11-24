package com.meyoustu.amuse.gous.lang;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Runtime.getRuntime;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/18 15:49
 */
public final class ThreadPool {

    private static ThreadPool threadPool;

    private static ThreadPool getInstance() {
        if (threadPool == null) {
            synchronized (ThreadPool.class) {
                threadPool = (threadPool == null) ?
                        new ThreadPool() :
                        threadPool;
            }
        }
        return threadPool;
    }

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            getRuntime().availableProcessors(),
            getRuntime().availableProcessors(),
            0,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            createThreadFactory()
    );


    public synchronized static ThreadPoolExecutor create() {
        return getInstance().executor;
    }

    public synchronized static void run(Runnable task) {
        create().execute(task);
    }


    public static java.util.concurrent.ThreadFactory createThreadFactory() {
        return new ThreadFactory();
    }

    private static final class ThreadFactory implements java.util.concurrent.ThreadFactory {
        private final AtomicInteger atomicInteger = new AtomicInteger(1);
        private String threadNameBeginWith = com.meyoustu.amuse.gous.util.Constants.bytesToString(
                (byte) 'A', (byte) 'M', (byte) 'U', (byte) 'S', (byte) 'E',
                (byte) '_', (byte) 'T', (byte) 'H', (byte) 'R', (byte) 'E',
                (byte) 'A', (byte) 'D', (byte) '_'
        );


        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, threadNameBeginWith + atomicInteger.getAndIncrement() +
                    com.meyoustu.amuse.gous.util.Constants.bytesToString(
                            (byte) '_', (byte) 'W', (byte) 'O', (byte) 'R', (byte) 'K',
                            (byte) 'I', (byte) 'N', (byte) 'G'
                    ));
        }
    }

}
