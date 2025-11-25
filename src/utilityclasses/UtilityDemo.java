package utilityclasses;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;

public class UtilityDemo {

    public static void main(String[] args) {

        // 1. Date - Current Date and Time
        Date d = new Date();
        System.out.println("Current Date & Time: " + d);

        // 2. Calendar - Adding 5 days and 1 month
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 5);
        c.add(Calendar.MONTH, 1);
        System.out.println("After Adding 5 Days & 1 Month: " + c.getTime());

        // 3. DateFormat - Formatting Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = sdf.format(d);
        System.out.println("Formatted Date: " + formattedDate);

        // 4. Properties - Using key-value data
        Properties p = new Properties();
        p.setProperty("appName", "Utility Demo App");
        p.setProperty("version", "1.0");
        System.out.println("App Name: " + p.getProperty("appName"));
        System.out.println("Version: " + p.getProperty("version"));

        // 5. Random - Generate random values
        Random r = new Random();
        System.out.println("Random Int (0-99): " + r.nextInt(100));
        System.out.println("Random Double (0-1): " + r.nextDouble());
        System.out.println("Random Boolean: " + r.nextBoolean());
    }
}