package com.framework;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A generic utility to run tests for any class that implements the Problem interface.
 * This version is enhanced to display rich metadata and test multiple solutions.
 */
public class TestRunner {

    /**
     * Runs all test cases for a given problem and prints a detailed report.
     * @param problem The problem to run.
     * @return {@code true} if all solutions passed all test cases, {@code false} otherwise.
     */
    public static <I, O> boolean run(Problem<I, O> problem) {
        // --- Header ---
        System.out.printf("\n================================================================================\n");
        System.out.printf("Problem:     %s (%s)\n", problem.getName(), problem.getID());
        System.out.printf("Source:      %s\n", problem.getSource());
        if (problem.getExternalLink() != null && !problem.getExternalLink().isBlank()) {
            System.out.printf("Link:        %s\n", problem.getExternalLink());
        }
        if (problem.getTopics() != null && !problem.getTopics().isEmpty()) {
            String topics = problem.getTopics().stream().map(Enum::toString).collect(Collectors.joining(", "));
            System.out.printf("Topics:      %s\n", topics);
        }
        System.out.printf("Description: %s\n", problem.getDescription());

        Map<String, Solution<I, O>> solutions = problem.getSolutions();
        List<TestCase<I, O>> testCases = problem.getTestCases();

        if (solutions == null || solutions.isEmpty()) {
            System.out.println("\nNo solutions found for this problem.");
            System.out.printf("================================================================================\n");
            return true; // No solutions to fail
        }

        boolean allSolutionsPassed = true;

        // --- Test Execution ---
        for (Map.Entry<String, Solution<I, O>> entry : solutions.entrySet()) {
            System.out.printf("--------------------------------------------------------------------------------\n");
            String solutionName = entry.getKey();
            Solution<I, O> solution = entry.getValue();
            int passedCount = 0;

            System.out.printf("Testing Solution: '%s'\n", solutionName);
            System.out.printf("  Time Complexity:  %s\n", solution.getTimeComplexity());
            System.out.printf("  Space Complexity: %s\n\n", solution.getSpaceComplexity());

            for (int i = 0; i < testCases.size(); i++) {
                TestCase<I, O> tc = testCases.get(i);
                try {
                    O actualOutput = solution.execute(tc.input());
                    boolean isCorrect = areOutputsEqual(tc.expectedOutput(), actualOutput, tc.orderAgnostic());

                    if (isCorrect) {
                        passedCount++;
                        System.out.printf("  ✅ Test %d: PASS\n", i + 1);
                    } else {
                        System.out.printf("  ❌ Test %d: FAIL\n", i + 1);
                        System.out.printf("     - Input:    %s\n", formatValue(tc.input()));
                        System.out.printf("     - Expected: %s\n", formatValue(tc.expectedOutput()));
                        System.out.printf("     - Actual:   %s\n", formatValue(actualOutput));
                    }
                } catch (Exception e) {
                    System.out.printf("  ❌ Test %d: ERROR\n", i + 1);
                    System.out.printf("     - Input:    %s\n", formatValue(tc.input()));
                    System.out.printf("     - An exception occurred: %s\n", e);
                }
            }

            boolean allTestsForThisSolutionPassed = passedCount == testCases.size();
            if (!allTestsForThisSolutionPassed) {
                allSolutionsPassed = false;
            }

            // --- Solution Summary ---
            String resultStatus = allTestsForThisSolutionPassed ? "✅ PASSED" : "❌ FAILED";
            System.out.printf("\n    Result for '%s': %s (%d / %d tests passed)\n", solutionName, resultStatus, passedCount, testCases.size());
        }

        // --- Overall Summary ---
        System.out.printf("--------------------------------------------------------------------------------\n");
        if (allSolutionsPassed) {
            System.out.printf("✅ Overall Result: All solutions for '%s' passed!\n", problem.getName());
        } else {
            System.out.printf("❌ Overall Result: Some solutions for '%s' failed.\n", problem.getName());
        }
        System.out.printf("================================================================================\n");

        return allSolutionsPassed;
    }

    private static boolean areOutputsEqual(Object expected, Object actual, boolean orderAgnostic) {
        if (orderAgnostic) {
            if (expected instanceof List && actual instanceof List) {
                return compareListsOrderAgnostic((List<?>) expected, (List<?>) actual);
            }
            if (expected instanceof int[] && actual instanceof int[]) {
                return compareIntArraysOrderAgnostic((int[]) expected, (int[]) actual);
            }
            if (expected instanceof Object[] && actual instanceof Object[]) {
                return compareObjectArraysOrderAgnostic((Object[]) expected, (Object[]) actual);
            }
        }
        return Objects.deepEquals(expected, actual);
    }

    private static boolean compareIntArraysOrderAgnostic(int[] expected, int[] actual) {
        if (expected == null || actual == null) return expected == actual;
        if (expected.length != actual.length) return false;
        int[] sortedExpected = Arrays.copyOf(expected, expected.length);
        int[] sortedActual = Arrays.copyOf(actual, actual.length);
        Arrays.sort(sortedExpected);
        Arrays.sort(sortedActual);
        return Arrays.equals(sortedExpected, sortedActual);
    }

    private static boolean compareObjectArraysOrderAgnostic(Object[] expected, Object[] actual) {
        if (expected == null || actual == null) return expected == actual;
        if (expected.length != actual.length) return false;
        Object[] sortedExpected = Arrays.copyOf(expected, expected.length);
        Object[] sortedActual = Arrays.copyOf(actual, actual.length);
        // A simple string-based comparator is a robust general-purpose choice.
        Comparator<Object> comparator = Comparator.comparing(Object::toString);
        Arrays.sort(sortedExpected, comparator);
        Arrays.sort(sortedActual, comparator);
        return Arrays.deepEquals(sortedExpected, sortedActual);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static boolean compareListsOrderAgnostic(List<?> list1, List<?> list2) {
        if (list1 == null || list2 == null) return list1 == list2;
        if (list1.size() != list2.size()) return false;

        // Create sorted copies for comparison
        List<Object> sortedList1 = new ArrayList<>();
        for (Object item : list1) {
            if (item instanceof List) {
                List<Object> innerList = new ArrayList<>((List<?>) item);
                try {
                    Collections.sort((List) innerList);
                } catch (Exception ignored) {}
                sortedList1.add(innerList);
            } else {
                sortedList1.add(item);
            }
        }

        List<Object> sortedList2 = new ArrayList<>();
        for (Object item : list2) {
            if (item instanceof List) {
                List<Object> innerList = new ArrayList<>((List<?>) item);
                try {
                    Collections.sort((List) innerList);
                } catch (Exception ignored) {}
                sortedList2.add(innerList);
            } else {
                sortedList2.add(item);
            }
        }

        Comparator<Object> comparator = Comparator.comparing(Object::toString);
        sortedList1.sort(comparator);
        sortedList2.sort(comparator);

        return Objects.deepEquals(sortedList1, sortedList2);
    }

    private static String formatValue(Object value) {
        if (value == null) return "null";
        // Use deepToString for arrays of objects, which handles nested arrays.
        if (value instanceof Object[]) return Arrays.deepToString((Object[]) value);
        if (value instanceof int[]) return Arrays.toString((int[]) value);
        if (value instanceof char[][]) return Arrays.deepToString((char[][]) value);
        // The default toString for records and other objects is usually sufficient and readable.
        return Objects.toString(value);
    }
}
