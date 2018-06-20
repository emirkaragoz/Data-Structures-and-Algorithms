package Q3;

import Q1.BinarySearchTree;
import java.security.InvalidParameterException;

/**
 * Q3 Main Class
 */
public class Q3main {
    /**
     * Main Method
     * @param args command line arguments
     */
    public static void main(String ... args){
        try{
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            bst.add(11);bst.add(2);bst.add(13);bst.add(15);bst.add(12);bst.add(16);
            System.out.println("------------------------");
            System.out.println("Binary Tree");
            System.out.println(bst);
            AVLTree<Integer> avl = new AVLTree<>(bst);
        }catch (InvalidParameterException e){
            System.out.println(e.getMessage());
            System.out.println("------------------------");
        }

        System.out.println("AVL Tree");
        AVLTree<Integer> avl = new AVLTree<>();
        avl.add(23);avl.add(48);avl.add(32);avl.add(98);avl.add(45);avl.add(28);
        avl.add(19);avl.add(37);avl.add(76);avl.add(63);avl.add(84);avl.add(11);
        System.out.println(avl);

        System.out.println("------------------------");
        System.out.println("32 was deleted");
        avl.delete(32);
        System.out.println(avl);

    }
}
