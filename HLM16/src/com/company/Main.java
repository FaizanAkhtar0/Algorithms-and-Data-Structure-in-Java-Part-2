package com.company;

import java.util.concurrent.ConcurrentHashMap;

class FirstWorker implements Runnable{

    private ConcurrentHashMap<String, Integer> map;

    public FirstWorker(ConcurrentHashMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try{

            map.put("B", 1);
            map.put("H", 2);
            Thread.sleep(1000);
            map.put("F", 3);
            map.put("A", 4);
            Thread.sleep(1000);
            map.put("E", 5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class SecondWorker implements Runnable{

    private ConcurrentHashMap<String, Integer> map;

    public SecondWorker(ConcurrentHashMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            Thread.sleep(1000);
            System.out.println(map.get("E"));
            System.out.println(map.get("C"));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class Main {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        new Thread(new FirstWorker(map)).start();
        new Thread(new SecondWorker(map)).start();
    }
}
