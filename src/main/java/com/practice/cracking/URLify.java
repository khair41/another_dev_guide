package com.practice.cracking;

/*
* Write a method to replace all spaces in a string with '%20'.
* You may assume that the string has sufficient space at the end to hold the additional characters,
*  and that you are given the "true" length of the string.
* (Note: If implementing in Java, please use a character array so that you can perform this operation in place.)
*
* Input: "Mr John Smith   ", 13
* Output: "Mr%20John%20Smith"
*
* */

import java.util.Arrays;

public class URLify {

    public static void main(String[] args) {
        String s = "Mr Jhon Smith    ";
        int trueLength = 13;

        char [] chars = s.toCharArray();
        int lastIndex = chars.length - 1;
        for(int i = trueLength - 1; i >= 0; i--){
            if(chars[i] != ' '){
                chars[lastIndex] = chars[i];
                lastIndex--;
            } else {
                chars[lastIndex] = '0';
                chars[lastIndex - 1] = '2';
                chars[lastIndex - 2] = '%';
                lastIndex = lastIndex - 3;
            }
        }

        System.out.println(Arrays.toString(chars));
    }
}
