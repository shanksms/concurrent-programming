package com.shashank.concurrent.programming.deadlock;

public class A {
  private Object lock1 = new Object();
  private Object lock2 = new Object();

  public void a() {
    synchronized (lock1) {
      System.out.println("[" + Thread.currentThread().getName() + " ] i am in a()");
      b();
    }
  }

  public void b() {
    synchronized (lock2) {
      System.out.println("[" + Thread.currentThread().getName() + " ] i am in b()");
      c();
    }

  }

  public void c() {
    synchronized (lock1) {
      System.out.println("[" + Thread.currentThread().getName() + " ] i am in c()");

    }
  }


}
