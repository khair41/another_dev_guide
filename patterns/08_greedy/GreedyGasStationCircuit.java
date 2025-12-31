package com.framework.patterns.greedy;

/**
 * Pattern 56: Greedy - Gas Station Circuit
 */
public class GreedyGasStationCircuit {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: Two arrays, `gas` and `cost`, representing a circular route.
     *     `gas[i]` is the amount of gas available at station `i`, and `cost[i]` is the cost to
     *     travel from station `i` to `i+1`.
     *
     * 2.  Problem Goal: You need to find a starting gas station index from which you can complete
     *     a full circle tour around the track. There is at most one such solution.
     *
     * 3.  Logic: This problem has two key greedy insights.
     *     -   **Insight 1 (Global Feasibility)**: If the total amount of gas available in the entire
     *         circuit is less than the total cost to travel the entire circuit, then no solution is
     *         possible. `sum(gas) < sum(cost)` means failure.
     *     -   **Insight 2 (Local Infeasibility)**: If you start at station `A` and run out of gas
     *         before reaching station `B`, then no station between `A` and `B` (inclusive) can be a
     *         valid starting point. This is because if you started at any of those intermediate
     *         stations, you would have had even less gas when leaving `A`, and would have failed
     *         at the same point or earlier.
     *
     *     -   **Algorithm**:
     *         1.  Keep track of `total_tank` (for the global check) and `current_tank` (for the local check).
     *         2.  Initialize a `start_station` index to 0.
     *         3.  Iterate through the stations. For each station `i`:
     *             -   Update both `total_tank` and `current_tank` with `gas[i] - cost[i]`.
     *             -   If `current_tank` drops below zero, it means you cannot reach the next station from
     *               the current `start_station`. Based on Insight 2, you know the new start must be
     *               at least `i+1`. So, reset `start_station = i + 1` and reset `current_tank = 0`.
     *         4.  After the loop, if `total_tank` is non-negative, a solution exists, and it is the
     *             `start_station` you found. Otherwise, no solution exists.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Gas Station)
     * =================================================================================
     */

    /**
     * Finds the starting gas station's index if you can travel around a circuit once.
     *
     * @param gas An array of gas amounts at each station.
     * @param cost An array of costs to travel to the next station.
     * @return The index of the starting gas station, or -1 if no solution exists.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // --- Core Pattern Logic: Insight 1 - Global check ---
        int totalGas = 0, totalCost = 0;
        for (int g : gas) totalGas += g;
        for (int c : cost) totalCost += c;
        if (totalGas < totalCost) {
            return -1;
        }
        // -----------------------------------------------------

        int startStation = 0;
        int currentTank = 0;

        // --- Core Pattern Logic: Insight 2 - Local check ---
        for (int i = 0; i < gas.length; i++) {
            currentTank += gas[i] - cost[i];

            // --- Greedy Choice: If we can't make it to the next station ---
            if (currentTank < 0) {
                // This means no station from the previous `startStation` up to `i` can be a start.
                // So, the new potential start is `i + 1`.
                startStation = i + 1;
                // Reset the tank for the new journey.
                currentTank = 0;
            }
            // -----------------------------------------------------------
        }
        // -----------------------------------------------------

        // If a solution exists, the `startStation` found is the one.
        return startStation;
    }
}
