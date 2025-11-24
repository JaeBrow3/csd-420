import java.util.Comparator;


public class BubbleSort {

    // Bubble sort using Comparable (E extends Comparable)
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean swapped;
        E temp;

        System.out.println("\nStarting Comparable bubble sort...");
        printArray(list);

        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;

            for (int j = 0; j < list.length - 1 - i; j++) {

                // Compare using compareTo
                if (list[j].compareTo(list[j + 1]) > 0) {

                    // Swap
                    temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;

                    System.out.println("Switch made at index " + j + " and " + (j+1));
                    printArray(list);
                    swapped = true;
                }
            }

            // If no swaps, list is sorted early
            if (!swapped) break;
        }

        System.out.println("\nComparable bubble sort complete!");
        printArray(list);
    }


    // Bubble sort using Comparator
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean swapped;
        E temp;

        System.out.println("\nStarting Comparator bubble sort...");
        printArray(list);

        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;

            for (int j = 0; j < list.length - 1 - i; j++) {

                // Compare using comparator
                if (comparator.compare(list[j], list[j + 1]) > 0) {

                    // Swap
                    temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;

                    System.out.println("Switch made at index " + j + " and " + (j+1));
                    printArray(list);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("\nComparator bubble sort complete!");
        printArray(list);
    }


    // Print array
    public static <E> void printArray(E[] arrayParam) {
        System.out.print("\narray = { ");
        for (E e : arrayParam) {
            System.out.print(e + " ");
        }
        System.out.println("}");
    }
}
