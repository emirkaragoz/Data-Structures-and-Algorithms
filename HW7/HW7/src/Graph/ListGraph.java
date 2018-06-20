package Graph;

import java.security.InvalidParameterException;
import java.util.*;

/** A ListGraph is an extension of the AbstractGraph abstract class
 *   that uses an array of lists to represent the edges.
 *   @author Koffman and Wolfgang
 */

public class ListGraph extends AbstractGraph {

    // Data Field
    /** An array of Lists to contain the edges that
     originate with each vertex. */
    private List <Edge> [] edges;

    /** Construct a graph with the specified number of
     vertices and directionality.
     @param numV The number of vertices
     @param directed The directionality flag
     */
    public ListGraph(int numV, boolean directed) {
        super(numV, directed);
        edges = new List[numV];
        for (int i = 0; i < numV; i++) {
            edges[i] = new LinkedList <> ();
        }
    }

    /** Determine whether an edge exists.
     @param source The source vertex
     @param dest The destination vertex
     @return true if there is an edge from source to dest
     */
    public boolean isEdge(int source, int dest) {
        return edges[source].contains(new Edge(source, dest));
    }

    /** Insert a new edge into the graph.
     @param edge The new edge
     */
    public void insert(Edge edge) {
        edges[edge.getSource()].add(edge);
        if (!isDirected()) {
            edges[edge.getDest()].add(new Edge(edge.getDest(),
                    edge.getSource(),
                    edge.getWeight()));
        }
    }

    public Iterator <Edge> edgeIterator(int source) {
        return edges[source].iterator();
    }

    /** Get the edge between two vertices. If an
     edge does not exist, an Edge with a weight
     of Double.POSITIVE_INFINITY is returned.
     @param source The source
     @param dest The destination
     @return the edge between these two vertices
     */
    public Edge getEdge(int source, int dest) {
        Edge target =
                new Edge(source, dest, Double.POSITIVE_INFINITY);
        for (Edge edge : edges[source]) {
            if (edge.equals(target))
                return edge; // Desired edge found, return it.
        }
        // Assert: All edges for source checked.
        return target; // Desired edge not found.
    }

    /**
     * Determine if there is any path between vertex v 1 and vertex v 2 in graph g. If
     * v1 or v2 are not in g then throw an error.
     * @param g given graph
     * @param v1 first vertex
     * @param v2 second vertex
     * @return if connected return true, otherwise return false
     */
    public static boolean is_connected(ListGraph g, int v1, int v2){
        if (g.edges.length <= v1 || g.edges.length <= v2 || v1<0 || v2<0)
            throw new InvalidParameterException("Invalid source or destination!");

        StringBuilder ignore = new StringBuilder();
        if(!g.isDirected())
            return DFS(g,v1,v2,ignore) || DFS(g,v2,v1,ignore);
        return DFS(g,v1,v2,ignore);
    }

    /**
     * Find the shortest path from vertex v 1 to vertex v 2 using Dijkstra’s algorithm.
     * @param g given graph
     * @param s start vertex
     * @param e end vertex
     * @param sb string builder for distance
     * @return vector which includes vertices of shortest path
     */
    public static Vector<Integer> shortestPath (ListGraph g, int s, int e,StringBuilder sb) {
        final int [] distance = new int [g.getNumV()];
        int [] preceding = new int [g.getNumV()];
        final boolean [] visited = new boolean [g.getNumV()];

        for (int i=0; i<distance.length; ++i)
            distance[i] = Integer.MAX_VALUE;

        distance[s] = 0;

        for (int i=0; i<distance.length; i++) {
            final int next = g.minVertex (distance, visited);

            if (next != -1) {
                visited[next] = true;

                Integer[] n = new Integer[g.edges[next].size()];
                for (int k = 0; k < g.edges[next].size(); ++k)
                    n[k] = g.edges[next].get(k).getDest();
                for (final Integer v : n) {
                    final int d = distance[next] + (int) (new Edge(next, v)).getWeight();
                    if (distance[v] > d) {
                        distance[v] = d;
                        preceding[v] = next;
                    }
                }
            }
        }

        preceding = g.createPath(preceding,s,e,sb);
        Vector<Integer> result = new Vector<>();
        for(int p:preceding) result.add(p);

        return result;
    }

