import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * General Tree represented by Binary Tree
 * @param <E>   generic type of tree
 */
public class GeneralTree <E> extends BinaryTree {

    /**
     * Constructor
     */
    public GeneralTree(){
        super();
    }

    /**
     * Adds given childItem to given parentItem
     * @param parentItem   Parent Node
     * @param childItem    Child Node
     * @return    if success true, otherwise false
     */
    public boolean add(E parentItem, E childItem){
        Queue<Node> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        Node parentNode = new Node(parentItem);
        Node childNode = new Node(childItem);
        Node lastSibling;
        if(parentItem == null || childItem == null) //parent ve ya child null olamaz
            return false;

        if (root == null){      //tree boşsa parentı root childi da rootun cocugu yap
            root = parentNode;
            root.left = childNode;
            return true;
        }
        else {
            Node parentInTree = LevelOrderSearch(q, parentNode,sb); //parentı bul
            if (parentInTree != null && parentNode.data.equals(parentInTree.data)) {
                if (parentInTree.left == null)      //hiç çocuğu yoksa direk soluna ekle
                    parentInTree.left = childNode;
                else {                              //çocuğu varsa onların sağına ekle
                    lastSibling = parentInTree.left;
                    while (lastSibling.right != null) {
                        lastSibling = lastSibling.right;
                    }
                    lastSibling.right = childNode;
                }
                return true;
            } else
                return false;
        }
    }

    /**
     * Verilen elementi ağaç içerisinde level level arar
     * @param element element to search
     * @param sb    ararken gezilen yolu tutar
     * @return  bulursa bulunan elemanı, bulamazsa null
     */
    public Node LevelOrderSearch(E element,StringBuilder sb){
        Queue<Node> q = new LinkedList<>();
        Node n = new Node<>(element);
        return LevelOrderSearch(q,n,sb);
    }

    /**
     * left,right,head
     * @param element element to search
     * @param sb ararken gezilen yolu tutar
     * @return  bulursa bulunan elemanı, bulamazsa null
     */
    public Node postOrderSearch(E element,StringBuilder sb){
        Stack<Node> s= new Stack<>();
        Node<E> n = new Node<>(element);
        Node tmp = root;
        while (tmp != null){
            s.add(tmp);
            tmp = tmp.left;
        }

        while (!s.empty()){
            if (s.peek().right == null){
                if (s.peek() != null){
                    if (s.peek().data.equals(n.data))
                        return s.peek();
                    sb.append(s.pop().toString()+" ");
                }
            } else {
                tmp = s.peek();
                while (tmp != null){
                    if (tmp.data.equals(n.data))
                        return tmp;
                    sb.append(tmp.toString()+" ");
                    tmp = tmp.right;
                }
                s.pop();
            }
        }
        return null;
    }

    /**
     * head,right,left
     * @param node  The local root
     * @param depth The depth
     * @param sb    The string buffer to save the output
     */
    @Override
    protected void preOrderTraverse(Node node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth , sb);
        }
    }

    /**
     * Verilen elementi ağaç içerisinde level level arar
     * @param q her adımdaki leveli tutar
     * @param element element to search
     * @param sb    ararken gezilen yolu tutar
     * @return  bulursa bulunan elemanı, bulamazsa null
     */
    private Node LevelOrderSearch(Queue<Node> q, Node element, StringBuilder sb){
        Queue<Node> tmpLevel = new LinkedList<>();
        Node tmp,moreSiblings;
        if (root == null)
            return null;

        if (q.isEmpty()) {      //ilk çağırılışta queue ya rootu koy
            tmp = root;

            q.add(tmp);
            sb.append(tmp.toString()+" ");
            if (tmp.data.equals(element.data))
                return tmp;
        }
        else
            tmp = q.peek();

        while (!q.isEmpty()) {  //queue dan her seferinde bir eleman çıkarıp onun çocuklarını ekle
                                //her recursive call'da bir level geziyor
            if (q.peek().left != null) {
                if (q.peek().data.equals(element.data))
                    return q.peek();
                if (!isInStringBuilder(sb,q.peek().toString()))
                    sb.append(q.peek().toString()+" ");

                moreSiblings = q.poll().left;
                tmpLevel.add(moreSiblings);
                while (moreSiblings.right != null) {
                    tmpLevel.add(moreSiblings.right);
                    moreSiblings = moreSiblings.right;
                }
            }
            else {
                if (q.peek().data.equals(element.data))
                    return q.peek();
                sb.append(q.peek().toString()+" ");
                q.poll();
            }
        }

        if (!tmpLevel.isEmpty()) {
            sb.append("\n");
            return LevelOrderSearch(tmpLevel,element,sb);
        }
        return null;
    }

    /**
     * verilen key string builderin içinde varmı yokmu onu kontrol eder
     * @param sb String Builder
     * @param key   key to search
     * @return  if find true, otherwise false
     */
    private boolean isInStringBuilder(StringBuilder sb, String key){
        String word="";
        for (int i=0; i<sb.length(); ++i){
            if (sb.charAt(i) != ' ')
                word+=sb.charAt(i);
            else{
                if (word.equals(key))
                    return true;
                word="";
            }
        }
        return false;
    }
}
