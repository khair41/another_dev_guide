package com.problems.stack.solutions;

import com.framework.Solution;

import java.util.LinkedList;

public class EvaluateReversePolishNotationLinkedListSolution implements Solution<String[], Integer> {

    /*
     * --- APPROACH ---
     * Use a Doubly Linked List helps us run a simulation at a node level to understand what happens in the stack.
     * We process an operation and update the pointers to match the behavior of the stack
     *
     * --- INTUITION ---
     * We update the pointer of the head when we find an operator, we then check for the past 2 nodes to evaluate
     * the operation. Update the value of the head then update the pointers.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N) Since we need to create the DLL
     *
     *   - Space: O(N) Since we need to store the tokens in the DLL
     */

    public class DLL {
        String val;
        DLL next;
        DLL prev;

        DLL(String val, DLL next, DLL prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Integer execute(String[] tokens) {
        DLL head = new DLL(tokens[0], null, null);
        DLL curr = head;
        for (int i = 0; i < tokens.length; i++) {
            curr.next = new DLL(tokens[i], null, curr);
            curr = curr.next;
        }

        int ans = 0;
        while(head != null){
            if("+-*/".contains(head.val)){
                int left =  Integer.parseInt(head.prev.prev.val);
                int right = Integer.parseInt(head.prev.val);
                // 1 2 + 3 - 4
                int res = 0;
                if(head.val.equals("+")) res = left + right;
                if(head.val.equals("-")) res = left - right;
                if(head.val.equals("*")) res = left * right;
                if(head.val.equals("/")) res = left / right;

                head.val = String.valueOf(res);

                /* considering a notation of 1 3 4 / +
                 is possible that we have nested operations, not a normal single level operations
                 this means that we need to update the pointers to accommodate deeper nested operations
                 the loop will navigate until we find the first operator to make the operations, after that
                 there are 2 cases, either is a simple one level operation 1 2 +
                 or the operation is nested therefor we need to update the pointers
                 1  2  3  4  +  -  +
                            h^ head pointer stops here
                      l^ r^
                 we then compute operation and update head (4 + 3)
                 1  2  3  4  7  -  +
                             ^h head remains here but now 3 and 4 does not exist anymore

                 so we make h.prev point to the immediate prev valid num
                 1  2  3  4  7  -  +
                    ^  <  <  ^h
                 thats why we jump back 3 prev pointers
                 this is still valid when there is just one level of nested operation i.e: 1 2 +
                 but here head.prev is null

                 final step is to change the pointer of the updated prev, if it's still valid
                 we update the last valid prev to the current head
                 1  2  _  _  7  -  +
                    ^  -  -  ^h now h.prev points to 2 and 2.next points to head
                 */
                head.prev = head.prev.prev.prev;
                if(head.prev != null){
                    head.prev.next = head;
                }

                ans = res;
            }

            head = head.next;
        }


        return ans;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N) Since we need to create the DLL";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N) Since we need to store the tokens in the DLL";
    }
}
