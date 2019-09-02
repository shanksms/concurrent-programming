package com.shashank.concurrent.programming;

  public class LongWrapper {
    private long value;
    private Object lock = new Object();

    public LongWrapper(long value) {
      this.value = value;
    }

    public long getValue() {
      return value;
    }
    public void incrementValue() {
      synchronized (lock) {
        value = value + 1;

      }
    }
  }
