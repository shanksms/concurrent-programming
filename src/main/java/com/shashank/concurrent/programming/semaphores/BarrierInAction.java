package com.shashank.concurrent.programming.semaphores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BarrierInAction {

  public static void main(String[] args) {
    class Friend implements Callable<String>  {
      private CyclicBarrier barrier;

      public Friend(CyclicBarrier barrier) {
        this.barrier = barrier;
      }


      public String call() throws Exception {
        Random random = new Random();
        int sleepTime  = random.nextInt(10000);
        //System.out.println("sleeping for "  + sleepTime);
        Thread.sleep(sleepTime);
        System.out.println("i just arrived. waiting");
        barrier.await();
        System.out.println("Lets go");
        return "Ok";
      }



    }
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    CyclicBarrier barrier = new CyclicBarrier(4);
    List<Future<String>> futures = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      futures.add(executorService.submit(new Friend(barrier)));
    }

    futures.forEach((f) -> {
      try {
        System.out.println(f.get());

      } catch (Exception e) {

      }
    });

    executorService.shutdown();
  }

}
