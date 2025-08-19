package com.practice.stack;

import java.util.*;

public class EvaluatePostFix {

    private static final Map<String, Integer> operators = new HashMap<>();

    static {
        operators.put("+", 1);
        operators.put("-", 1);
        operators.put("*", 2);
        operators.put("/", 2);
    }
    public static void main (String [] args){
//        String [] exp = "1 + ( ( 2 + 3 ) * 4 + 5 ) * 6".split(" ");
        String [] exp = "2 + ( 4 + 3 * 2 + 1 ) / 3".split(" ");
//        String [] exp = "1 + ( 4 + 3 + 2 + 1 ) / 3".split(" ");
        inFixToPostFix(exp);
    }

    public static void inFixToPostFix(String [] a){
        List<String> result = new ArrayList<>();
        Stack<String> s = new Stack<>();
        for(String current : a){
            if(current.chars().allMatch(Character::isDigit)){
                result.add(current);
            } else if(operators.containsKey(current) && s.empty()) {
                s.push(current);
            } else if(operators.containsKey(current) && !s.empty()) {
                String temp = s.peek();
                boolean pop = true;
                while(pop && !s.empty()){
                    if(operators.containsKey(temp)
                            && operators.get(temp) >= operators.get(current)){
                        result.add(s.pop());
                        temp = s.peek();
                    } else {
                        pop = false;
                        s.push(current);
                    }
                }
            } else if(current.equals("(")){
                s.push(current);
            } else if(current.equals(")")){
                String temp = s.pop();
                while(!temp.equals("(")){
                    result.add(temp);
                    temp = s.pop();
                }
            }
        }
        while(!s.empty()){
           result.add(s.pop());
        }
        System.out.println(result);
    }

//    public static void postFix

}
