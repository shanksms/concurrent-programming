package com.shashank.concurrent.programming.deadlock;

public class Deadlock {

  public static void main(String[] args) throws Exception {
    A a = new A();

    Thread t1 = new Thread(() -> {
      a.a();
    });

    Thread t2 = new Thread(() -> {
      a.b();
    });

    t1.start(); t2.start();
    t1.join(); t2.join();


  }
}
