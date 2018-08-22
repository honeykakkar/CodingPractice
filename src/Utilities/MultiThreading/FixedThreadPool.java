package Utilities.MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/26/2016
 */

public class FixedThreadPool {

    static class WorkerThread implements Runnable {

        private String command;

        WorkerThread(String s) {
            this.command = s;
        }

        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
            processCommand();
            System.out.println(Thread.currentThread().getName() + " End.");
        }

        private void processCommand() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return this.command;
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread(" " + i);
            threadPool.execute(worker);
        }
        threadPool.shutdown();    // Disable new tasks from being submitted
        try {
            threadPool.awaitTermination(10, TimeUnit.SECONDS);
            // Wait a while for existing tasks to terminate
            if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                threadPool.shutdownNow(); // Cancel currently executing tasks

                // Wait a while for tasks to respond to being cancelled
                if (!threadPool.awaitTermination(5, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        System.out.println("Finished all threads");
    }
}
