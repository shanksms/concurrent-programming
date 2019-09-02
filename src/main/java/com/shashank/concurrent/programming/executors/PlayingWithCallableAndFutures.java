package com.shashank.concurrent.programming.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class PlayingWithCallableAndFutures {

  public static void main(String[] args) throws ExecutionException,InterruptedException {
    Callable<String> callableTask = () -> {
      return "I am in " + Thread.currentThread().getName();
    };

    ExecutorService executorService = Executors.newFixedThreadPool(4);

    try{
      for (int i = 0; i< 10; i++) {
        Future<String> future = executorService.submit(callableTask);
        System.out.println("I get: " + future.get());
      }

    } finally {
      executorService.shutdown();
    }



  }
}
