package Mathematics;

import java.util.ArrayList;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/20/2016
 */

// Given a positive number n, print all prime numbers till n (inclusive).

    // Prime number theorem gives an approximate of number of primes less than or equal to n.
    // Number of primes (n) = n / log (n -1)

public class PrimeNumbers {

    // Trial division method - not efficient
    private boolean isAPrime(int number){
        if(number <= 1)
            return false;
        if(number == 2)
            return true;
        if (number%2 == 0)
            return false;

        for(int i=3; i*i <= number; i = i+2){
            if(number%i == 0)
                return false;
        }
        return true;
    }

    // method to get all prime numbers using trial division method - not efficient
    private ArrayList<Integer> getAllPrimeNumbers(int n){
        ArrayList<Integer> primeNumbers = new ArrayList<>(n/2);
        if(n <= 1)
            return primeNumbers;

        primeNumbers.add(2);
        for(int i=3; i<=n; i=i+2){
            if(isAPrime(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    // method to get all prime numbers using Sieve of Eratosthenes  method
    private ArrayList<Integer> getAllPrimeNumbers_SoE(int n){
        ArrayList<Integer> primeNumbers = new ArrayList<>(n/2);
        if(n <= 1)
            return primeNumbers;

        boolean[] isPrime = new boolean[n+1];
        for(int i=0; i<isPrime.length; ++i)
            isPrime[i] = true;

        for(int i=2; i*i<n; ++i){
            if(isPrime[i]){
                for(int j = i + i; j<=n; j = j+i)
                    isPrime[j] = false;
            }
        }

        for(int i=2; i<=n; ++i){
            if(isPrime[i])
                primeNumbers.add(i);
        }
        return primeNumbers;
    }

    // method to display the list of elements
    private <T> void display(ArrayList<T> array){
        for (T element : array)
            System.out.print(element + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        PrimeNumbers primeNumbers = new PrimeNumbers();
        int number = 4;
        System.out.println(number + " is " + (primeNumbers.isAPrime(number) ? "" : "not ") + "a prime number");
        number = 83;
        System.out.println(number + " is " + (primeNumbers.isAPrime(number) ? "" : "not ") + "a prime number");
        number = 87;
        System.out.println(number + " is " + (primeNumbers.isAPrime(number) ? "" : "not ") + "a prime number");
        number = 57;
        System.out.println(number + " is " + (primeNumbers.isAPrime(number) ? "" : "not ") + "a prime number");
        number = 97;
        System.out.println(number + " is " + (primeNumbers.isAPrime(number) ? "" : "not ") + "a prime number");
        number = 107;
        System.out.println(number + " is " + (primeNumbers.isAPrime(number) ? "" : "not ") + "a prime number");
        number = 103;
        System.out.println(number + " is " + (primeNumbers.isAPrime(number) ? "" : "not ") + "a prime number");
        number = 101;
        System.out.println(number + " is " + (primeNumbers.isAPrime(number) ? "" : "not ") + "a prime number");

        int n; long startTime, endTime;
        ArrayList<Integer> allPrimeNumbers;

        n = 109;
        startTime = System.nanoTime();
        allPrimeNumbers = primeNumbers.getAllPrimeNumbers(n);
        endTime = System.nanoTime();
        System.out.printf("%nThere are total %d prime numbers till %d (inclusive)%n",allPrimeNumbers.size(), n);
        primeNumbers.display(allPrimeNumbers);
        System.out.printf("Trial division took %d ns to finish.%n", (endTime-startTime));
        startTime = System.nanoTime();
        allPrimeNumbers = primeNumbers.getAllPrimeNumbers_SoE(n);
        endTime = System.nanoTime();
        System.out.printf("There are total %d prime numbers till %d (inclusive)%n",allPrimeNumbers.size(), n);
        System.out.printf("Sieve of E took %d ns to finish.%n", (endTime-startTime));

        n = 1000;
        startTime = System.nanoTime();
        allPrimeNumbers = primeNumbers.getAllPrimeNumbers(n);
        endTime = System.nanoTime();
        System.out.printf("%nThere are total %d prime numbers till %d (inclusive)%n",allPrimeNumbers.size(), n);
        primeNumbers.display(allPrimeNumbers);
        System.out.printf("Trial division took %d ns to finish.%n", (endTime-startTime));
        startTime = System.nanoTime();
        allPrimeNumbers = primeNumbers.getAllPrimeNumbers(n);
        endTime = System.nanoTime();
        System.out.printf("There are total %d prime numbers till %d (inclusive)%n",allPrimeNumbers.size(), n);
        System.out.printf("Sieve of E took %d ns to finish.%n", (endTime-startTime));

        n = 10000;
        startTime = System.nanoTime();
        allPrimeNumbers = primeNumbers.getAllPrimeNumbers(n);
        endTime = System.nanoTime();
        System.out.printf("%nThere are total %d prime numbers till %d (inclusive)%n",allPrimeNumbers.size(), n);
        //primeNumbers.displayMSTEdges(allPrimeNumbers);
        System.out.printf("Trial division took %d ns to finish.%n", (endTime-startTime));
        startTime = System.nanoTime();
        allPrimeNumbers = primeNumbers.getAllPrimeNumbers_SoE(n);
        endTime = System.nanoTime();
        System.out.printf("There are total %d prime numbers till %d (inclusive)%n",allPrimeNumbers.size(), n);
        System.out.printf("Sieve of E took %d ns to finish.%n", (endTime-startTime));

        n = 100000;
        startTime = System.nanoTime();
        allPrimeNumbers = primeNumbers.getAllPrimeNumbers(n);
        endTime = System.nanoTime();
        System.out.printf("%nThere are total %d prime numbers till %d (inclusive)%n",allPrimeNumbers.size(), n);
        //primeNumbers.displayMSTEdges(allPrimeNumbers);
        System.out.printf("Trial division took %d ns to finish.%n", (endTime-startTime));
        startTime = System.nanoTime();
        allPrimeNumbers = primeNumbers.getAllPrimeNumbers_SoE(n);
        endTime = System.nanoTime();
        System.out.printf("There are total %d prime numbers till %d (inclusive)%n",allPrimeNumbers.size(), n);
        System.out.printf("Sieve of E took %d ns to finish.%n", (endTime-startTime));
    }
}
