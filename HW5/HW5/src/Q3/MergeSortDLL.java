package Q3;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Merge Sort with Double Linked List
 */
public class MergeSortDLL {

    /**
     * Merges right and left sequences
     * @param outputSequence merged output sequence
     * @param leftSequence left sequence
     * @param rightSequence right sequence
     * @param <T>  a comparable type of linked list
     */
    private static <T extends Comparable<T>> void merge(LinkedList<T> outputSequence,
                                                        LinkedList<T> leftSequence,
                                                        LinkedList<T> rightSequence){

        ListIterator<T> leftIt = leftSequence.listIterator();
        ListIterator<T> rightIt = rightSequence.listIterator();
        outputSequence.clear();

        while (leftIt.hasNext() && rightIt.hasNext()){
            T tmpLeft = leftIt.next();
            T tmpRight = rightIt.next();
            if (tmpLeft.compareTo(tmpRight)<0) {
                outputSequence.add(tmpLeft);
                rightIt.previous();
            }
            else {
                outputSequence.add(tmpRight);
                leftIt.previous();
            }
        }

        while (leftIt.hasNext())
            outputSequence.add(leftIt.next());

        while (rightIt.hasNext())
           outputSequence.add(rightIt.next());

    }

    /**
     * Merge Sort Method
     * @param table given table
     * @param <T> a comparable type of linked list
     */
    public static <T extends Comparable<T>> void mergeSort(LinkedList<T> table){
        if (table.size()>1){
            int halfSize = table.size()/2;
            LinkedList<T> leftTable = new LinkedList<>();
            LinkedList<T> rightTable = new LinkedList<>();

            ListIterator<T> it = table.listIterator();

            for (int i= 0; i<halfSize;++i)
                leftTable.add(it.next());

            while (it.hasNext())
                rightTable.add(it.next());

            mergeSort(leftTable);
            mergeSort(rightTable);

            merge(table,leftTable,rightTable);
        }
    }
}
