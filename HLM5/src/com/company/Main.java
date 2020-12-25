package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static int counter = 0;
    private static Lock lock = new ReentrantLock();

    public static void increment(){
        lock.lock();
        try{
            for(int i = 1; i <= 1000; i++){
                ++counter;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void unlockTheLock(){  //  -> can also lock at given position and unlock at a given position using ReentrantLock...
        lock.unlock();
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        t1.start(); t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter: " + counter);
    }
}
