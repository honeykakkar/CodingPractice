package Utilities.MultiThreading;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/10/2016
 */

public class ThreadSync {

    // Thread synchronization ensures that only one thread can access the protected (shared) data or code.

    static class CountPrinter {

        // One can also make this method synchronized
        // instead of monitoring CountPrinter's object to achieve concurrency on this method.
        void printCount() {
            try {
                for (int i = 50; i > 0; i--) {
                    System.out.println("Counter   ---   " + i);
                }
            } catch (Exception e) {
                System.out.println("Thread  interrupted.");
            }
        }
    }


    static class MyThread extends Thread {
        private Thread t;
        private String threadName;
        private final CountPrinter PD;

        MyThread(String name, CountPrinter pd) {
            threadName = name;
            PD = pd;
        }

        @Override
        public void run() {
            synchronized (PD) {
                PD.printCount();
            }
            System.out.println("Thread " + threadName + " exiting.");
        }

        @Override
        public void start() {
            System.out.println("Starting " + threadName);
            if (t == null) {
                t = new Thread(this, threadName);
                t.start();
            }
        }
    }

    public static void main(String args[]) {
        CountPrinter PD = new CountPrinter();

        MyThread T1 = new MyThread("Thread - 1 ", PD);
        MyThread T2 = new MyThread("Thread - 2 ", PD);

        T1.start();
        T2.start();

        // wait for threads to end
        try {
            T1.join();
            T2.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}
