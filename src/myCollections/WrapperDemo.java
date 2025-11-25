package myCollections;

import java.util.ArrayList;

public class WrapperDemo {
    public static void main(String[] args) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        Integer num1 = Integer.valueOf(100);
        intList.add(num1);
        
        intList.add(1);
        int i = 2;
        intList.add(i);
        Integer j = Integer.valueOf(3);
        intList.add(j);

        System.out.println("Integer ArrayList contents:");
        for (Integer num : intList) {
            System.out.println(num);
        }
        System.out.println("Size of Integer ArrayList: " + intList.size());
    }
    
}
