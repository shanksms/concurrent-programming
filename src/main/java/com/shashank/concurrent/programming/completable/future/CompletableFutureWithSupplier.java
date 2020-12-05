package com.shashank.concurrent.programming.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CompletableFutureWithSupplier {

  public static void main(String[] args) {
    Supplier<String> supplier = () -> Thread.currentThread().getName();

    CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(supplier);
    String result = stringCompletableFuture.join();

    System.out.println(result);
  }

}
