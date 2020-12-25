package com.company;

import java.util.Random;
import java.util.concurrent.*;

class Worker implements Runnable{

    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public Worker(int id, CyclicBarrier cyclicBarrier){
        this.id = id;
        this.random = new Random();
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread: " + this.id + " -> Work Started...");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread: " + this.id + " Finished...");

        try {
            cyclicBarrier.await();
            System.out.println("After await()..." + " Thread: " + this.id);
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return ""+this.id;
    }
}

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("Tasks: All Finished...");
            }
        });

        for (int i = 0; i < 5; i++){
            executorService.execute(new Worker(i + 1, barrier));
        }

        executorService.shutdown();
    }
}
