package myCollections;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetDemo2 {
    public static void main(String[] args) {
        HashSet bollywoodHS = new HashSet<>();
        bollywoodHS.add("Sallu Bhai");
        bollywoodHS.add("Ajju");
        bollywoodHS.add("Hakku");
        bollywoodHS.add("Katrina");
        bollywoodHS.add("Hakku");

        Iterator itr = bollywoodHS.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }

    }
}
