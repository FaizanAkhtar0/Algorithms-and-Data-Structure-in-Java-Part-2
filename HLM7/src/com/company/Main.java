package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {

    INSTANCE;

    private Semaphore semaphore = new Semaphore(3, true);

    public void downloadData(){
        try {
            semaphore.acquire();
            download();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            semaphore.release();
        }
    }

    private void download() {
        System.out.println("Downloading data from the web...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(); // -> reuses the terminated threads only creates new thread when all the threads are busy.
//        ExecutorService executorService = Executors.newFixedThreadPool(5); // -> if all threads are busy waits for 1 to terminate and do the task on that thread.
//        ExecutorService executorService = Executors.newSingleThreadExecutor(); -> SingleThread executes -- execute() -> Runnable + Callable; submit() -> Runnable <-> interfaces

        for (int i = 0; i < 12; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Downloader.INSTANCE.downloadData();
                }
            });
        }

        executorService.shutdown();
    }
}
