package Utilities.MultiThreading;

import java.util.concurrent.CountDownLatch;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/26/2016
 */
public class CountDownLatchDemo {

    static class Service implements Runnable {
        private final String serviceName;
        private final CountDownLatch latch;

        Service(String argName, CountDownLatch argLatch) {
            this.serviceName = argName;
            this.latch = argLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(serviceName + " is Up");
            latch.countDown();
        }
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(3);
        Thread cacheService = new Thread(new Service("CacheService", latch));
        Thread alertService = new Thread(new Service("AlertService", latch));
        Thread validationService = new Thread(new Service("ValidationService", latch));

        try {
            cacheService.start();
            cacheService.join();

            alertService.start();
            alertService.join();

            validationService.start();
            validationService.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            latch.await();
            System.out.println();
            System.out.println("All services are up, Application is starting now");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}