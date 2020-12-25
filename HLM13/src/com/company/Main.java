package com.company;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorker implements Runnable{

    private BlockingQueue<Person> blockingQueue;

    public FirstWorker(BlockingQueue<Person> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
//            blockingQueue.put("B");
//            blockingQueue.put("H");
//            blockingQueue.put("F");
            blockingQueue.put(new Person(12, "Alex"));
            blockingQueue.put(new Person(2, "david"));
            blockingQueue.put(new Person(25, "nibba"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(89, "wtf"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(18, "XD"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorker implements Runnable{

    private BlockingQueue<Person> blockingQueue;

    public SecondWorker(BlockingQueue<Person> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("Take: " + blockingQueue.take());
            Thread.sleep(1000);
            System.out.println("Take: " + blockingQueue.take());
            Thread.sleep(1000);
            System.out.println("Take: " + blockingQueue.take());
            System.out.println("Take: " + blockingQueue.take());
            System.out.println("Take: " + blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Comparable<Person>{

    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person otherPerson) {
        if (this.age < otherPerson.age){
            return -1;
        }else if (this.age > otherPerson.age){
            return 1;
        }else{
            return 0;
        }

//        return Integer.compare(this.age, otherPerson.age);  // -> could also use this.
    }

    @Override
    public String toString() {
        return this.name + " -> " + this.age;
    }
}

public class Main {

    public static void main(String[] args) {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>(); // -> Sorts according to the priority or Alphabetical or numberical order.

        new Thread(new FirstWorker(queue)).start();
        new Thread(new SecondWorker(queue)).start();
    }
}
