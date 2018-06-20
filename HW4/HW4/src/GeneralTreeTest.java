import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralTreeTest {

    GeneralTree <Double> gt = new GeneralTree<>();
    StringBuilder sb = new StringBuilder();

    @AfterEach
    void tearDown() {
        if (gt.root != null)
            gt.root = gt.root.right = gt.root.left = null;
    }

    @Test
    void add() {
        boolean expected=true;
        boolean actual;
        Double firstChild,secondChild;

        actual = gt.add(82.2,32.85);
        assertEquals(expected,actual);
        firstChild = 32.85;

        gt.add(82.2,25.5);
        secondChild = 25.5;

        //kardeşler sağa eklenir
        assertEquals(firstChild.toString(),gt.root.left.toString());
        assertEquals(secondChild.toString(),gt.root.left.right.toString());

        expected = false;
        actual = gt.add(null,null);
        assertEquals(expected,actual);
    }

    @Test
    void levelOrderSearch() {
        BinaryTree.Node actual;
        BinaryTree.Node<Double> expected;
        gt.add(1.2,3.5);
        gt.add(1.2,4.7);
        gt.add(4.7,32.5);

        expected = new BinaryTree.Node<>(32.5);
        actual = gt.LevelOrderSearch(32.5,sb);

        assertEquals(expected.toString(),actual.toString());

        assertEquals("1.2 \n3.5 4.7 \n",sb.toString());
    }

    @Test
    void postOrderSearch() {
        BinaryTree.Node actual;
        BinaryTree.Node<Double> expected;
        gt.add(19.0,5.0);
        gt.add(19.0,4.0);
        gt.add(5.0,8.0);

        expected = new BinaryTree.Node<>(4.0);
        actual = gt.postOrderSearch(4.0,sb);

        assertEquals(expected.toString(),actual.toString());

        assertEquals("8.0 5.0 ",sb.toString());
    }

    @Test
    void preOrderTraverse() {
        gt.add(19.0,5.0);
        gt.add(19.0,4.0);
        gt.add(5.0,8.0);

        gt.preOrderTraverse(gt.root,1,sb);

        assertEquals("19.0\n" +
                              "  5.0\n" +
                              "    8.0\n" +
                              "      null\n" +
                              "    null\n" +
                              "  4.0\n" +
                              "    null\n" +
                              "  null\n" +
                              "null\n",sb.toString());
    }
}