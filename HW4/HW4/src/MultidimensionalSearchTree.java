import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * Multidimensional Search Tree
 * @param <E> generic type of tree
 */
public class MultidimensionalSearchTree<E extends Comparable> extends BinaryTree implements SearchTree{
    /**
     * dimension number of tree
     */
    int dimension;

    /**
     * No Parameter Constructor
     */
    public MultidimensionalSearchTree(){
        dimension = 3;
    }

    /**
     * Constructor
     * @param d input dimension
     */
    public MultidimensionalSearchTree(int d){
        if (d>0)
            dimension = d;
        else
            throw new InvalidParameterException("Dimension must be grater than 0");
    }

    /**
     * For BinaryTree.Node, includes E type vector
     */
    public class Multidimension{
        /**
         * Field Vector
         */
        public Vector<E> v;

        /**
         * Constructor
         * @param inputVector input vector
         */
        public Multidimension(Vector<E> inputVector){
            v = inputVector;
        }

        /**
         * setter
         * @param v  input vector
         */
        public void setV(Vector<E> v) {
            this.v = v;
        }
    }

    /**
     * add item, if grater than local root to right side, otherwise to left side
     * @param item item to add
     * @return  if success true, otherwise false
     */
    @Override
    public boolean add(Object item){
        Multidimension m = (Multidimension) item;
        if (contains(m))
            return false;
       if (root == null && m.v.size() == dimension) {
           Node n = new Node(m.v.clone());
           root = n;
           return true;
       }
       else {
           if (m.v.size() != dimension)
               return false;
           else {
               Node tmp = root;
               int level = 0;
               while (tmp != null){
                   //büyükse sağa
                    if (((Vector<E>)tmp.data).elementAt(level%dimension).compareTo(m.v.elementAt(level%dimension))<0) {
                        if(tmp.right != null)
                            tmp = tmp.right;
                        else {
                            Node n = new Node(m.v.clone());
                            tmp.right = n;
                            return true;
                        }
                    }
                    else {      //küçük ve eşitse sola
                        if (tmp.left != null)
                            tmp = tmp.left;
                        else {
                            Node n = new Node(m.v.clone());
                            tmp.left = n;
                            return true;
                        }
                    }
                    ++level;
               }
           }
       }
       return false;
    }

    /**
     * Starter method find.
     * @pre The target object must implement
     *      the Comparable interface.
     * @param target The Comparable object being sought
     * @return The object, if found, otherwise null
     */
    @Override
    public Object find(Object target) {
        Multidimension m = (Multidimension) target;
        return find(root, m.v);
    }

    /**
     * checks whether targer is in tree
     * @param target    target node
     * @return  if found true,otherwise false
     */
    @Override
    public boolean contains(Object target) {
        if (find(target) != null)
            return true;
        return false;
    }

    /**
     * Recursive find method.
     * @param localRoot The local subtree's root
     * @param target The object being sought
     * @return The object, if found, otherwise null
     */
    private Vector find(Node localRoot, Vector target) {
        if (localRoot == null) {
            return null;
        }

        if (target.equals((Vector<E>)localRoot.data))
            return (Vector<E>)localRoot.data;

        //iki daldan ağacı arar bulursa return eder
        Vector v =find(localRoot.left, target);
        if (v!=null)
            return v;

        v = find(localRoot.right, target);
        if (v!=null)
            return v;

        return null;
    }

    /**
     * Removes target from tree
     * @param target    node to remove
     * @return  if success true,otherwise false
     */
    @Override
    public boolean remove(Object target) {
        if (delete(target) != null)
            return true;
        return false;
    }

    /**
     * Deletes target from tree
     * @param target    node to delete
     * @return  The object, if deleted otherwise null
     */
    @Override
    public Object delete(Object target) {
        Multidimension m = (Multidimension) target;
        Multidimension tmp = new Multidimension(m.v);
        int firstSize,lastSize;
        LinkedList<Node> list = new LinkedList<>();

        binaryLevelOrder(list,null);    //level order olarak tree yi bir linked liste yaz
        firstSize = list.size();

        list.clear();

        binaryLevelOrder(list,m.v);         //level order olarak tree yi target hariç bir linked liste yaz
        lastSize = list.size();

        if (firstSize != lastSize) {    //target tree de varsa
            root = root.right = root.left = null;   //mevcut treeyi sil

            for (int i = 0; i < lastSize; ++i) {        //targetsız linked listin elemanları ile baştan bir tree oluştur
                tmp.setV((Vector<E>) list.poll().data);
                add(tmp);
            }

            return m.v;
        }
        else
            return null;
    }

    /**
     * helper to delete method
     * @param arr list to be filled out
     * @param target element that shouldn't be added to the list
     */
    private void binaryLevelOrder(LinkedList<Node> arr,Vector target){
        Queue<Node> queue = new LinkedList<>();

        if (root == null)
            return;

        queue.add(root);
        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            if (target == null || !target.equals((Vector<E>)tempNode.data))
                arr.add(tempNode);

            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
}
