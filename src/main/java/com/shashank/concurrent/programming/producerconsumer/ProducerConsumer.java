package com.shashank.concurrent.programming.producerconsumer;

import java.util.stream.IntStream;

public class ProducerConsumer {
  private Object lock = new Object();
  private int size;
  private int count = -1;
  private int[] resourceBuffer;

  public int getCount() {
    return count;
  }
  public ProducerConsumer(int size) {
    this.size = size;
    resourceBuffer = new int[this.size];
  }

  public void produce() throws Exception{
    synchronized (lock) {
      while (this.isFull()) {
        lock.wait();
      }
      count++;
      resourceBuffer[count] = 1;
      lock.notify();
    }
  }
  public void consume() throws Exception{
    synchronized (lock) {
      while (this.isEmpty()) {
        lock.wait();
      }
      resourceBuffer[count] = 0;
      count -= 1;
      lock.notify();
    }
  }

  private boolean isFull() {
    return count == size - 1;
  }

  private boolean isEmpty() {
    return count == -1;
  }

  public static void main(String[] args) throws Exception{
    ProducerConsumer producerConsumer = new ProducerConsumer(10);


    Runnable producerTask = () -> {
      IntStream.range(0, 50).forEach(i -> {
        try {
          producerConsumer.produce();
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    } ;

    Runnable consumerTask = () -> {
      IntStream.range(0, 50).forEach(i -> {
        try {
          producerConsumer.consume();
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    } ;

    Thread producerThread = new Thread(producerTask);
    Thread consumerThread = new Thread(consumerTask);

    producerThread.start();
    consumerThread.start();
    producerThread.join(); consumerThread.join();
    System.out.println(producerConsumer.count);
  }

}
