package Utilities.MultiThreading;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/10/2016
 */
class CustomThread implements Runnable {

    @Override
    public void run() {
        System.out.println("\n----" + this.toString() + "----\n");
        for (int i = 1; i <= 100; ++i)
            System.out.print(i * i * i + " ");
    }

    @Override
    public String toString() {
        Thread current = Thread.currentThread();
        return "CustomThread[" + current.getName() + "," + current.getPriority() + "," + current.getThreadGroup().getName() + "]";
    }
}
