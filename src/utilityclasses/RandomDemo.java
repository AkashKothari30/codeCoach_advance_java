package utilityclasses;
import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        Random r = new Random();
        System.out.println("Ramdom Integer: " + r.nextInt());
        System.out.println("Random Double: " + r.nextDouble());
        System.out.println("Random Boolean: " + r.nextBoolean());
    }
}
