package org.example.counter;

public class Counter implements Runnable{
    private int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int getValue() {
        return count;
    }

    @Override
    public void run() {
//        synchronized (this){    // 동기화 처리로 해결할 수 있음.
            this.increment();
            System.out.println("value for Thread After increment " + Thread.currentThread().getName() + " " + this.getValue()); // 1
            this.decrement();
            System.out.println("value for Thread at last " + Thread.currentThread().getName() + " " + this.getValue());  // 0
//        }
    }
}
