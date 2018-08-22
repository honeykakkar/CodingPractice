package Utilities.MultiThreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/26/2016
 */
public class CallableDemo {

    static class StringLength implements Callable<Integer> {

        String input;

        StringLength(String input) {
            this.input = input;
        }

        @Override
        public Integer call() throws Exception {
            return input.trim().length();
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("   Honey     ", "   Kakkar     ", "J A V A     ", "    Callable"));
        ArrayList<Future<Integer>> outputs = new ArrayList<>();

        for (String input : inputs) {
            Callable<Integer> callable = new StringLength(input);
            Future<Integer> future = threadPool.submit(callable);
            outputs.add(future);
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(5, TimeUnit.SECONDS);

            for (Future<Integer> future : outputs)
                System.out.println("Length: " + future.get());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
