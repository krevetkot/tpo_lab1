package org.example;

import static java.lang.Double.NaN;


public class Arcsin {
    private static double EPS = 1e-12;
    private static int MAX_ITERATIONS = 200000;

    public static double getEPS(){
        return EPS;
    }

    public static int getMaxIterations(){
        return MAX_ITERATIONS;
    }

    public static void setEPS(double eps){
        EPS = eps;
    }

    public static void setMaxIterations(int maxIters){
        MAX_ITERATIONS = maxIters;
    }

    // есть расширенная версия метода со всеми настройками, а есть базовая
    public static double asin(double x) {
        return asin(x, EPS, MAX_ITERATIONS);
    }

    public static double asin(double x, double eps, int maxIterations) {
        if (x < -1.0 || x > 1.0) {
            return NaN; // потому что так работает Math.asin()
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
