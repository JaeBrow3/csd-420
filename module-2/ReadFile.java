import java.io.File; // Import File class
import java.io.FileNotFoundException; // Import FileNotFoundException class
import java.util.Scanner; // Import Scanner class

public class ReadFile {

    public static void main(String[] args) {

        File file = new File("JamesBrown datafile.dat");

        try (Scanner scanner = new Scanner(file)) {

            System.out.println("File Contents:\n");

            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
