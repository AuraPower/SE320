import java.util.ArrayList;
import java.util.Scanner;

public class HW2 {

    public static void main(String[] args){
        problem1();
        problem2();
        problem3();
    }


    private static void problem1() {

    }
    private static void problem2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(14);
        list.add(24);
        list.add(14);
        list.add(42);
        list.add(25);

        ArrayList<Integer> newList = removeDuplicates(list);

        System.out.println(newList);
    }

    private static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> noDups = new ArrayList<>();
        for (E e : list) {
            if (!noDups.contains(e))
                noDups.add(e);
        }
        return noDups;
    }

    private static void problem3() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number between 1 and 9: ");
        int userInput = scanner.nextInt();
        assert userInput<10 && userInput>0: "The entered number is out of range";
    }
}
