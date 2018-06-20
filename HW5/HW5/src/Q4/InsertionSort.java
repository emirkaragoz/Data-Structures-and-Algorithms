package Q4;

/**
 * Insertion Sort Class
 */
public class InsertionSort {
    /**
     * Insertion Sort Method
     * @param arr given array
     * @param <T> a comparable array type
     */
    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
        for (int i=1; i<arr.length; ++i)
        {
            T key = arr[i];
            int j = i-1;

            while (j>=0 && arr[j].compareTo(key) > 0) {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }
}
