package utilityclasses;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) {
        Properties p = new Properties();
        p.setProperty("User", "Akash123");
        p.setProperty("Password", "AKash@234");

        System.out.println("User:" + p.getProperty("User"));
        System.out.println("Password: " + p.getProperty("Password"));
    }
}
