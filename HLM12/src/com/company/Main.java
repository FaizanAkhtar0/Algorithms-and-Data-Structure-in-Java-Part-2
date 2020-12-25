package com.company;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class Main {

    public static void main(String[] args) {
        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();

        try{
            queue.put(new DelayedWorker(1000, "1st Message."));
            queue.put(new DelayedWorker(10000, "2nd Message."));
            queue.put(new DelayedWorker(4000, "3rd Message."));
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }

        while (!queue.isEmpty()){
            try{
                System.out.println("Take: " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
