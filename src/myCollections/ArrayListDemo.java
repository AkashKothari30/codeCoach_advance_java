package myCollections;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("Hello");
        list.add("World");
        list.add(42);
        list.add(3.14);
        list.add(55);
        list.add("akash");

        System.out.println("ArrayList contents:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("Size of ArrayList: " + list.size());

        ArrayList<String> stringlist = new ArrayList<String>();

        stringlist.add("akash kothari");
        stringlist.add("akash kothari");
        stringlist.add("akash kothari");

        System.out.println("ArrayList string " + list.size());
        for(String str : stringlist){
            System.out.println(str);
        }
         System.out.println("Size of ArrayList string: " + stringlist.size());



    }


}
