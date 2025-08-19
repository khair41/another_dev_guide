import java.util.Arrays;
import java.util.List;

public class IsValidSubsequence {

    public static void main(String [] args){
        isValidSubsequence(Arrays.asList(new Integer []{5, 1, 22, 25, 6, -1, 8, 10}), Arrays.asList(new Integer[]{1, 6, -1, 10}));

    }


    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        int arrPos = 0;
        int seqPos = 0;

        while(arrPos < array.size() && seqPos < sequence.size()){
            if(array.get(arrPos) == sequence.get(seqPos)){
                arrPos++;
                seqPos++;
            } else {
                arrPos++;
            }
        }

        return seqPos == sequence.size() - 1 ? true : false;
    }
}
