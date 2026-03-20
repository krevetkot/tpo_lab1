package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class HashTable {

    private static final int CAPACITY = 10;

    private final LinkedList<Entry>[] table;

    //контрольные точки
    public enum TP {
        HASH_COMPUTED,
        BUCKET_EMPTY,
        BUCKET_NOT_EMPTY,
        SCAN_ENTRY,
        KEY_FOUND,
        KEY_NOT_FOUND,
        INSERT_NEW,
        UPDATE_EXISTING,
        REMOVE_FOUND,
        REMOVE_NOT_FOUND
    }

    private Consumer<TP> trace;

    public void setTrace(Consumer<TP> trace) {
        this.trace = trace;
    }

    private void emit(TP point) {
        if (trace != null) {
            trace.accept(point);
        }
    }

    public HashTable() {
        table = new LinkedList[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
    }

    protected int hash(String key) {
        emit(TP.HASH_COMPUTED);
        return Math.abs(key.hashCode()) % CAPACITY;
    }

    public void put(String key, String value) {
        int index = hash(key);
        List<Entry> bucket = table[index];

        if (bucket.isEmpty()) {
            emit(TP.BUCKET_EMPTY);
        } else {
            emit(TP.BUCKET_NOT_EMPTY);
        }

        for (Entry entry : bucket) {
            emit(TP.SCAN_ENTRY);
            if (entry.key.equals(key)) {
                emit(TP.KEY_FOUND);
                entry.value = value;
                emit(TP.UPDATE_EXISTING);
                return;
            }
        }

        emit(TP.KEY_NOT_FOUND);
        bucket.add(new Entry(key, value));
        emit(TP.INSERT_NEW);
    }

    public String get(String key) {
        int index = hash(key);
        List<Entry> bucket = table[index];

        if (bucket.isEmpty()) {
            emit(TP.BUCKET_EMPTY);
            emit(TP.KEY_NOT_FOUND);
            return null;
        }

        emit(TP.BUCKET_NOT_EMPTY);

        for (Entry entry: bucket) {
            emit(TP.SCAN_ENTRY);
            if (entry.key.equals(key)) {
                emit(TP.KEY_FOUND);
                return entry.value;
            }
        }

        emit(TP.KEY_NOT_FOUND);
        return null;
    }

    public void remove(String key) {
        int index = hash(key);
        List<Entry> bucket = table[index];

        for (int i = 0; i < bucket.size(); i++) {
            emit(TP.SCAN_ENTRY);
            if (bucket.get(i).key.equals(key)) {
                bucket.remove(i);
                emit(TP.REMOVE_FOUND);
                return;
            }
        }

        emit(TP.REMOVE_NOT_FOUND);
    }

    private static class Entry {
        String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}