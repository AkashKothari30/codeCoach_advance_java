package myCollections;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("orange");
        hashSet.add("Apple");
        

        System.out.println("HashSet Contains " + hashSet);

        boolean containsApple = hashSet.contains("Apple");
        System.out.println("HashSet contains 'Apple :" + containsApple);

        System.out.println("Accesing elements in HashSet:");
        for(String fruit : hashSet){
            System.out.println(fruit);
        }

        //iterator method
        Iterator itr = hashSet.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        System.out.println("Size of HashSet :" + hashSet.size());

        hashSet.remove("Banana");
        System.out.println("HashSet after Removing 'Bnana "+ hashSet);
    }
}
