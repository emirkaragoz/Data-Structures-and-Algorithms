package Q4;

/**
 * Merge Sort Class
 */
public class MergeSort {
    /**
     * Merges right and left sequences
     * @param outputSequence merged output sequence
     * @param leftSequence left sequence
     * @param rightSequence right sequence
     * @param <T>  a comparable type
     */
    private static <T extends Comparable<T>> void merge(T[] outputSequence,
                                                        T[] leftSequence,
                                                        T[] rightSequence){
        int i=0,j=0,k=0;

        while (i<leftSequence.length && j<rightSequence.length){
            if (leftSequence[i].compareTo(rightSequence[j])<0)
                outputSequence[k++] = leftSequence[i++];
            else
                outputSequence[k++] = rightSequence[j++];
        }

        while (i<leftSequence.length)
            outputSequence[k++] = leftSequence[i++];

        while (j<rightSequence.length)
            outputSequence[k++] = rightSequence[j++];
    }

    /**
     * Merge Sort Method
     * @param table given array
     * @param <T> a comparable type of array
     */
    public static <T extends Comparable<T>> void mergeSort(T[] table){
        if (table.length>1){
            int halfSize = table.length/2;
            T[] leftTable = (T[]) new Comparable[halfSize];
            T[] rightTable = (T[]) new Comparable[table.length-halfSize];

            System.arraycopy(table,0,leftTable,0,halfSize);
            System.arraycopy(table,halfSize,rightTable,0,table.length-halfSize);

            mergeSort(leftTable);
            mergeSort(rightTable);

            merge(table,leftTable,rightTable);
        }
    }
}
