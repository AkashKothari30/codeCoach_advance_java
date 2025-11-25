package utilityclasses;
import java.lang.StringBuilder;

public class StringBuilderConstructorsDemo {
    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Hello");
        System.out.println("sb1: " + sb1);

        StringBuilder sb2 = new StringBuilder(50);
        sb2.append("This has initial capacity 50");
        System.out.println("sb2: " + sb2);

        StringBuilder sb3 = new StringBuilder("Akash");
        sb3.append("Kothari");
        System.out.println("sb3: " + sb3);

        CharSequence cs = "Java";
        StringBuilder sb4 = new StringBuilder(cs);
        sb4.append("Programming");
        System.out.println("sb4: " + sb4);
    }
}
    
     
