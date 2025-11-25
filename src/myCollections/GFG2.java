package myCollections;
    // Java program to demonstrate working of Collections.
// binarySearch() in an array sorted in descending order.
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GFG2 {
    public static void main(String[] args)
    {
        List<Integer> al = new ArrayList<Integer>();
        al.add(100);
        al.add(50);
        al.add(30);
        al.add(10);
        al.add(2);

        // The last parameter specifies the comparator
        // method used for sorting.
        int index = Collections.binarySearch(
            al, 50, Collections.reverseOrder());

        System.out.println("Found at index " + index);
    }
}

