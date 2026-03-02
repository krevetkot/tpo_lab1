package org.example;

import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main <x> <eps> <n>");
            return;
        }

        double x = Double.parseDouble(args[0]);
        double eps = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);

        try {
            double result = Arcsin.asin(x, eps, n);
            System.out.printf("arcsin(%.4f) = %.4f +- %e", x, Math.toDegrees(result), Math.toDegrees(eps));
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}