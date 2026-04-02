package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    static class TestHashTable extends HashTable {
        @Override
        protected int hash(String key) {
            super.hash(key);
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
    void testFoundAndReturnsValue_trace() {
        TestHashTable table = new TestHashTable();
        table.put("A", "1");

        List<HashTable.TP> trace = new ArrayList<>();
        table.setTrace(trace::add);

        String result = table.get("A");

        assertEquals("1", result);
        assertEquals(List.of(
                HashTable.TP.HASH_COMPUTED,
                HashTable.TP.BUCKET_NOT_EMPTY,
                HashTable.TP.SCAN_ENTRY,
                HashTable.TP.KEY_FOUND
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
    void testGetNotFoundValue_trace() {
        TestHashTable table = new TestHashTable();
        table.put("A", "1");

        List<HashTable.TP> trace = new ArrayList<>();
        table.setTrace(trace::add);

        String result = table.get("Z");

        assertNull(result);
        assertEquals(List.of(
                HashTable.TP.HASH_COMPUTED,
                HashTable.TP.BUCKET_NOT_EMPTY,
                HashTable.TP.SCAN_ENTRY,
                HashTable.TP.KEY_NOT_FOUND
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

    @Test
    void testGetFromEmptyTable_trace() {
        TestHashTable table = new TestHashTable();

        List<HashTable.TP> trace = new ArrayList<>();
        table.setTrace(trace::add);

        String result = table.get("P");

        assertNull(result);
        assertEquals(List.of(
                HashTable.TP.HASH_COMPUTED,
                HashTable.TP.BUCKET_EMPTY,
                HashTable.TP.KEY_NOT_FOUND
        ), trace);
    }

    @Test
    void testRemoveFromEmptyTable_trace() {
        TestHashTable table = new TestHashTable();

        List<HashTable.TP> trace = new ArrayList<>();
        table.setTrace(trace::add);

        table.remove("W");

        assertEquals(List.of(
                HashTable.TP.HASH_COMPUTED,
                HashTable.TP.REMOVE_NOT_FOUND
        ), trace);
    }
}