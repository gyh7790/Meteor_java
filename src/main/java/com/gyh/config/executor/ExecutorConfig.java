package com.gyh.config.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 配置 创建线程池的
 * @author gyh
 * @Date 2020/10/9 21:19
 */
@Configuration
public class ExecutorConfig {

    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 10 + 1;
    public static String COMPUTE_BATCH_BILL_THREAD_NAME = "compute-batch-bill-thread-pool-%d";
    public static String COMPUTE_SINGLE_BILL_THREAD_NAME = "compute-single-bill-thread-pool-%d";
    public static int MAX_POOL_SIZE = 400;
    public static int QUEUE_SIZE = 4800;


    @Bean(value = "buildThreadPool")
    public static ExecutorService buildThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(COMPUTE_BATCH_BILL_THREAD_NAME).build();

        /**
         * 1. CallerRunsPolicy ：    这个策略重试添加当前的任务，他会自动重复调用 execute() 方法，直到成功。
         2. AbortPolicy ：         对拒绝任务抛弃处理，并且抛出异常。
         3. DiscardPolicy ：       对拒绝任务直接无声抛弃，没有异常信息。
         4. DiscardOldestPolicy ： 对拒绝任务不抛弃，而是抛弃队列里面等待最久的一个线程，然后把拒绝任务加到队列。
         */
        ExecutorService threadPool = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_SIZE),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        return threadPool;
    }

}
