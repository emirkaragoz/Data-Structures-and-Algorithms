package Q5;

import Q3.MergeSortDLL;
import Q4.*;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Worst Case Run Time Class
 */
public class WorstCaseRunTime {
    /** array of array sizes*/
    private final static int [] arrSizes = {100,1000,5000,10000};

    /**
     * Main method Q5
     * @param args command line arguments
     */
    public static void main(String ... args){

        Integer arr[][] = new Integer[4][];
        for (int i=0 ; i<4 ;++i)
            arr[i] = new Integer[arrSizes[i]];

        for (int i=0 ; i<4 ;++i)
            for (int j=arrSizes[i]-1, n=0 ; j>=0 ; --j,++n)
                arr[i][j] = n;

        System.out.println("\nInsertion Sort");
        for (int i=0 ; i<4 ; ++i) {
            long before = System.nanoTime();
            InsertionSort.insertionSort(arr[i].clone());
            System.out.println("Array Size: " + arr[i].length +" | Time: "+(System.nanoTime() - before) / 1000000 + " ms");
        }

        System.out.println("\nMerge Sort");
        for (int i=0 ; i<4 ; ++i) {
            long before = System.nanoTime();
            MergeSort.mergeSort(arr[i].clone());
            System.out.println("Array Size: " + arr[i].length +" | Time: "+(System.nanoTime() - before) / 1000000 + " ms");
        }

        System.out.println("\nMerge Sort with DLL");
        for (int i=0 ; i<4 ; ++i) {
            LinkedList ll = new LinkedList();
            ll.addAll(Arrays.asList(arr[i]).subList(0, arrSizes[i]));

            long before = System.nanoTime();
            MergeSortDLL.mergeSort(ll);
            System.out.println("Array Size: " + arr[i].length +" | Time: "+(System.nanoTime() - before) / 1000000 + " ms");
        }

        System.out.println("\nHeap Sort");
        for (int i=0 ; i<4 ; ++i) {
            long before = System.nanoTime();
            HeapSort.heapSort(arr[i].clone());
            System.out.println("Array Size: " + arr[i].length +" | Time: "+(System.nanoTime() - before) / 1000000 + " ms");
        }

        System.out.println("\nQuick Sort");
        for (int i=0 ; i<4 ; ++i) {
            Integer quickArr[] = new Integer[arrSizes[i]];
            if (i==3) {
                quickArr = new Integer[7000];
                System.arraycopy(arr[i], 0, quickArr, 0, 7000);
            }
            else
                quickArr = arr[i].clone();

            long before = System.nanoTime();
            QuickSort.quickSort(quickArr);
            System.out.println("Array Size: " + arr[i].length +" | Time: "+(System.nanoTime() - before) / 1000000 + " ms");
        }
    }
}
