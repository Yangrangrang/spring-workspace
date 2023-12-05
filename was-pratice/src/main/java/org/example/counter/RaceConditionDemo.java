package org.example.counter;

/**
 * 멀티스레드에서 하나의 자원을 공유 하게 되면 우리가 뜻하지 않는 레이스 컨디션
 * 원치 않는 결과가 나옴.
 * value for Thread After increment Thread1 1
 * value for Thread After increment Thread3 3
 * value for Thread After increment Thread2 2
 * value for Thread at last Thread1 2
 * value for Thread at last Thread3 1
 * value for Thread at last Thread2 0
 *
 * 레이스 컨디션이란? 여러 프로세스 혹은 스레드가 동시에 하나의 자원에 접근하기 위해 경쟁하는 상태
 */
public class RaceConditionDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1  = new Thread(counter, "Thread1");
        Thread t2  = new Thread(counter, "Thread2");
        Thread t3  = new Thread(counter, "Thread3");

        t1.start();
        t2.start();
        t3.start();


    }
}
