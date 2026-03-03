package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    // Хеш всегда в 0 = гарантированные коллизии
    static class TestHashTable extends HashTable {
        @Override
        protected int hash(String key) {
            super.hash(key); // чтобы зафиксировать HASH_COMPUTED
            return 0;
        }
    }

    @Test
    void testPutIntoEmptyBucket_trace() {
        TestHashTable table = new TestHashTable();
        List<HashTable.TP> trace = new ArrayList<>();
        table.setTrace(trace::add);

        table.put("A", "1");

        assertEquals(List.of(
                HashTable.TP.HASH_COMPUTED,
                HashTable.TP.BUCKET_EMPTY,
                HashTable.TP.KEY_NOT_FOUND,
                HashTable.TP.INSERT_NEW
        ), trace);
    }

    @Test
    void testPutCollision_trace() {
        TestHashTable table = new TestHashTable();
        table.put("A", "1");

        List<HashTable.TP> trace = new ArrayList<>();
        table.setTrace(trace::add);

        table.put("B", "2");

        assertEquals(List.of(
                HashTable.TP.HASH_COMPUTED,
                HashTable.TP.BUCKET_NOT_EMPTY,
                HashTable.TP.SCAN_ENTRY,
                HashTable.TP.KEY_NOT_FOUND,
                HashTable.TP.INSERT_NEW
        ), trace);
    }

    @Test
    void testUpdateExisting_trace() {
        TestHashTable table = new TestHashTable();
        table.put("A", "1");

        List<HashTable.TP> trace = new ArrayList<>();
        table.setTrace(trace::add);

        table.put("A", "999");

        assertEquals(List.of(
                HashTable.TP.HASH_COMPUTED,
                HashTable.TP.BUCKET_NOT_EMPTY,
                HashTable.TP.SCAN_ENTRY,
                HashTable.TP.KEY_FOUND,
                HashTable.TP.UPDATE_EXISTING
        ), trace);
    }

    @Test
    void testGetNotFound_trace() {
        TestHashTable table = new TestHashTable();

        List<HashTable.TP> trace = new ArrayList<>();
        table.setTrace(trace::add);

        table.get("X");

        assertEquals(List.of(
                HashTable.TP.HASH_COMPUTED,
                HashTable.TP.BUCKET_EMPTY,
                HashTable.TP.KEY_NOT_FOUND
        ), trace);
    }

    @Test
    void testRemoveFound_trace() {
        TestHashTable table = new TestHashTable();
        table.put("A", "1");

        List<HashTable.TP> trace = new ArrayList<>();
        table.setTrace(trace::add);

        table.remove("A");

        assertEquals(List.of(
                HashTable.TP.HASH_COMPUTED,
                HashTable.TP.SCAN_ENTRY,
                HashTable.TP.REMOVE_FOUND
        ), trace);
    }

    @Test
    void testRemoveNotFound_trace() {
        TestHashTable table = new TestHashTable();
        table.put("A", "1");

        List<HashTable.TP> trace = new ArrayList<>();
        table.setTrace(trace::add);

        table.remove("Z");

        assertEquals(List.of(
                HashTable.TP.HASH_COMPUTED,
                HashTable.TP.SCAN_ENTRY,
                HashTable.TP.REMOVE_NOT_FOUND
        ), trace);
    }
}