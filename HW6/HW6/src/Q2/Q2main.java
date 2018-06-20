package Q2;

/**
 * Q2 Main Class
 */
public class Q2main {
    /**
     * Main Method
     * @param args command line arguments
     */
    public static void main(String ... args){
        System.out.println("----------------------------");
        System.out.println("Example 1");
        BTree<Integer> b = new BTree<>(3);
        b.add(3);b.add(4);b.add(5);b.add(6);b.add(1);b.add(2);
        b.add(23);b.add(24);b.add(56);b.add(72);b.add(11);b.add(9);
        System.out.println(b);

        System.out.println("----------------------------");
        System.out.println("Example 2");
        BTree<Double> b1 = new BTree<>(5);
        b1.add(22.2);b1.add(28.4);b1.add(19.3);b1.add(87.0);b1.add(11.1);
        b1.add(85.2);b1.add(73.9);b1.add(54.4);b1.add(44.4);b1.add(90.9);
        b1.add(66.6);b1.add(43.4);b1.add(47.6);b1.add(94.6);b1.add(100.0);
        System.out.println(b1);
    }
}
