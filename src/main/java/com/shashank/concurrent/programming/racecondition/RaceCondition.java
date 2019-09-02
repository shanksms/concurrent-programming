package com.shashank.concurrent.programming.racecondition;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RaceCondition {

  public static void main(String[] args) {

    LongWrapper longWrapper = new LongWrapper(0l);

    Runnable runnable = () -> {
      IntStream.range(0, 1_000).forEach(i -> longWrapper.incrementValue() );
    };
    Thread [] threads = new Thread[1_000];

    IntStream.range(0, 1_000).forEach(i -> {
      threads[i] = new Thread(runnable);
      threads[i].start();
    });

    Arrays.stream(threads).forEach(t ->{
      try {
       t.join();
      } catch (Exception e){
        e.printStackTrace();
      }
    } );

    System.out.println(longWrapper.getValue());

  }

}
