// import java.util.*;

public class BinarySearch {
    public static void main(String [] args){
        int [] array = {0,1,21,33,45,45,61,71,72,73};
//        array.hashCode()
        int target = -33;
        // Queue<Integer> q = new ArrayDeque<>();

        // Map<int[] , Integer> test = new HashMap<>();
        System.out.println(Integer.toString(target));

        System.out.println(bs(0, array.length - 1, array, target));
    }

    public static int bs(int left, int right, int [] array, int target){
        if(left > right) return -1;
        int mid = (right + left) / 2;
        if(array[mid] == target) {
            return mid;
        } else if(array[mid] < target) {
            return bs(mid - 1, right, array, target);
        } else {
            return bs(left, mid + 1, array, target);
        }

    }
}
