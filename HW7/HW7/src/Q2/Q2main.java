package Q2;

import Graph.*;

/**
 * Q2 Main Class
 */
public class Q2main {
    /**
     * main method
     * @param args command line arguments
     */
    public static void main(String ... args){
        ListGraph lg = new ListGraph(15,false);

        lg.insert(new Edge(0,3,1));
        lg.insert(new Edge(1,2,1));
        lg.insert(new Edge(1,4,1));
        lg.insert(new Edge(2,3,1));
        lg.insert(new Edge(4,7,1));
        lg.insert(new Edge(5,7,1));
        lg.insert(new Edge(6,7,1));
        lg.insert(new Edge(8,9,1));
        lg.insert(new Edge(9,10,1));
        lg.insert(new Edge(9,11,1));
        lg.insert(new Edge(9,12,1));
        lg.insert(new Edge(10,13,1));
        lg.insert(new Edge(13,14,1));

        System.out.println("          ---Graph---");
        ListGraph.plotGraph(lg);

        System.out.println("-----------------------");
        if (ListGraph.is_undirected(lg))
            System.out.println("Graph is undirected.");
        else
            System.out.println("Graph is directed.");

        if (ListGraph.is_acyclic_graph(lg))
            System.out.println("Graph is acyclic graph.");
        else
            System.out.println("Graph is cyclic graph.");

        System.out.println("-----------------------");
        if (ListGraph.is_connected(lg,8,14))
            System.out.println("8 and 14 is connected!");
        else
            System.out.println("8 and 14 is not connected!");

        System.out.println("-----------------------");
        if (ListGraph.is_connected(lg,9,0))
            System.out.println("9 and 0 is connected!");
        else
            System.out.println("9 and 0 is not connected!");

        System.out.println("-----------------------");
        if (ListGraph.is_connected(lg,5,2))
            System.out.println("5 and 2 is connected!");
        else
            System.out.println("5 and 2 is not connected!");
        System.out.println("-----------------------");

    }
}
