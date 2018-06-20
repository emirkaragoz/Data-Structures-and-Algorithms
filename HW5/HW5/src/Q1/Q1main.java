package Q1;

/**
 * Main Class of Q1
 */
public class Q1main {
    /**
     * Main Method
     * @param args command line arguments
     */
    public static void main(String ... args){
        System.out.println("--------------------------------------------------------------------");
        DoubleHashMap o = new DoubleHashMap(11);

        o.put(23,"first");
        o.put(12,"second");
        o.put(34,"third");
        o.put(5,"fourth");

        printHashTable(o);

        if (!o.isEmpty())
            System.out.println("Size of Hash Map: "+o.size());

        o.put(34,"chanced third");
        System.out.println("\nPut with same key 34");
        printHashTable(o);
        if (!o.isEmpty())
            System.out.println("Size of Hash Map: "+o.size());

        o.remove(12);
        System.out.println("\nKey 12 deleted");
        printHashTable(o);
        if (!o.isEmpty())
            System.out.println("Size of Hash Map: "+o.size());

        o.put(4,"fifth");
        System.out.println("\nCan't put DELETED index");
        printHashTable(o);
        if (!o.isEmpty())
            System.out.println("Size of Hash Map: "+o.size());

        for (Integer i=0 ; i<8 ;++i)
            o.put(i,(i.toString()));

        System.out.println("\nRehashed. DELETED's didn't move new has table");
        printHashTable(o);
        if (!o.isEmpty())
            System.out.println("Size of Hash Map: "+o.size());

        System.out.println("--------------------------------------------------------------------");
        DoubleHashMap o2 = new DoubleHashMap(13);
        o2.put("first","one");
        o2.put("second","two");
        o2.put("third","three");
        o2.put("fourth","four");

        printHashTable(o2);
        if (!o2.isEmpty())
            System.out.println("Size of Hash Map: "+o2.size());

        o2.put("first","chanced one");
        System.out.println("\nPut with same key 'first'");
        printHashTable(o2);
        if (!o2.isEmpty())
            System.out.println("Size of Hash Map: "+o2.size());

        o2.remove("first");
        o2.remove("second");
        System.out.println("\nKey 'first' and 'second' deleted");
        printHashTable(o2);
        if (!o2.isEmpty())
            System.out.println("Size of Hash Map: "+o2.size());
        System.out.println("--------------------------------------------------------------------");

    }

    /**
     * Prints Hash Table of an Double Hash Map object
     * @param o Double Hash Map object
     */
    private static void printHashTable(DoubleHashMap o){
        for (int i=0 ; i< o.getTable().length ; ++i) {
            if (o.getTable()[i] != null) {
                if (o.getTable()[i].getKey() != null)
                    System.out.print("("+o.getTable()[i].getKey() + " " + o.get(o.getTable()[i].getKey()) +") - ");
                else
                    System.out.print("DELETED - ");
            } else {
                System.out.print(o.getTable()[i] + " - ");
            }
        }
        System.out.println();
    }
}
