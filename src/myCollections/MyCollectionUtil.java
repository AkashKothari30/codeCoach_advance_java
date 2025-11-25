package myCollections;

import java.util.Arrays;
import java.util.List;

public class MyCollectionUtil {
    public static void main(String[] args) {
        Integer[] integerArray = {2, 4, 3, 7, 21, 9, 98, 76, 74};
        System.out.printf("IntegerArray elements : %s", Arrays.toString(integerArray));

        List<Integer> integerList = Arrays.asList(integerArray);
        System.out.println("\nInteger List: " + integerList);

        
        int[] baseArray = {2, 4, 3, 7, 21, 9, 98, 76, 74};
        Arrays.sort(baseArray);

        System.out.println("
        Sorted baseArray:");
        for (int ba : baseArray) {
            System.out.print(ba + " ");
        }

        
        int idx = Arrays.binarySearch(baseArray, 21);

        System.out.println("\n21 basearray position: " + idx);
    }
}
