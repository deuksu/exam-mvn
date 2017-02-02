package com.exam.async;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component(value="com.exam.async.AsynMethod")
public class AsyncMethod {
  
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Async
  public void asyncWithNoReturnType() throws Exception {
    this.asyncWithNoReturnType(0);
  }
  
  @Async
  public void asyncWithNoReturnType(int sleeping) throws Exception {
    try {
        Thread.sleep(sleeping*1000);
        logger.info(">> AsynMethod.asyncWithNoReturnType = {}",Thread.currentThread().getName());
    } catch (InterruptedException e) {
        throw e;
    }
  }
  
  @Async
  public Future<String> asyncWithReturnType(int order, int sleeping) throws Exception {
    logger.info(">> AsynMethod.asyncWithReturnType = {}",Thread.currentThread().getName());
    try {
        Thread.sleep(sleeping*1000);
        return new AsyncResult<String>(order + ". hello world !!!!");
    } catch (InterruptedException e) {
        throw e;
    }
//    return null;
  }
  
  @Async
  public Future<String> asyncWithReturnType(int sleeping) throws Exception {
    return this.asyncWithReturnType(1, sleeping);
  }
  
  @Async
  public Future<String> asyncWithReturnType() throws Exception {
    return this.asyncWithReturnType(1, 10);
  }  
}
