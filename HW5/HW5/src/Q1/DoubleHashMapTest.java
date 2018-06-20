package Q1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class DoubleHashMapTest {

    private DoubleHashMap obj;

    @BeforeEach
    void setUp() {
        obj = new DoubleHashMap(17);
    }

    @Test
    void get() {
        DoubleHashMap.Entry expected = new DoubleHashMap.Entry<>(35,"izmir");
        Object actual;

        obj.put(35,"izmir");
        obj.put(26,"eskisehir");
        obj.put(37,"kastamonu");

        actual = obj.get(35);
        assertEquals(expected.getValue(),actual);

        expected = null;
        actual = obj.get(11);
        assertEquals(expected,actual);

    }

    @Test
    void put() {
        DoubleHashMap.Entry expected = new DoubleHashMap.Entry<>(35,"izmir");
        Object actual;

        obj.put(35,"izmir");
        actual = obj.put(35,"goztepe");

        assertEquals(expected.getValue(),actual);
    }

    @Test
    void remove() {
        DoubleHashMap.Entry expected = new DoubleHashMap.Entry<>(35,"izmir");
        Object actual;

        obj.put(35,"izmir");

        actual = obj.remove(35);

        assertEquals(expected.getValue(),actual);

        expected = null;
        actual = obj.remove(35);
        assertEquals(expected,actual);
    }
}