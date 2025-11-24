import java.util.Comparator;

public class BubbleSortTest {

    public static void main(String[] args) {

        // Test 1: Comparable (Integer)
        Integer[] nums = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};
        System.out.println("\n===== TEST 1: Comparable(Integer) =====");
        BubbleSort.bubbleSort(nums);

        // Test 2: Comparator (String reverse alphabetical)
        String[] words = {"banana", "apple", "pear", "kiwi"};
        System.out.println("\n===== TEST 2: Comparator(String) =====");
        BubbleSort.bubbleSort(words, Comparator.reverseOrder());
    }
}


