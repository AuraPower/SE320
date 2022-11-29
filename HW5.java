import Day;
import java.time.LocalDate;


public class HW5 {
    public static void main(String[] args){

        problem1();
        problem2();
        problem3();

    }

    private static int problem1() {
        Day birthday = new Day(1999, 8, 16);
        LocalDate tld = LocalDate.now(); // Today from Local Date class
        Day today = new Day(tld.getYear(), tld.getMonth(), tld.getDayOfMonth());
        int daysBetween = today.daysFrom(birthday);

        return daysBetween;

    }
    private static void problem2() {
        /*
          requires: lst contains duplicates\
         */

    }
    private static void problem3() {

        /*
        return this.lastName.toUpperCase().hashcode();
         */

    }
}
