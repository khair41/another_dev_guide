package com.problems.two_pointers.solutions;

import com.framework.Solution;

import java.util.*;

public class ThreeSumHashMapSolution implements Solution<int[], List<List<Integer>>> {

    /*
     * --- APPROACH ---
     * The main idea is to iterate over the array with 2 pointers i and j, once we carefully remove duplicates
     * its matter of a basic operation to attempt to find the remaining element x = -(nums[i] + nums[j]) since we can
     * store the counts for each element in a HashMap. We can do the last operation in O(1) time.
     *
     * --- INTUITION ---
     * By fixing one number (`nums[i]` and `nums[j]`), the problem is reduced to finding the remaining element.
     * The HashSet provides an O(1) average time lookup for the third number, making the inner loop O(n).
     * The overall complexity becomes O(n^2).
     * A key challenge is avoiding duplicate triplets, which requires careful management of the seen numbers.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N^2)
     *     A nested loop structure where the outer loop runs N times
     *     and the inner loop also runs N times. HashSet operations are O(1) on average.
     *
     *   - Space: O(N)
     *     The space is used by the HashSet to store complements.
     */

    @Override
    public List<List<Integer>> execute(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        Map<Integer, Integer> counts = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for(int num : nums){
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        for(int i = 0; i < n; i++){
            counts.put(nums[i], counts.get(nums[i]) - 1);
            // first iteration it takes the num as a posibility
            // next iteration is skiped untill we find a new element to consider
            if(i > 0 && nums[i] == nums[i - 1]) continue;

            for(int j = i + 1; j < n; j++){
                // we do the same in inner loop to catch the repeated element in any case
                // i.e -1 -1 -1 1 1 1 2 2 2
                //      i  j
                // j will take care of skipping repeated nums
                // i will take care of skipping nums on the next iteration
                if(j > j + 1 && nums[j] == nums[j - 1]) continue;

                // reduce the count to acknowledge we have already on curr num
                counts.put(nums[j], counts.get(nums[j]) - 1);

                // this is the complement
                // 1 + 2 + x = 0 -> x = -1 - 2 -> x = -(1 + 2) -> x = -3
                int tmp = -(nums[i] + nums[j]);

                // if a num exists and its count is greater than 0
                if(counts.getOrDefault(tmp, 0) > 0){
                    res.add(Arrays.asList(nums[i], nums[j], tmp));
                }
            }

            // we have reduced the counts of visited elements so we need to add them back for next iteration
            for(int j = i + 1; j < n; j++){
                counts.put(nums[j], counts.get(nums[j]) + 1);
            }
        }
        return res;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N^2)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N)";
    }
}
