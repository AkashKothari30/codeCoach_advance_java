package myCollections;
import java.util.LinkedHashSet;

public class Geeks {
    public static void main(String[] args) {
      
        // Create a LinkedHashSet of Strings
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple");
        System.out.println("" + set);
    }
}