/**
 * Main Class of General Tree
 */
public class GeneralTreeMain {
    /**
     * Main Method
     * @param args  command line arguments
     */
    public static void main(String ... args){
        GeneralTree <String> gt = new GeneralTree<>();
        StringBuilder sb = new StringBuilder();

        gt.add("William1","Robert");
        gt.add("William1","William2");
        gt.add("William1","Adela");
        gt.add("William1","Henry1");
        gt.add("Robert","William");
        gt.add("Adela","Stephan");
        gt.add("Henry1","William3");
        gt.add("Henry1","Matilda");
        gt.add("Matilda","Henry2");
        gt.add("Henry2","Henry");
        gt.add("Henry2","Richard1");
        gt.add("Henry2","Geoffrey");
        gt.add("Henry2","John");
        gt.add("Geoffrey","Arthur");
        gt.add("John","Henry3");
        gt.add("John","Richard");
        gt.add("Henry3","Edward1");
        gt.add("Henry3","Edmund");
        gt.add("Edward1","Edward2");
        gt.add("Edward1","Thomas");
        gt.add("Edward1","Edmund1");
        gt.add("Edward2","Edward3");

        BinaryTree.Node n = gt.LevelOrderSearch("John",sb);
        if(n != null ) {
            System.out.println(sb);
            System.out.println(n + " was found!\n");
        }

        sb.delete(0,sb.length());

        n = gt.LevelOrderSearch("Emir",sb);
        if (n == null){
            System.out.println(sb);
            System.out.println("Emir wasn't found!\n");
        }

        sb.delete(0,sb.length());

        n = gt.LevelOrderSearch("Edward3",sb);
        if(n != null ) {
            System.out.println(sb);
            System.out.println(n + " was found!\n");
        }

        System.out.println("\nPreOrderTraverse\n");
        System.out.println(gt.toString());

        System.out.println("------------------------------");

        GeneralTree <Integer> gt2 = new GeneralTree<>();
        StringBuilder sb2 = new StringBuilder();
        gt2.add(1,2);
        gt2.add(1,4);
        gt2.add(1,7);
        gt2.add(2,3);
        gt2.add(2,6);
        gt2.add(3,5);

        BinaryTree.Node n2 = gt2.postOrderSearch(7,sb2);
        if(n2 != null ) {
            System.out.println(sb2);
            System.out.println(n2 + " was found!\n");
        }

        sb2.delete(0,sb2.length());

        n2 = gt2.postOrderSearch(12,sb2);
        if(n2 == null ) {
            System.out.println(sb2);
            System.out.println("12 wasn't found!\n");
        }

        System.out.println("PreOrderTraverse\n");
        System.out.println(gt2.toString());
    }
}
