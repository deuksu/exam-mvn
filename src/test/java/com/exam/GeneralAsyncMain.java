package com.exam;

import java.util.concurrent.Future;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.exam.async.AsyncMethod;
import com.exam.async.SpringAsyncConfig;

public class GeneralAsyncMain {

  public static void main(String[] args) throws Exception {
    
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringAsyncConfig.class);
    AsyncMethod asyncMethod = context.getBean(AsyncMethod.class);
    
    System.out.println(">> AsyncRunner start");
    try {
      
      asyncMethod.asyncWithNoReturnType(10);
      System.out.println(">> AsyncRunner 0");
      
      
/*      Future<String> f1 = asyncMethod.asyncWithReturnType(1,10);
      System.out.println(">> AsyncRunner 1");
      Future<String> f2 = asyncMethod.asyncWithReturnType(2,3);
      System.out.println(">> AsyncRunner 2");
      Future<String> f3 = asyncMethod.asyncWithReturnType(3,7);
      System.out.println(">> AsyncRunner 3");
      Future<String> f4 = asyncMethod.asyncWithReturnType(4,10);
      System.out.println(">> AsyncRunner 4");
      Future<String> f5 = asyncMethod.asyncWithReturnType(5,3);
      System.out.println(">> AsyncRunner 5");
      
      System.out.format(">> AsyncRunner f1 = %s\n", f1.get());
      System.out.format(">> AsyncRunner f2 = %s\n", f2.get());
      System.out.format(">> AsyncRunner f3 = %s\n", f3.get());
      System.out.format(">> AsyncRunner f4 = %s\n", f4.get());
      System.out.format(">> AsyncRunner f5 = %s\n", f5.get());*/
      
      System.out.println(">> AsyncRunner end");
    } catch(Exception ex) {
      ex.printStackTrace();
    } finally {
      context.close();  
    }
    
/*    // 단순 Thread 생성
    new Thread(new Runnable(){
      @Override
      public void run() {
        try {
          System.out.format("runnable %s\n"+Thread.currentThread().getName());
        } catch(Exception ex) {}
      }
    }).start();  */  
    
  }
  
  
  
}
