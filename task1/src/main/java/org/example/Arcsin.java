package org.example;

public class Arcsin {
    public static double asin(double x, double eps, int maxIterations) throws IllegalArgumentException{
        if (x < -1.0 || x > 1.0) {
            throw new IllegalArgumentException("x must be in [-1, 1]");
        }

        double term = x;
        double sum = term;
        int n = 0;

        while (Math.abs(term) > eps && n < maxIterations) {
            double numerator = (2.0 * n + 1) * (2.0 * n + 1) * x * x;
            double denominator = 2.0 * (n + 1) * (2.0 * n + 3);

            term = term * numerator / denominator;
            sum += term;

            n++;
        }

        return sum;
    }
}
