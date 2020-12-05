package com.shashank.concurrent.programming.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstCompletableFuture {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    Runnable task = () -> System.out.println("I am running async");

    CompletableFuture cf = CompletableFuture.runAsync(task, executor);

    executor.shutdown();
  }

}
