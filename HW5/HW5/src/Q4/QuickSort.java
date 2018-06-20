package Q4;

/**
 * Quick Sort Class
 */
public class QuickSort {

    /**
     * Quick Sort Wrapper Method
     * @param table given array
     * @param <T> a comparable array type
     */
    public static <T extends Comparable<T>> void quickSort(T[] table){
        sort(table,0,table.length-1);
    }

    /**
     * Quick Sort Method
     * @param table given array
     * @param low starting index
     * @param high ending index
     * @param <T> a comparable array type
     */
    private static <T extends Comparable<T>> void sort(T[] table, int low, int high){
        if (low<high){
            int pi = partition(table,low,high);

            sort(table, low, pi - 1);
            sort(table, pi + 1, high);
        }
    }

    /**
     * Partition Method
     * @param table given array
     * @param low starting index
     * @param high ending index
     * @param <T> a comparable array type
     * @return index
     */
    private static <T extends Comparable<T>> int partition(T[] table, int low, int high){
        T pivot = table[high];
        int i = (low - 1);

        for (int j = low; j <= high- 1; j++) {
            if (table[j].compareTo(pivot) <= 0) {
                i++;
                swap(table,i,j);
            }
        }
        swap(table, i + 1, high);
        return (i + 1);
    }

    /**
     * swap method
     * @param table given array
     * @param i index of the first element to be swapped
     * @param j index of the second element to be swapped
     * @param <T> a comparable array type
     */
    private static <T extends Comparable<T>> void swap(T[] table, int i, int j){
        T temp = table[i];
        table[i] = table[j];
        table[j] = temp;
    }
}
