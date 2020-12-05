package com.shashank.concurrent.programming.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
  private static AtomicInteger counter = new AtomicInteger(0);


  public static void main(String[] args) throws Exception{

    class Incrementer implements Runnable{

      public void run() {

        for (int i = 0; i < 1_000; i++) {
          counter.incrementAndGet();
        }
      }


    }

    class Decrementer implements Runnable {

      public void run() {

        for (int i = 0; i < 1_000; i++) {
          counter.decrementAndGet();
        }
      }

    }

    ExecutorService executorService = Executors.newFixedThreadPool(8);

    for (int i = 0; i < 4; i++) {
      executorService.submit(new Incrementer());
    }
    for (int i = 0; i < 4; i++) {
      executorService.submit(new Decrementer());
    }
    executorService.shutdown();
    executorService.awaitTermination(5, TimeUnit.MINUTES);
    System.out.println(counter.get());
  }


}
