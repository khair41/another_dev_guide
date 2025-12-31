package com.problems.binary_search.solutions;

import com.framework.Solution;
import com.problems.binary_search.problems.TimeBasedKeyValueStoreProblem.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStoreSolution implements Solution<List<Operation>, List<String>> {

    /*
     * --- APPROACH ---
     * [TODO: Explain the core idea of your TimeMap implementation.]
     * Hint: Use a HashMap to store the keys. The value for each key will be a list of pairs
     * or custom objects, where each pair holds a `(timestamp, value)`. This list must be
     * kept sorted by timestamp.
     *
     * --- INTUITION ---
     * [TODO: Why does this approach work? Explain the logic in plain English.]
     * Hint: A HashMap provides O(1) average time access to the list of values for any given key.
     * Since the timestamps for each key are guaranteed to be strictly increasing, the list of
     * values will be naturally sorted by timestamp. This allows us to use binary search on the
     * list to find the correct value for a `get` operation in O(log N) time, where N is the
     * number of entries for that specific key.
     *
     * --- COMPLEXITY ---
     *
     *   - `set`: O(1)
     *     [Adding a new value is an O(1) operation for the HashMap and an amortized O(1) for adding to the end of an ArrayList.]
     *
     *   - `get`: O(log N)
     *     [Where N is the number of entries for the given key. This is the time complexity of the binary search.]
     */

    // This is the class you will implement.
    class TimeMap {
        // TODO: Initialize your data structures here.
        // Hint: Map<String, List<Pair<Integer, String>>>

        public TimeMap() {
        }

        public void set(String key, String value, int timestamp) {
            // TODO: Implement the set operation.
        }

        public String get(String key, int timestamp) {
            // TODO: Implement the get operation using binary search.
            return ""; // Placeholder
        }
    }

    // This is the driver method that our TestRunner will call.
    @Override
    public List<String> execute(List<Operation> operations) {
        TimeMap timeMap = null;
        List<String> results = new ArrayList<>();

        for (Operation op : operations) {
            switch (op.name()) {
                case "TimeMap":
                    timeMap = new TimeMap();
                    break;
                case "set":
                    timeMap.set(op.key(), op.value(), op.timestamp());
                    break;
                case "get":
                    results.add(timeMap.get(op.key(), op.timestamp()));
                    break;
            }
        }
        return results;
    }

    @Override
    public String getTimeComplexity() {
        return "set: O(1), get: O(log N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N)";
    }
}
