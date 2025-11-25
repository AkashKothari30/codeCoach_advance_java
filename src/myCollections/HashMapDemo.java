package myCollections;

import java.util.HashMap;

public class HashMapDemo {

    public static void main(String[] args) {
        // Create a HashMap to demonstrate its functionality.
        HashMap<String, Integer> hashMap = new HashMap<>();

        // Add elements to the HashMap
        hashMap.put("Apple", 1);
        hashMap.put("Banana", 2);
        hashMap.put("Orange", 3);
        hashMap.put("Apple", 4); // Duplicate key, will update the value

        // Display the contents of the HashMap
        System.out.println("HashMap contents: " + hashMap);

        // Check if a key exists in the HashMap
        boolean containsApple = hashMap.containsKey("Apple");
        System.out.println("HashMap contains 'Apple': " + containsApple);

        // Get a value from the HashMap using a key
        Integer appleValue = hashMap.get("Apple");
        System.out.println("Value associated with 'Apple': " + appleValue);

        // Size of the HashMap
        System.out.println("Size of HashMap: " + hashMap.size());

        // Remove an element from the HashMap
        hashMap.remove("Banana");
        System.out.println("HashMap after removing 'Banana': " + hashMap);
    }

}