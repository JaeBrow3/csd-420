import java.io.FileWriter; // Import FileWriter class
import java.io.IOException; // Import IOException class
import java.util.Random; // Import Random class

public class WriteFile {

    public static void main(String[] args) {
        Random rand = new Random();

        int[] intArray = new int[5];
        double[] doubleArray = new double[5];

        // Generate random integers
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = rand.nextInt(100) + 1; // 1–100
        }

        // Generate random doubles
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = Math.round((rand.nextDouble() * 100) * 100.0) / 100.0;
        }

        // Write data to file
        try (FileWriter writer = new FileWriter("JamesBrown datafile.dat", true)) {

            writer.write("Integers: ");
            for (int i : intArray) {
                writer.write(i + " ");
            }

            writer.write("\nDoubles: ");
            for (double d : doubleArray) {
                writer.write(d + " ");
            }

            writer.write("\n--------------------------\n");

            System.out.println("Data written successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }
    }
}
