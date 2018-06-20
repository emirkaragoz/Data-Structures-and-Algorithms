package Q3;

import java.util.LinkedList;
import java.util.Random;

/**
 * Q3 Main Class
 */
public class Q3main {
    /**
     * Main Method of Q3
     * @param args command line argument
     */
    public static void main(String ... args){
        LinkedList<Integer> ll = new LinkedList<>();
        Random rand = new Random();

        for (int i=0 ; i<10 ; ++i)
            ll.add(rand.nextInt(20)+1);

        System.out.println("Unsorted random list: "+ll);
        MergeSortDLL.mergeSort(ll);
        System.out.print("Sorted list: " + ll);
    }
}
