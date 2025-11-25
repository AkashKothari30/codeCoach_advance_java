package utilityclasses;

public class StringBufferConstructorsDemo {
    public static void main(String[] args) {

        // 1. Using default constructor
        StringBuffer sb1 = new StringBuffer();
        sb1.append("Hello");
        System.out.println("Default Constructor: " + sb1);

        // 2. Using constructor with specified capacity
        StringBuffer sb2 = new StringBuffer(50);
        sb2.append("Java Programming");
        System.out.println("With Capacity 50: " + sb2);

        // 3. Using constructor with String
        StringBuffer sb3 = new StringBuffer("Welcome");
        sb3.append(" to Java");
        System.out.println("With String: " + sb3);
    }
}


