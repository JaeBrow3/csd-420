import java.util.ArrayList;
import java.util.Random;

public class TestProgram {

    // Generic static method to remove duplicates
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> newList = new ArrayList<>();
        for (E element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> originalList = new ArrayList<>();
        Random rand = new Random();

        // 50 random values from 1 to 20
        for (int i = 0; i < 50; i++) {
            originalList.add(rand.nextInt(20) + 1);
        }

        // Remove duplicates
        ArrayList<Integer> uniqueList = removeDuplicates(originalList);

        // Display results
        System.out.println("Original List (50 random values):");
        System.out.println(originalList);
        System.out.println("\nList without duplicates:");
        System.out.println(uniqueList);
    }
}
