package com.foodapp.deliveryexecutive.logging.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Configuration for asynchronous logging operations.
 * Uses a dedicated thread pool to avoid blocking business logic.
 */
@Configuration
@EnableAsync
public class AsyncLoggingConfig implements AsyncConfigurer {

    @Value("${logging.async.core-pool-size:2}")
    private int corePoolSize;

    @Value("${logging.async.max-pool-size:10}")
    private int maxPoolSize;

    @Value("${logging.async.queue-capacity:5000}")
    private int queueCapacity;

    @Value("${logging.async.thread-name-prefix:AsyncLog-}")
    private String threadNamePrefix;

    /**
     * Creates a dedicated executor for logging operations.
     * This ensures logging doesn't block the main application threads.
     */
    @Bean(name = "loggingTaskExecutor")
    public Executor loggingTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }

    /**
     * Creates an executor specifically for audit log operations.
     * Audit logs have higher priority and different queue settings.
     */
    @Bean(name = "auditLoggingExecutor")
    public Executor auditLoggingExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(10000);
        executor.setThreadNamePrefix("AuditLog-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(120);
        executor.initialize();
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return loggingTaskExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new LoggingAsyncExceptionHandler();
    }
}
