package Q3;

import Graph.*;

/**
 * Q3 Main Class
 */
public class Q3main {
    /**
     * main method
     * @param args command line arguments
     */
    public static void main(String ... args) {
        ListGraph lg = new ListGraph(15, false);

        lg.insert(new Edge(0, 1, 0));
        lg.insert(new Edge(0, 4, 0));
        lg.insert(new Edge(0, 7, 0));
        lg.insert(new Edge(0, 13, 0));
        lg.insert(new Edge(1, 4, 0));
        lg.insert(new Edge(1, 5, 0));
        lg.insert(new Edge(1, 2, 0));
        lg.insert(new Edge(2, 3, 0));
        lg.insert(new Edge(2, 6, 0));
        lg.insert(new Edge(3, 6, 0));
        lg.insert(new Edge(3, 10, 0));
        lg.insert(new Edge(3, 14, 0));
        lg.insert(new Edge(4, 7, 0));
        lg.insert(new Edge(4, 8, 0));
        lg.insert(new Edge(5, 6, 0));
        lg.insert(new Edge(5, 9, 0));
        lg.insert(new Edge(6, 10, 0));
        lg.insert(new Edge(7, 8, 0));
        lg.insert(new Edge(7, 13, 0));
        lg.insert(new Edge(8, 11, 0));
        lg.insert(new Edge(8, 9, 0));
        lg.insert(new Edge(9, 10, 0));
        lg.insert(new Edge(9, 12, 0));
        lg.insert(new Edge(10, 14, 0));
        lg.insert(new Edge(11, 13, 0));
        lg.insert(new Edge(12, 14, 0));

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

        System.out.println("-----------------------\n");

        System.out.println("          ---Spanning Tree With BFS---");
        ListGraph spanningTreeBFS = ListGraph.spanningTreeWithBFS(lg);
        ListGraph.plotGraph(spanningTreeBFS);

        System.out.println("          ---Spanning Tree With DFS---");
        ListGraph spanningTreeDFS = ListGraph.spanningTreeWithDFS(lg);
        ListGraph.plotGraph(spanningTreeDFS);
    }
}
