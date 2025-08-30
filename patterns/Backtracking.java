package com.practice.coaching;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic template for solving backtracking com.problems.
 *
 * Backtracking is an algorithmic technique for solving com.problems recursively by trying to
 * build a solution incrementally, one piece at a time, removing those solutions that fail
 * to satisfy the constraints of the problem at any point in time (this is the "backtracking").
 *
 * Common com.problems that can be solved with this pattern:
 * - Subsets
 * - Permutations
 * - Combination Sum
 * - N-Queens
 * - Sudoku Solver
 */
public class Backtracking {

    /**
     * The public-facing function that clients will call.
     * It sets up the necessary data structures and initiates the backtracking process.
     *
     * @param input The input required for the problem (e.g., an array of candidates).
     * @return A list of all valid solutions.
     */
    public List<List<Integer>> solve(int[] input) {
        // This list will store all the valid solutions found.
        List<List<Integer>> allSolutions = new ArrayList<>();

        // This list will hold the solution being built in the current recursive path.
        List<Integer> currentSolution = new ArrayList<>();

        // Start the backtracking process from the beginning.
        backtrack(allSolutions, currentSolution, input, 0); // Or other initial state

        return allSolutions;
    }

    /**
     * The core recursive backtracking function.
     *
     * @param allSolutions   The master list to store every valid solution.
     * @param currentSolution The current solution being built.
     * @param input          The original input data (e.g., candidate numbers).
     * @param start          The current position or state to make a decision from.
     */
    private void backtrack(List<List<Integer>> allSolutions, List<Integer> currentSolution, int[] input, int start) {
        // === BASE CASE ===
        // The condition to check if the currentSolution is a complete and valid solution.
        // This condition will vary greatly depending on the problem.
        // (e.g., if currentSolution.size() == requiredSize, if sum == target, etc.)
        if (isSolutionCompleteAndValid(currentSolution)) {
            // A valid solution is found. Add a *copy* of it to the master list.
            // It's crucial to add a copy because currentSolution will be modified during backtracking.
            allSolutions.add(new ArrayList<>(currentSolution));
            // Depending on the problem, you might return here or continue searching for other solutions.
            // For com.problems like "find all subsets/permutations," you continue.
            // For "find one solution," you could return.
            return;
        }

        // === RECURSIVE STEP / CHOICES ===
        // Iterate through all possible "candidates" or "choices" for the next step.
        // The loop's starting point and end point will depend on the problem.
        for (int i = start; i < input.length; i++) {
            // You might have a condition to prune the search space.
            // if (isChoiceNotValid(input[i])) {
            //     continue; // Skip this choice
            // }

            // 1. CHOOSE
            // Add the current candidate to the solution path.
            currentSolution.add(input[i]);

            // 2. EXPLORE
            // Recursively call the function to explore further down this path.
            // The next starting position `i` or `i + 1` depends on whether candidates can be reused.
            // - Use `i + 1` for com.problems like Subsets or Combinations (prevents reusing the same element).
            // - Use `i` for com.problems where elements can be reused.
            backtrack(allSolutions, currentSolution, input, i + 1);

            // 3. UN-CHOOSE (BACKTRACK)
            // This is the most critical step. After exploring the path with the chosen candidate,
            // remove it from the current solution. This "resets" the state, allowing the loop
            // to explore the next candidate as if the previous one was never chosen.
            currentSolution.remove(currentSolution.size() - 1);
        }
    }

    /**
     * A placeholder for the problem-specific logic to determine if a solution is complete.
     * You would replace this with the actual condition for your problem.
     *
     * @param currentSolution The solution path being checked.
     * @return true if the solution is complete and valid, false otherwise.
     */
    private boolean isSolutionCompleteAndValid(List<Integer> currentSolution) {
        // Example for a problem that requires subsets of size 3:
        // return currentSolution.size() == 3;

        // For this generic template, we'll just return false so it explores all paths.
        // In a real problem, this would be meaningful.
        // For subset generation, the base case is often when the start index reaches the end of the input array.
        // In that case, this function might not even be needed, and the check would be `if (start == input.length)`.
        return false;
    }

    public static void main(String[] args) {
        BacktrackingProblemTemplate template = new BacktrackingProblemTemplate();

        // Example usage:
        // This template, as is, will not produce a meaningful result because the
        // isSolutionCompleteAndValid() method is just a placeholder.
        // To use it, you would adapt the base case and loop logic for a specific problem
        // like finding all subsets, permutations, etc.

        System.out.println("This is a template and requires problem-specific implementation.");
        System.out.println("See recursion.java for a concrete example of finding all subsets.");
    }
}
