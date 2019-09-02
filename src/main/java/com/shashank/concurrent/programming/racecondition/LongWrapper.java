package com.shashank.concurrent.programming.racecondition;

  public class LongWrapper {
    private long value;
    private Object lock = new Object();

    public LongWrapper(long value) {
      this.value = value;
    }

    public long getValue() {
      synchronized (lock) {
        return value;
      }

    }
    public void incrementValue() {
      synchronized (lock) {
        value = value + 1;

      }
    }
  }
