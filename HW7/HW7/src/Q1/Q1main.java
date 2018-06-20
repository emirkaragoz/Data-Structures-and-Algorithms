package Q1;

import Graph.*;

import java.util.Random;
import java.util.Vector;

/**
 * Q1 Main Class
 */
public class Q1main {
    /**
     * main method
     * @param args command line arguments
     */
    public static void main(String ... args){
        Random r = new Random();
        ListGraph lg = new ListGraph(10,true);

        lg.insert(new Edge(0,2,r.nextInt(25)+1));
        lg.insert(new Edge(0,7,r.nextInt(25)+1));
        lg.insert(new Edge(1,2,r.nextInt(25)+1));
        lg.insert(new Edge(3,0,r.nextInt(25)+1));
        lg.insert(new Edge(3,4,r.nextInt(25)+1));
        lg.insert(new Edge(3,7,r.nextInt(25)+1));
        lg.insert(new Edge(5,1,r.nextInt(25)+1));
        lg.insert(new Edge(5,4,r.nextInt(25)+1));
        lg.insert(new Edge(5,8,r.nextInt(25)+1));
        lg.insert(new Edge(6,3,r.nextInt(25)+1));
        lg.insert(new Edge(6,4,r.nextInt(25)+1));
        lg.insert(new Edge(6,9,r.nextInt(25)+1));
        lg.insert(new Edge(6,7,r.nextInt(25)+1));
        lg.insert(new Edge(6,2,r.nextInt(25)+1));
        lg.insert(new Edge(7,2,r.nextInt(25)+1));
        lg.insert(new Edge(8,1,r.nextInt(25)+1));
        lg.insert(new Edge(8,4,r.nextInt(25)+1));
        lg.insert(new Edge(8,9,r.nextInt(25)+1));
        lg.insert(new Edge(9,2,r.nextInt(25)+1));
        lg.insert(new Edge(9,4,r.nextInt(25)+1));


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
        Vector<Integer> shortestPaths;
        StringBuilder sb = new StringBuilder();
        System.out.println("Shortest path from 3 to 2");
        shortestPaths = ListGraph.shortestPath(lg,3,2,sb);
        if (!shortestPaths.isEmpty()) {
            System.out.println(shortestPaths);
            System.out.println(sb);
        }
        else
            System.out.println("There is no path between 6 and 4");
        shortestPaths.clear();
        sb = new StringBuilder();

        System.out.println("-----------------------");
        System.out.println("Shortest path from 5 to 4");
        shortestPaths = ListGraph.shortestPath(lg,5,4,sb);
        if (!shortestPaths.isEmpty()) {
            System.out.println(shortestPaths);
            System.out.println(sb);
        }
        else
            System.out.println("There is no path between 5 and 4");
        shortestPaths.clear();
        sb = new StringBuilder();

        System.out.println("-----------------------");
        System.out.println("Shortest path from 4 to 2");
        shortestPaths = ListGraph.shortestPath(lg,4,2,sb);
        if (!shortestPaths.isEmpty()) {
            System.out.println(shortestPaths);
            System.out.println(sb);
        }
        else
            System.out.println("There is no path between 4 and 2");

        System.out.println("-----------------------");
    }
}
