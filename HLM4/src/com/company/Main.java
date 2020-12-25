package com.company;

import java.util.ArrayList;
import java.util.List;

class Processor{

    private List<Integer> list = new ArrayList<>();
    private final int LIMIT = 5;
    private final int BOTTOM = 0;
    private int value;
    private final Object lock = new Object();

    public void produce() throws InterruptedException{
        synchronized (lock){
            while(true){
                if (list.size() == LIMIT){
                    System.out.println("Waiting for the consumer...");
                    lock.wait();
                }else{
                    System.out.println("Adding value: " + value);
                    list.add(value++);
                    lock.notify();
                }

                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException{
        synchronized (lock){
            while(true){
                if (list.size() == BOTTOM){
                    System.out.println("Waiting for producer...");
                    lock.wait();
                }else{
                    System.out.println("Removing value: " + list.remove(--value));
                    lock.notify();
                }

                Thread.sleep(500);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start(); t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
