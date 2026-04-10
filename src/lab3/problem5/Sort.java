package lab3.problem5;

public class Sort {
    static <E> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <E extends Comparable<E>> void siftDown(E[] array, int start, int end) {
        int root = start;

        while (2 * root + 1 <= end) {
            int j = 2 * root + 1;

            if (j < end && array[j].compareTo(array[j + 1]) < 0) {
                j++;
            }

            if (array[root].compareTo(array[j]) < 0) {
                swap(array, root, j);
                root = j;
            } else {
                break;
            }
        }
    }

    public static <E extends Comparable<E>> void heapSort(E[] array) {
        int n = array.length;
        if (n <= 1) return;

        // heapify
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(array, i, n - 1);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            siftDown(array, 0, i - 1);
        }
    }
}