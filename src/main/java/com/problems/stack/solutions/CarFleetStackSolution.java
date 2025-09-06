package com.problems.stack.solutions;

import com.framework.Solution;
import com.problems.stack.problems.CarFleetProblem.CarFleetInput;

import java.util.Arrays;
import java.util.Stack;

public class CarFleetStackSolution implements Solution<CarFleetInput, Integer> {

    /*
     * --- APPROACH ---
     * Sort cars by their starting position in descending order. Iterate through the sorted cars,
     * calculate each car's arrival time at the target, and push this time onto a stack.
     * If the current car's arrival time is greater than the arrival time of the car at the top of the stack,
     * it means this car forms a new fleet. Otherwise, it will catch up to the fleet ahead.
     *
     * --- INTUITION ---
     * By processing cars from farthest to the target, we can use the stack to maintain
     * the arrival times of the *leaders* of the fleets. If a car behind arrives later than the current
     * fleet leader, it forms a new fleet. If it arrives earlier or at the same time, it joins the current fleet.
     * The stack effectively keeps track of the distinct fleet arrival times.
     *
     * You have 2 cars, 1 behind the other, when you compare the arrival time, you
     * can know if they are gonna be on the same carfleet or not by simple comparing
     * the arrival time. If the car behind another is gonna take 5min to arrive to target and
     * the one in front is going to take 3min to arrive to target, no matter what, the car behind
     * is not going to reach the car in front, therefore the car behind can be considered a separate fleet.
     *
     * You dont need to consider the distance the cars need to travel because you already have
     * calculated the respective arrival time and that is unique. Absolute arrival time (because
     * the distance is already computed)
     *
     * if the last car we just added reaches is slower than the one before it
     * try running a simulation of two cars in a single line
     *
     *   In the graphic, the cars look like this after sorted by position:
     *
     *   -------7---------10----|(12) target
     *   ... *(t=2)     *(t=3)
     *
     *   In the array, the positions looks like this because we want the closest to the target
     *   [(10,x), (7,x)... ]
     *
     * so you start processing the sorted cars and start with 10 then we store its time
     * s = [3]
     * then we push the next car at pos 7
     * s = [3, 2]
     * now we check that the car we just added will arrive before, therefore becomes a fleet
     * and we keep just the time of the leader of the fleet
     * s = [3]
     *
     * Any time that is less than that becomes part of the fleet, otherwise becomes the last fleet we can use
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N log N)
     *     [The dominant operation is sorting the N cars. The stack operations are O(1) on average.]
     *
     *   - Space: O(N)
     *     [We need to store the combined car data for sorting and the stack can hold up to N arrival times.]
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

        Stack<Double> s = new Stack<>();
        for(int[] car : cars){
            // [V = d / t] => [t = d / V]
            double time = (double) (target - car[0]) / car[1];
            s.push(time);
            if(s.size() >= 2){
                if(s.get(s.size() - 2) >= s.peek()){
                    s.pop();
                }
            }
        }
        return s.size();

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
