package com.shashank.concurrent.programming.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class PlayingWithExecutorsAndRunnable {

  public static void main(String[] args) {
    Runnable runnable = () -> System.out.println("i am in " + Thread.currentThread().getName());
    //ExecutorService executorService = Executors.newSingleThreadExecutor();
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    IntStream.range(0, 10).forEach(i -> executorService.submit(runnable));
    executorService.shutdown();
  }

}
