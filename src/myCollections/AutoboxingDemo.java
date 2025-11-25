package myCollections;

public class AutoboxingDemo {
    public static void main(String[]args){
        Integer i = Integer.valueOf(10);

        int i1 = i;

        System.out.println("Value of Interger : "+ i);
        System.out.println("Value of Interger : "+ i1);

        Character gfg = 'a';

        char ch = gfg;
        System.out.println("value of ch" + ch);
        System.out.println("value of gfg" + gfg);
    }
}
