package utilityclasses;
import java.util.Calendar;

public class CalendarDemo {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 5);
        c.add(Calendar.MONTH, 2);
        
        System.out.println(c.getTime());
    }
}
