package Q4;

import Q3.MergeSortDLL;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * Average Run Time Class
 */
public class AverageRunTime {
    /** There are 10 different array size*/
    private final static int numberOfSize = 10;

    /**
     * Main Method Of Q4
     * @param args command line arguments
     */
    public static void main(String ... args){
        Random rand = new Random();

        int [] arrSizes = new int[numberOfSize];

        for (int i=0 ; i<numberOfSize ; ++i)  //determine sizes of arrays
            arrSizes[i] = 2000*(i+1);

        //initialise and allocate arrays
        Integer [][][] allArrs = new Integer[numberOfSize][numberOfSize][];
        for (int i=0 ; i<numberOfSize ; ++i)
            for (int j=0 ; j<10 ; ++j)
                allArrs[i][j] = new Integer[arrSizes[i]];

        //random fill arrays
        for (int i=0 ; i<numberOfSize ; ++i)
            for (int j=0 ; j<10 ; ++j)
                for (int k=0 ; k<arrSizes[i] ; ++k)
                    allArrs[i][j][k] = rand.nextInt(1000);

        System.out.println("Insertion Sort");
        for (int i=0 ; i<numberOfSize ; ++i) {
            Integer [][] insertionArr = allArrs[i].clone();
            for (int j=0 ; j<numberOfSize ; ++j)
                insertionArr[j] = allArrs[i][j].clone();

            System.out.print("Array Size: " + insertionArr[i].length + "| Times(ms) ");

            for (int k=0 ; k<10 ; ++k) {
                long before = System.nanoTime();
                InsertionSort.insertionSort(insertionArr[k]);
                System.out.print((System.nanoTime() - before) / 1000000 + " ");
            }
            System.out.println();
        }

        System.out.println("\nMerge Sort");
        for (int i=0 ; i<numberOfSize ; ++i) {
            Integer [][] mergeArr = allArrs[i].clone();
            for (int j=0 ; j<numberOfSize ; ++j)
                mergeArr[j] = allArrs[i][j].clone();

            System.out.print("Array Size: " + mergeArr[i].length + "| Times(ms) ");

            for (int k=0 ; k<10 ; ++k) {
                long before = System.nanoTime();
                MergeSort.mergeSort(mergeArr[k]);
                System.out.print((System.nanoTime() - before) / 1000000 +" ");
            }
            System.out.println();
        }


        System.out.println("\nMerge Sort with DLL");
        for (int i=0 ; i<numberOfSize ; ++i) {
            Integer [][] mergeDLL = allArrs[i].clone();
            for (int j=0 ; j<numberOfSize ; ++j)
                mergeDLL[j] = allArrs[i][j].clone();

            System.out.print("Array Size: " + mergeDLL[i].length + "| Times(ms) ");

            for (int k=0 ; k<10 ; ++k) {
                LinkedList ll = new LinkedList();
                ll.addAll(Arrays.asList(mergeDLL[k]).subList(0, arrSizes[i]));

                long before = System.nanoTime();
                MergeSortDLL.mergeSort(ll);
                System.out.print((System.nanoTime() - before) / 1000000 +" ");
            }
            System.out.println();
        }

        System.out.println("\nHeap Sort");
        for (int i=0 ; i<numberOfSize ; ++i) {
            Integer [][] heapArr = allArrs[i].clone();
            for (int j=0 ; j<numberOfSize ; ++j)
                heapArr[j] = allArrs[i][j].clone();

            System.out.print("Array Size: " + heapArr[i].length + "| Times(ms) ");

            for (int k=0 ; k<10 ; ++k) {
                long before = System.nanoTime();
                HeapSort.heapSort(heapArr[k]);
                System.out.print((System.nanoTime() - before) / 1000000 +" ");
            }
            System.out.println();
        }

        System.out.println("\nQuick Sort");
        for (int i=0 ; i<numberOfSize ; ++i) {
            Integer [][] quickArr = allArrs[i].clone();
            for (int j=0 ; j<numberOfSize ; ++j)
                quickArr[j] = allArrs[i][j].clone();

            System.out.print("Array Size: " + quickArr[i].length + "| Times(ms) ");

            for (int k=0 ; k<10 ; ++k) {
                long before = System.nanoTime();
                QuickSort.quickSort(quickArr[k]);
                System.out.print((System.nanoTime() - before) / 1000000 +" ");
            }
            System.out.println();
        }
    }
}


