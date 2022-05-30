import java.util.*;

public class Main {

    public static String getDayOfWeekName(int number) {
        // write your code here
        String dayOfWeekName = null;
        switch (number) {
            case 1:
                dayOfWeekName = "Mon";
                break;
            case 2:
                dayOfWeekName = "Tue";
                break;
            case 3:
                dayOfWeekName = "Wed";
                break;
            case 4:
                dayOfWeekName = "Thu";
                break;
            case 5:
                dayOfWeekName = "Fri";
                break;
            case 6:
                dayOfWeekName = "Sat";
                break;
            case 7:
                dayOfWeekName = "Sun";
                break;
            default:
                throw new IllegalArgumentException();
        }
        return dayOfWeekName;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dayNumber = scanner.nextInt();
        try {
            System.out.println(getDayOfWeekName(dayNumber));
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
        }
    }
}