package Utilities.MultiThreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/26/2016
 */

public class CyclicBarrierDemo {

    //Runnable task for each thread
    private static class MyTask implements Runnable {

        private CyclicBarrier barrier;

        MyTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
                barrier.await();  // current thread waits at the CyclicBarrier
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
            } catch (InterruptedException | BrokenBarrierException ex) {
                Logger.getLogger(CyclicBarrierDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String args[]) {

        //creating CyclicBarrier with 3 parties i.e. 3 Threads needs to call await()
        // We pass runnable reference to specify what barrier action must be taken once all awaiting threads have reached the barrier.
        final CyclicBarrier cb = new CyclicBarrier(3, () -> {
            //This task will be executed once all thread reaches barrier
            System.out.println("All parties are arrived at barrier, lets play");
        });

        //starting each of thread
        Thread t1 = new Thread(new MyTask(cb), "Thread 1");
        Thread t2 = new Thread(new MyTask(cb), "Thread 2");
        Thread t3 = new Thread(new MyTask(cb), "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}