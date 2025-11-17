import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;

public class Sorter {

    public static void main(String[] args) {
        readAndSortColors();
        testSortingLogic(); 
    }

    public static void readAndSortColors() {
        Set<String> colors = new TreeSet<>(); 

        try {
            File file = new File("collection_of_words.txt");
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                colors.add(input.next().toLowerCase());
            }

            input.close();

            System.out.println("\nAscending Order:");
            System.out.println(colors);

            ArrayList<String> reversed = new ArrayList<>(colors);
            Collections.reverse(reversed);

            System.out.println("\nDescending Order:");
            System.out.println(reversed);

        } catch (FileNotFoundException e) {
            System.out.println("Error: collection_of_words.txt not found.");
        }
    }

        public static void testSortingLogic() {
        System.out.println("\nRunning Test...");

        String[] sample = {"red", "green", "blue", "red", "yellow", "green"};
        Set<String> testSet = new TreeSet<>();

        for (String color : sample) {
            testSet.add(color);
        }

        System.out.println("Expected Ascending: [blue, green, red, yellow]");
        System.out.println("Actual Ascending:   " + testSet);

        ArrayList<String> reverse = new ArrayList<>(testSet);
        Collections.reverse(reverse);

        System.out.println("Expected Descending: [yellow, red, green, blue]");
        System.out.println("Actual Descending:   " + reverse);
    }
}
