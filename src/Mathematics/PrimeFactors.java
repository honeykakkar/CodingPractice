package Mathematics;

import java.util.ArrayList;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 10/20/2016
 */
public class PrimeFactors {

    // Based on Sieve of E approach
    private ArrayList<Long> getPrimeFactors(long n){
        ArrayList<Long> primeFactors = new ArrayList<>();
        for(long factor = 2; factor*factor <= n; ++factor){
            while ( n%factor == 0){
                primeFactors.add(factor);
                n = n/factor;
            }
        }

        if(n>1)
            primeFactors.add(n);
        return primeFactors;
    }

    // method to display the list of elements
    private <T> void display(ArrayList<T> array){
        for (T element : array)
            System.out.print(element + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        PrimeFactors primeFactorization = new PrimeFactors();
        long n;
        ArrayList<Long> primeFactors;

        n=168;
        primeFactors = primeFactorization.getPrimeFactors(n);
        System.out.println("\nThe prime factors of " + n + " are :");
        primeFactorization.display(primeFactors);

        n=997;
        primeFactors = primeFactorization.getPrimeFactors(n);
        System.out.println("\nThe prime factors of " + n + " are :");
        primeFactorization.display(primeFactors);

        n = Long.parseLong("4444444444");
        primeFactors = primeFactorization.getPrimeFactors(n);
        System.out.println("\nThe prime factors of " + n + " are :");
        primeFactorization.display(primeFactors);

        n = Long.parseLong("4444444444444463");
        primeFactors = primeFactorization.getPrimeFactors(n);
        System.out.println("\nThe prime factors of " + n + " are :");
        primeFactorization.display(primeFactors);
    }
}
