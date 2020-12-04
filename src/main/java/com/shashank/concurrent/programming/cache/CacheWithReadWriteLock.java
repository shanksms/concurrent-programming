package com.shashank.concurrent.programming.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheWithReadWriteLock {
  private Map<Long, String> cache = new HashMap<>();
  private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private Lock readLock = readWriteLock.readLock();
  private Lock writeLock = readWriteLock.writeLock();

  public String put(Long key, String value) {
    writeLock.lock();
    try {
      return cache.put(key, value);
    } finally {
      writeLock.unlock();
    }

  }

  public String get(Long key) {
    readLock.lock();
    try {
      return cache.get(key);
    }finally {
      readLock.unlock();
    }
  }

  public static void main(String[] args) {

    CacheWithReadWriteLock cacheWithReadWriteLock = new CacheWithReadWriteLock();

    class Producer implements Callable<String> {
      Random random = new Random();

      public String call() {

        while (true) {
          long next = random.nextInt(1_000);
          cacheWithReadWriteLock.put(next, Long.toString(next));
          if (cacheWithReadWriteLock.get(next) == null) {
            System.out.println("key " + next + " has not be put");
          }
        }
      }

    }

    ExecutorService executorService = Executors.newFixedThreadPool(4);

    try {
      for (int i = 0; i < 4; i++) {
        executorService.submit(new Producer());
      }
    } finally {
      executorService.shutdown();
    }


  }


}
