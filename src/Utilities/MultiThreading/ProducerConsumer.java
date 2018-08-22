package Utilities.MultiThreading;

/*
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/26/2016
 */

// Java program to implement solution of producer
// consumer problem.

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
    // This class has a list, producer (adds items to list
    // and consumer (removes items).
    static class PC {
        // Create a list shared by producer and consumer
        // Size of list is 10.
        BlockingQueue<Integer> list = new ArrayBlockingQueue<>(10);

        // Function called by producer thread
        void produce() throws InterruptedException {
            int value = 1;
            while (true) {
                synchronized (this) {
                    // producer thread waits while list
                    // is full
                    while (list.remainingCapacity() == 0)
                        wait();

                    System.out.println("Producer produced-" + value);

                    // to insert the jobs in the list
                    list.offer(value++);

                    // notifies the consumer thread that now it can start consuming
                    notify();

                    // makes the working of program easier to  understand
                    Thread.sleep(1000);
                }
            }
        }

        // Function called by consumer thread
        void consume() throws InterruptedException {
            while (true) {
                synchronized (this) {
                    // consumer thread waits while list
                    // is empty
                    while (list.size() == 0)
                        wait();

                    //to retrieve the first job in the list
                    int val = list.poll();

                    System.out.println("Consumer consumed-"
                            + val);

                    // Wake up producer thread
                    notify();

                    // and sleep
                    Thread.sleep(1000);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Object of a class that has both produce()
        // and consume() methods
        final PC pc = new PC();

        // Create producer thread
        Thread t1 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Create consumer thread
        Thread t2 = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // t1 finishes before t2
        t1.join();
        t2.join();
    }
}
