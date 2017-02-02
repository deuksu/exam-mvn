package com.exam.runner;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.exam.async.AsyncMethod;

@Component
public class AsyncRunner implements CommandLineRunner {
  
  private final static Logger logger = LoggerFactory.getLogger(AsyncRunner.class);
  
  @Autowired AsyncMethod asynMethod;
  
  @Override
  public void run(String... args) throws Exception {
    logger.debug(">> AsyncRunner start");
    
    try {
      asynMethod.asyncWithNoReturnType();
      Future<String> f = asynMethod.asyncWithReturnType();
      while(!f.isDone()) {
        logger.debug(">> AsyncRunner future<String> = {}", f.get());
      }
    } catch(Exception ex) {
      ex.printStackTrace();
    }
    
    logger.debug(">> AsyncRunner end");
  }

}
