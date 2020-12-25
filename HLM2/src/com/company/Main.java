package com.company;

public class Main {

    private static int count1 = 0, count2 = 0;
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void increment(){
        synchronized (lock1){
            count1++;
        }
    }

    public static void increment1(){
        synchronized (lock2){
            count2++;
        }
    }

    public static void compute(){
        for (int i = 0; i < 10; i++){
            increment();
            increment1();
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });

        t1.start(); t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count1: " + count1 + " - " + "Count2: " + count2);
    }
}
