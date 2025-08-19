
import java.util.*;


public class main {

    public static void main(String[] args) {
       for(int i = 0; i < 100; i++){
           System.out.println(i);
       }
    }

    public String addBinary(String a, String b) {
        if(a.length() < b.length()){
            String c = a;
            a = b;
            b = c;
        }
        int i = a.length() - 1;
        int j = b.length() - 1;

        Stack<Character> s = new Stack();

        char carry = '0';
        while(i >= 0){
            char chA = a.charAt(i);
            char chB;
            if(j < 0) chB = '0';
            else chB = b.charAt(j);

            Queue<Character> q = new ArrayDeque();
            q.add(chA);
            q.add(chB);
            q.add(carry);


            s.push(q.poll());
        }
        StringBuilder sb = new StringBuilder();
        while(!s.isEmpty()) sb.append(s.pop());
        return sb.toString();
    }

}
