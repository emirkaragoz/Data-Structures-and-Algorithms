package Q1;

import java.util.Random;

/**
 * Q1 Main Class
 */
public class Q1main {
    /**
     * Main Method
     * @param args command line arguments
     */
    public static void main(String ... args){
        Random rand = new Random();

        System.out.println("First Example");
        RedBlackTree<Integer> rb= new RedBlackTree<>();
        int tmpInt=rand.nextInt(20);
        System.out.println("------------------------");
        while (rb.height(rb.root) < 7){
            tmpInt += rand.nextInt(10)+1;
            rb.add(tmpInt);
            System.out.print(rb);
            System.out.println("------------------------");
        }

        System.out.println("Second Example");
        RedBlackTree<Integer> rb1= new RedBlackTree<>();
        int tmpInt1=rand.nextInt(250);
        System.out.println("------------------------");
        while (rb1.height(rb1.root) < 7){
            tmpInt1 -= rand.nextInt(10)+1;
            rb1.add(tmpInt1);
            System.out.print(rb1);
            System.out.println("------------------------");
        }
    }
}
