package com.shashank.concurrent.programming.completable.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AsyncExample {

  public static void main(String[] args) {

    Supplier<List<Integer>> idSupplier = () -> {
      sleep(200);
      return Arrays.asList(1, 2, 3);
    };

    Function<List<Integer>, List<User>> userFetcher = (ids) -> {
      sleep(200);
      return ids.stream().map(User::new).collect(Collectors.toList());
    };

    Consumer<List<User>> userDisplayer = (userList) -> {
      userList.forEach(user -> System.out.println(user));
    };

    CompletableFuture.supplyAsync(idSupplier).thenApply(userFetcher).thenAccept(userDisplayer);
    sleep(2000);

  }
  private static void sleep (int sleep) {
    try {
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }



}
