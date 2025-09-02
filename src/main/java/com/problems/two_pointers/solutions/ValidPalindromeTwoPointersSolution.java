package com.problems.two_pointers.solutions;

import com.framework.Solution;

public class ValidPalindromeTwoPointersSolution implements Solution<String, Boolean> {

    /*
     * --- APPROACH ---
     * Use 2 pointers to traverse the String, one at the beginning of the string, the other once at the end.
     * Both meeting in the middle
     *
     * --- INTUITION ---
     * Two pointers work because besides knowing that the count of the characters need to be equal, they have to be
     * in the same order when reading it backwards.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *     Since we need to go over half of the String
     *
     *   - Space: O(1)
     *     Since variable declaration does not constitute extra space
     */

    @Override
    public Boolean execute(String s) {
        int l = 0;
        int r = s.length() - 1;

        if(r == 0) return true;

        while(l < r){
            while(!Character.isLetterOrDigit(s.charAt(l))){
                l++;
            }
            while(!Character.isLetterOrDigit(s.charAt(r))){
                r--;
            }
            if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))){
                return false;
            }
            l++; r--;
        }
        return true; 
    }

    @Override
    public String getTimeComplexity() {
        return "O(N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}
