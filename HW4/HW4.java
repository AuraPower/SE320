import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class HW4 {
    public static void main(String[] args){
        problem1();
        problem2();
        problem3();
    }

    //I know this looks ugly, but I'm tired and it works
    private static void problem1() {
        String[] ImLazy = {"George", "Jim", "John", "Blake", "Kevin", "Michael"};
        String[] ImLazy2 = {"George", "Katie", "Kevin", "Michelle", "Ryan"};
        LinkedHashSet<String> nameSet1 = new LinkedHashSet<>(Arrays.asList(ImLazy));
        LinkedHashSet<String> nameSet2 = new LinkedHashSet<>(Arrays.asList(ImLazy2));

        nameSet1.addAll(nameSet2);//union
        System.out.println(nameSet1);
        nameSet1 = new LinkedHashSet<>(Arrays.asList(ImLazy));
        nameSet1.retainAll(nameSet2);//intersection
        System.out.println(nameSet1);
        nameSet1 = new LinkedHashSet<>(Arrays.asList(ImLazy));
        nameSet1.removeAll(nameSet2);//difference
        System.out.println(nameSet1);
    }

    private static void problem2() {
        TreeSet<String> noDupeNames = new TreeSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("names.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                noDupeNames.add(line);
            }
            System.out.println(noDupeNames);
        } catch (IOException IOE) {
            System.out.println("File not found");
        }
    }

    private static void problem3(){
        //A
        //Write the code to format number 12345.678 in the United Kingdom locale. Keep two digits after the decimal point.
        double numUK = 12345.678;
        NumberFormat nfUK = NumberFormat.getNumberInstance(Locale.UK);
        String stringUK = nfUK.format(numUK);
        System.out.println(stringUK);

        //B
        //Write the code to format number 12345.678 in U.S. currency.
        double numUS =12345.678;
        NumberFormat cfUS = NumberFormat.getCurrencyInstance(Locale.US);
        String currencyUS = cfUS.format(numUS);
        System.out.println(currencyUS);

        //C
        //Write the code to parse '12,345.678' into a number.
        NumberFormat nf = NumberFormat.getNumberInstance();
        try {
            double parsedNum = (double) nfUK.parse("12,345.678");
            System.out.println(parsedNum);
        }catch (ParseException PE){
            System.out.println("Number not parsable");
        }


    }
}
