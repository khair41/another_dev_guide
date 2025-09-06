package com.problems.stack.solutions;

import com.framework.Solution;
import com.problems.stack.problems.CarFleetProblem.CarFleetInput;

import java.util.Arrays;

public class CarFleetIterativeSolution implements Solution<CarFleetInput, Integer> {

    /*
     * --- APPROACH ---
     * The key is to process cars from closest to the target to furthest. Combine the
     * position and speed arrays into a single structure, then sort it by position in descending order.
     * Iterate through the sorted cars, calculate their arrival times, and count the fleets.
     *
     * --- INTUITION ---
     * A car can only form a fleet with cars ahead of it. By sorting the cars from closest to the
     * target backwards, we can determine if a car behind will catch up. If the car behind takes less
     * or equal time to arrive than the car ahead of it, it will catch up and join that fleet. If it
     * takes more time, it can never catch up and must form a new, slower fleet.
     *
     * The approach has the same logic, but then you realize that you are just looking at the last time of
     * the leader of the fleet, therefore you just update that value each iteration if a car cant reach the one in
     * front of it.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N log N)
     *     [The dominant operation is sorting the N cars.]
     *
     *   - Space: O(N)
     *     [We need to store the combined car data (position and speed) for sorting.]
     */

    @Override
    public Integer execute(CarFleetInput input) {
        int target = input.target();
        int [] position = input.position();
        int [] speed = input.speed();

        int n = position.length;
        int [][] cars = new int[n][2];

        for(int i = 0; i < position.length; i++){
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        // Get cars sorted by its position in the line
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));

        int fleetCount = 1;
        double prevTime = (double) (target - cars[0][0]) / cars[0][1]; // there is at least 1 car
        for(int i = 1; i < n; i++){
            double time = (double) (target - cars[i][0]) / cars[i][1];
            // if the time of the car that comes behind(we are processing backwards) is greater
            // it cannot reach the current fleet and, therefore, we create another fleet and update the time
            if(time > prevTime){
                fleetCount++;
                prevTime = time;
            }
        }
        return fleetCount;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N log N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N)";
    }
}
