package com.company;

public class Main {

    static void series(int A, int X, int n)
    {

        // Calculating and printing first
        // term
        int term = (int)Math.pow(A, n);
        System.out.print(term + " ");

        // Computing and printing
        // remaining terms
        for (int i = 1; i <= n; i++) {

            // Find current term using
            // previous terms We increment
            // power of X by 1, decrement
            // power of A by 1 and compute
            // nCi using previous term by
            // multiplying previous term
            // with (n - i + 1)/i
            System.out.println("(n - i + 1): " + (n - i + 1));
            System.out.println("(i * A): " + (i * A));
            System.out.println("(n - i + 1) / (i * A): " + (n - i + 1) / (i * A));
            System.out.println("X * (n - i + 1) / (i * A): " + X * (n - i + 1) / (i * A));

            term = term * X * (n - i + 1) / (i * A);

            System.out.print(term + " ");
        }
    }

    public static void main(String[] args) {

        int A = 3, X = 4, n = 5;

        series(A, X, n);

    }
}
