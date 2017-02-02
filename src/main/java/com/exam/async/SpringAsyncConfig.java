package com.exam.async;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@ComponentScan(basePackages="com.exam.async")
public class SpringAsyncConfig implements AsyncConfigurer {
  
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  
  @Override
  public Executor getAsyncExecutor() {
    logger.info("ThreadPoolTaskExecutor");
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(5);
    taskExecutor.setMaxPoolSize(10);
    taskExecutor.setQueueCapacity(10);
    taskExecutor.initialize();
    
    return taskExecutor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    logger.info("AsyncUncaughtExceptionHandler");
    return new CustomAsyncExcaptionHandler();
  }
  
  public class CustomAsyncExcaptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
      logger.debug("Exception message - {}",arg0.getMessage());
      logger.debug("Method name - {}",arg1.getName());
      for (Object param : arg2) {
        logger.debug("Parameter value - {}",param);
      }
    }
  }

  
}
