import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

class MultidimensionalSearchTreeTest {

    MultidimensionalSearchTree<Double> o = new MultidimensionalSearchTree<>(1);
    Vector<Double> v = new Vector<>(1);

    @AfterEach
    void tearDown() {
        if (o.root != null)
            o.root = o.root.right = o.root.left = null;
    }

    @Test
    void add() {
        boolean expected=true;
        v.add(13.3);
        MultidimensionalSearchTree.Multidimension m = o.new Multidimension(v);
        assertEquals(expected,o.add(m));
        Vector<Double> rootNode = (Vector<Double>) v.clone();

        v.set(0,28.98);
        m.setV(v);
        assertEquals(expected,o.add(m));
        Vector<Double> rightNode = (Vector<Double>) v.clone();

        v.set(0,-20.32);
        m.setV(v);
        assertEquals(expected,o.add(m));
        Vector<Double> leftNode = (Vector<Double>) v.clone();

        assertEquals(o.root.toString(),rootNode.toString());
        assertEquals(o.root.right.toString(),rightNode.toString());
        assertEquals(o.root.left.toString(),leftNode.toString());

        expected = false;
        v.set(0,28.98);
        m.setV(v);
        assertEquals(expected,o.add(m));
    }

    @Test
    void find() {
        v.add(19.2);
        MultidimensionalSearchTree.Multidimension m = o.new Multidimension(v);
        o.add(m);
        assertEquals(o.find(m),m.v);    //target'ın değil tree deki target node'unun referansını döndürdüğü için
                                        //referans karşılaştırması doğru sonuç verir

        v.set(0,0.0);
        m.setV(v);
        assertEquals(null,o.find(m));   //bulamadığı için null döndürür

    }

    @Test
    void contains() {
        boolean expected=true;
        boolean actual;
        v.add(153.52);
        MultidimensionalSearchTree.Multidimension m = o.new Multidimension(v);
        o.add(m);

        actual = o.contains(m);
        assertEquals(expected,actual);

        expected = false;
        v.set(0,18.0);
        m.setV(v);
        actual = o.contains(m);
        assertEquals(expected,actual);
    }

    @Test
    void remove() {
        boolean expected=true;
        boolean actual;

        v.add(24.52);
        MultidimensionalSearchTree.Multidimension m = o.new Multidimension(v);
        o.add(m);

        actual = o.remove(m);
        assertEquals(expected,actual);

        expected = false;
        actual = o.remove(m);
        assertEquals(expected,actual);
    }

    @Test
    void delete() {
        v.add(24.52);
        MultidimensionalSearchTree.Multidimension m = o.new Multidimension(v);
        o.add(m);

        assertEquals(m.v,o.delete(m));
        assertEquals(null,o.delete(m));
    }
}