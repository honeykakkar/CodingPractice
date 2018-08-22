package Utilities.MultiThreading;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/10/2016
 */
public class TestingThreads {

    public static void main(String[] args) {

        try {
            CustomThread targetRun = new CustomThread();
            Thread runThread = new Thread(targetRun);
            DerivedThread derivedThread = new DerivedThread();

            Thread.sleep(500); // causing the main thread to sleep for 2 secs

            runThread.start();
            runThread.join();   // causes the main thread to pause execution until runThread terminates.

            derivedThread.start();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
