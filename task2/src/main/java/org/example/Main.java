package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTable table = new HashTable();

        List<HashTable.TP> trace = new ArrayList<>();
        table.setTrace(trace::add);

        table.put("A", "1");
        table.put("B", "2");

        System.out.println("A=" + table.get("A"));

        table.remove("B");
        System.out.println("B=" + table.get("B"));

        System.out.println("Trace: " + trace);
    }
}