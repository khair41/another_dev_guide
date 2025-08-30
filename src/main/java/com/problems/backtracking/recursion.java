package com.problems.backtracking;

/*
The objective of this class is to practice recursion
*/

import java.util.*;

public class recursion {
    static List<List<Integer>> subsets = new ArrayList<>();
    static List<Integer> subset = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(sumArray(new int[]{1,2,3}));
        System.out.println(reverseString("abcde"));
        System.out.println(isPalindrome("aaaavvaaa"));
        System.out.println(decimalToBinary(17));
        System.out.println(power(2, 10));

        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.add(5);
        queue.add(1);
        queue.add(3);
        queue.add(2);
        queue.add(4);
        System.out.println("Original queue: " + queue);
        sortQueue(queue);
        System.out.println("Sorted queue: " + queue);

        System.out.println(binarySearchExists(new int[]{1,2,3,4,5}, 6));
        System.out.println(binarySearchPos(new int[]{1,2,3,4,5}, 5));

        printAllSubsets(new int[]{1,2,3,4});
        System.out.println(subsets);
    }

    // 1. Sum of an array (recursive)
    public static int sumArray(int[] arr) {
        if(arr.length == 1) return arr[0];
        return sumArray(Arrays.copyOfRange(arr, 1, arr.length)) + arr[0];
    }

    // 2. Reverse a string (recursive)
    public static String reverseString(String s) {
        if(s.length() == 1) return s;
        return s.charAt(s.length() - 1) + reverseString(s.substring(0, s.length() - 1));
    }

    // 3. Check if a string is palindrome (recursive)
    public static boolean isPalindrome(String s) {
        if(s.length() <= 1) return true;
        if(s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    // 4. Convert a decimal to a binary number (recursive, returns String)
    public static String decimalToBinary(int n) {
        if(n == 0) return "";
        return decimalToBinary(n / 2) + n % 2;
    }

    // 5. Power function to calculate a^b (recursive)
    public static int power(int a, int b) {
        if(b == 1) return a;
        return power(a, b - 1) * a;
    }

    // 6. Sort a queue (recursive, ascending order)
    public static void sortQueue(java.util.Queue<Integer> queue) {
        // Base case: If the queue is empty, it's considered sorted.
        if (queue.isEmpty()) return;
        int frontElement = queue.poll();
        sortQueue(queue);
        sortedInsert(queue, frontElement);
    }

    private static void sortedInsert(Queue<Integer> queue, int element) {
        if (queue.isEmpty() || element <= queue.peek()) {
            queue.add(element);
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.add(queue.poll());
            }
            return;
        }
        int temp = queue.poll();
        sortedInsert(queue, element);
        queue.add(temp);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
    }

     // 7. Binary search Exists (recursive)
     public static boolean binarySearchExists(int[] arr, int target) {
        if(arr.length == 0) return false;
        int mid = arr.length / 2;
        if(arr[mid] == target) return true;
        if(arr[mid] < target) return binarySearchExists(Arrays.copyOfRange(arr, mid + 1, arr.length), target);
        else return binarySearchExists(Arrays.copyOfRange(arr, 0, mid), target);
     }
    // 7.1 Binary search Position(recursive)
    public static int binarySearchPos(int[] arr, int target) {
        return bsHelper(arr, 0, arr.length - 1, target);
     }

     static int bsHelper(int [] arr, int l, int r, int target){
        int mid = l + (r - l) / 2;
        if(arr[mid] == target) return mid;
        if(arr[mid] < target) return bsHelper(arr, mid + 1, r, target);
        else return bsHelper(arr, l, mid - 1, target);
     }

    // 8. Print all subsets of a given array (recursive)
    public static void printAllSubsets(int[] arr) {
        subsetsRec(arr, 0);
    }

    static void subsetsRec(int[] arr, int i){
        subsets.add(new ArrayList<>(subset));
        for(int j = i; j < arr.length; j++) {
            subset.add(arr[j]);
            subsetsRec(arr, j + 1);
            subset.remove(subset.size() - 1);
        }

    }


}