    /**
     * Check if the graph object is undirected, this is true if all directed edges have
     * a complementary directed edge with the same weight in the opposite direction.
     * @param g given graph
     * @return if undirected return true, otherwise return false
     */
    public static boolean is_undirected(ListGraph g){
        int start = 0;

        boolean visited[] = new boolean[g.getNumV()];

        LinkedList<Integer> queue = new LinkedList<>();

        visited[start]=true;
        queue.add(start);

        while (queue.size() != 0) {
            start = queue.poll();

            for (Edge n : g.edges[start]) {
                int neighbor = n.getDest();
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    if (g.getEdge(start, neighbor).getWeight() != g.getEdge(neighbor, start).getWeight())
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * Plots given graph
     * @param g given graph
     */
    public static void plotGraph(ListGraph g){
        for (int i=0; i< g.edges.length ; ++i){
            if (g.edges[i].size()!=0) {
                System.out.print("|V:" + g.edges[i].get(0).getSource()+" - D:0| -> ");
                for (int j = 0; j < g.edges[i].size(); ++j) {
                    System.out.print("|V:" + g.edges[i].get(j).getDest()+ " - D:" + g.edges[i].get(j).getWeight());
                    if (j != g.edges[i].size()-1)
                        System.out.print("| -> ");
                    else
                        System.out.print("|");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * The graph may or may not have cycles.
     * @param g given graph
     * @return if graph is acyclic return true, otherwise return false
     */
    public static boolean is_acyclic_graph(ListGraph g){
        if (g.isDirected())
            return is_acyclic_directed_graph(g);

        return is_acyclic_undirected_graph(g);
    }

    /**
     * Breath First Search
     * @param g given graph
     * @param target search element
     * @param sb String Builder which includes result searched path
     * @return if found return true, otherwise return false
     */
    public static boolean BFS(ListGraph g, int target, StringBuilder sb){
        boolean visited[] = new boolean[g.getNumV()];
        LinkedList<Integer> queue = new LinkedList<>();
        int start=0;

        visited[start]=true;
        queue.add(start);

        while (queue.size() != 0) {
            start = queue.poll();
            sb.append(start).append(" ");
            if (start == target)
                return true;

            for (Edge n : g.edges[start]) {
                int neighbor = n.getDest();
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }

    /**
     * Wrapper public Depth First Search
     * @param g given graph
     * @param target search element
     * @param sb String Builder which includes result searched path
     * @return if found return true, otherwise return false
     */
    public static boolean DFS(ListGraph g, int target, StringBuilder sb){
        return DFS(g,0,target,sb);
    }

    /**
     * Wrapper public Depth First Search
     * @param g given graph
     * @param start start vertex to begin search
     * @param target search element
     * @param sb String Builder which includes result searched path
     * @return if found return true, otherwise return false
     */
    private static boolean DFS(ListGraph g, int start, int target, StringBuilder sb) {
        boolean visited[] = new boolean[g.getNumV()];
        Stack<Integer> stack=new  Stack<>();

        visited[start] = true;
        stack.add(start);

        while (!stack.isEmpty()) {
            start = stack.pop();
            sb.append(start).append(" ");
            if (target==start)
                return true;

            List<Edge> neighbours = g.edges[start];
            for (Edge neighbour : neighbours) {
                Integer n = neighbour.getDest();
                if (!visited[n]) {
                    stack.add(n);
                    visited[n] = true;
                }
            }
        }
        return false;
    }

    /**
     * Minimum Spanning Tree
     * @param graph given graph
     * @param sb String Builder which includes result BFS
     * @return spanning graph
     */
    private static ListGraph spanning(ListGraph graph,StringBuilder sb){
        ListGraph lg = new ListGraph(graph.getNumV(), graph.isDirected());
        ArrayList<Integer> arr = new ArrayList<>();
        StringBuilder tmp= new StringBuilder();
        for (int i=0; i<sb.length(); ++i) {
            if (sb.charAt(i) != ' ')
                tmp.append(sb.charAt(i));
            else {
                arr.add(Integer.parseInt(tmp.toString()));
                tmp = new StringBuilder();
            }
        }

        for (int i=0 ; i<graph.getNumV()-1 ; ++i){
            if (!lg.isEdge(arr.get(i),arr.get(i+1)))
                lg.insert(new Edge(arr.get(i),arr.get(i+1)));
        }

        return lg;
    }

    /**
     * Minimum Spanning Tree With BFS
     * @param graph given graph
     * @return spanning graph
     */
    public static ListGraph spanningTreeWithBFS(ListGraph graph) {
        StringBuilder sb = new StringBuilder();
        BFS(graph,graph.getNumV()+5,sb);
        return spanning(graph,sb);

    }

    /**
     * Minimum Spanning Tree With DFS
     * @param graph given graph
     * @return spanning graph
     */
    public static ListGraph spanningTreeWithDFS(ListGraph graph) {
        StringBuilder sb = new StringBuilder();
        DFS(graph,graph.getNumV()+5,sb);
        return spanning(graph,sb);
    }

    /**
     * finds minimum vertex in dist
     * @param dist distance array
     * @param v visited array
     * @return minimum vertex
     */
    private int minVertex (int [] dist, boolean [] v) {
        int x = Integer.MAX_VALUE;
        int y = -1;
        for (int i=0; i<dist.length; i++) {
            if (!v[i] && dist[i]<x) {
                y=i;
                x=dist[i];
            }
        }
        return y;
    }

    /**
     * creates shortest path from s to e
     * @param pred preceding array
     * @param s start vertex
     * @param e end vertex
     * @param sb string builder for distance
     * @return array which includes vertices of shortest path
     */
    private int [] createPath (int [] pred, int s, int e,StringBuilder sb) {
        if (is_connected(this,s,e)) {
            final ArrayList<Edge> path = new ArrayList<>();
            int distance = 0;
            int x = e;
            while (x != s) {
                path.add(0, getEdge(x, pred[x]));
                distance += getEdge(pred[x], x).getWeight();
                x = pred[x];
            }
            path.add(0, getEdge(s, e));

            pred = new int [path.size()];
            for (int i=0 ; i<path.size() ; ++i){
                pred[i] = path.get(i).getSource();
            }
            sb.append("Distance from ").append(s).append(" to ").append(e).append(" = ").append(distance);
        } else {
            pred = new int[0];
        }
        return pred;
    }

    /**
     * acyclic undirected helper recursive method
     * @param g given graph
     * @param index current vertex
     * @param visited visited array
     * @param up cyclic check root vertex
     * @return  if cyclic return true, otherwise false
     */
    private static boolean acyclic_helper_undirected(ListGraph g,int index, boolean visited[], int up) {
        visited[index] = true;
        Edge e;

        for (Edge edge : g.edges[index]) {
            e = edge;

            if (!visited[e.getDest()]) {
                if (acyclic_helper_undirected(g, e.getDest(), visited, index))
                    return true;
            } else if (e.getDest() != up)
                return true;
        }
        return false;
    }

    /**
     * acyclic undirected method
     * @param g given graph
     * @return if acyclic return true, otherwise return false
     */
    private static boolean is_acyclic_undirected_graph(ListGraph g) {
        boolean visited[] = new boolean[g.getNumV()];
        for (int i = 0; i < g.getNumV(); ++i)
            visited[i] = false;

        for (int i = 0; i < g.getNumV(); ++i)
            if (!visited[i])
                if (acyclic_helper_undirected(g, i, visited, -1))
                    return false;

        return true;
    }

    /**
     * acyclic directed helper recursive method
     * @param g given graph
     * @param i current vertex
     * @param visited visited array
     * @param stack path stack
     * @return  if cyclic return true, otherwise false
     */
    private static boolean acyclic_helper_directed(ListGraph g,int i, boolean[] visited, boolean[] stack) {
        if (stack[i]) return true;
        if (visited[i]) return false;

        visited[i] = true;
        stack[i] = true;
        List<Edge> down = g.edges[i];

        for (Edge e: down)
            if (acyclic_helper_directed(g,e.getDest(), visited, stack))
                return true;

        stack[i] = false;

        return false;
    }

    /**
     * acyclic directed method
     * @param g given graph
     * @return if acyclic return true, otherwise return false
     */
    private static boolean is_acyclic_directed_graph(ListGraph g) {
        boolean[] visited = new boolean[g.getNumV()];
        boolean[] stack = new boolean[g.getNumV()];

        for (int i = 0; i < g.getNumV(); i++)
            if (acyclic_helper_directed(g,i, visited, stack))
                return false;

        return true;
    }
}
