import java.util.LinkedList;
import java.util.Iterator;

public class LinkedListTraversalTest {

    public static void main(String[] args) {

        // Test with 50,000 elements
        System.out.println("Testing with 50,000 elements:");
        testLinkedListTraversal(50_000);

        // Test with 500,000 elements
        System.out.println("\nTesting with 500,000 elements:");
        testLinkedListTraversal(500_000);
    }

    public static void testLinkedListTraversal(int numberOfElements) {
        LinkedList<Integer> list = new LinkedList<>();

        // Fill LinkedList
        for (int i = 0; i < numberOfElements; i++) {
            list.add(i);
        }

        // Traverse using Iterator
        long startTime = System.nanoTime();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        long endTime = System.nanoTime();
        long iteratorTime = endTime - startTime;

        // Traverse using get(index)
        startTime = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        endTime = System.nanoTime();
        long getTime = endTime - startTime;

        System.out.println("Traversal using Iterator: " + iteratorTime / 1_000_000.0 + " ms");
        System.out.println("Traversal using get(index): " + getTime / 1_000_000.0 + " ms");
    }
}

// Explaination of Results!
// When testing a LinkedList, using an iterator is much faster than using the get(index) method. This happens because a 
// LinkedList stores items as connected nodes, not in a row like an array. The iterator moves from one node to the next in 
// order, which only takes one pass through the list. But get(index) must start at the beginning every time it looks for an 
// item, so it takes much longer. As the list grows from 50,000 to 500,000 items, the iterator’s time increases a little, 
// while the get(index) time increases a lot, making it very slow for big lists.