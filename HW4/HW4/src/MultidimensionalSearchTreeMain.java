import java.security.InvalidParameterException;
import java.util.Vector;

/**
 * Main Class of Multidimensional Search Tree
 */
public class MultidimensionalSearchTreeMain {
    /**
     * Main Method
     * @param args  command line arguments
     */
    public static void main(String ... args){
        System.out.println("--------------------------------");
        try {
            MultidimensionalSearchTree<Integer> o1 = new MultidimensionalSearchTree<>(2);

            Vector<Integer> v1 = new Vector<>(2);
            v1.addElement(40);
            v1.addElement(45);
            MultidimensionalSearchTree.Multidimension m = o1.new Multidimension(v1);
            o1.add(m);

            v1.setElementAt(15, 0);
            v1.setElementAt(70, 1);
            m.setV(v1);
            o1.add(m);

            v1.setElementAt(70, 0);
            v1.setElementAt(10, 1);
            m.setV(v1);
            o1.add(m);

            v1.setElementAt(69, 0);
            v1.setElementAt(50, 1);
            m.setV(v1);
            o1.add(m);

            v1.setElementAt(66, 0);
            v1.setElementAt(85, 1);
            m.setV(v1);
            o1.add(m);

            v1.setElementAt(85, 0);
            v1.setElementAt(90, 1);
            m.setV(v1);
            o1.add(m);

            System.out.println(o1.toString());

            if (o1.contains(m))
                System.out.println(o1.find(m) + " is in MDS Tree");

            v1.setElementAt(69, 0);
            v1.setElementAt(50, 1);
            m.setV(v1);
            System.out.println(o1.delete(m) + " deleted from MDS Tree.\n");

            System.out.println(o1.toString());

            v1.setElementAt(40, 0);
            v1.setElementAt(45, 1);
            m.setV(v1);
            if (o1.remove(m)) {
                System.out.println(m.v.toString() + " deleted from MDS Tree.\n");
                System.out.println(o1.toString());
            }
        }
        catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }
        System.out.println("--------------------------------");

        MultidimensionalSearchTree<String> o2 = new MultidimensionalSearchTree<>();

        Vector<String> v2 = new Vector<>(3);
        v2.add("lay");v2.add("rat");v2.add("house");
        MultidimensionalSearchTree.Multidimension m2 = o2.new Multidimension(v2);
        o2.add(m2);

        v2.set(0,"cow");v2.set(1,"jack");v2.set(2,"with");
        m2.setV(v2);
        o2.add(m2);

        v2.set(0,"that");v2.set(1,"short");v2.set(2,"married");
        m2.setV(v2);
        o2.add(m2);

        v2.set(0,"malt");v2.set(1,"man");v2.set(2,"cat");
        m2.setV(v2);
        o2.add(m2);

        v2.set(0,"fox");v2.set(1,"bear");v2.set(2,"and");
        m2.setV(v2);
        o2.add(m2);

        v2.set(0,"is");v2.set(1,"deep");v2.set(2,"in");
        m2.setV(v2);
        o2.add(m2);

        System.out.println(o2.toString());

        if (o2.contains(m2))
            System.out.println(o2.find(m2)+" is in MDS Tree");

        v2.set(0,"malt");v2.set(1,"man");v2.set(2,"cat");
        m2.setV(v2);
        System.out.println(o2.delete(m2)+" deleted from MDS Tree.\n");

        System.out.println(o2.toString());

        v2.set(0,"cow");v2.set(1,"jack");v2.set(2,"with");
        m2.setV(v2);
        if (o2.remove(m2)) {
            System.out.println(m2.v.toString()+" deleted from MDS Tree.\n");
            System.out.println(o2.toString());
        }
        System.out.println("--------------------------------");
    }
}
