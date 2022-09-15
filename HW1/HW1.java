import java.util.InputMismatchException;
import java.util.Scanner;

public class HW1 {
    public static void main(String[] args){
        problem1();
        problem2();
    }

    private static void problem1() {
        Scanner input = new Scanner(System.in);
        int num1, num2;
        boolean cont=true;
        do {
            try {
                System.out.println("Enter first number: ");
                num1 = input.nextInt();

                System.out.println("Enter second number: ");
                num2 = input.nextInt();

                int sum = num1 + num2;
                System.out.println("Sum is: " + sum);
                cont=false;
            } catch (InputMismatchException IME) {
                System.out.println("Integer required for both inputs");
                input.nextLine();
            }
        }while(cont);
    }

    private static void problem2() {
        Scanner input = new Scanner(System.in);
        int[] arr= new int[100];

        for (int i=0;i<arr.length;i++) {
            arr[i] = (int)(Math.random()*100);
            System.out.println(arr[i]);
        }

        try {
            System.out.println("Enter index desired: ");
            int index = input.nextInt();
            System.out.println("The number at " + index + " is: " + arr[index]);
        } catch (ArrayIndexOutOfBoundsException AIOBE){
            System.out.println("Out of bounds");
        } catch (InputMismatchException IME){
            System.out.println("Number Required");
        }
    }
}
