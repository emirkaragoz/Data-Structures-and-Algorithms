package Part3;

import Part1.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GTUCoursesLinkedListTest {
    GTUCoursesLinkedList obje = new GTUCoursesLinkedList();
    Course c1 = new Course("1;PHYS 121;Physics I;6;4;3+0+0");
    Course c2 = new Course("2;CSE 102;Computer Programming;8;4;4+0+0");
    Course c3 = new Course("3;CSE 241;Object Oriented Programming;9;5;3+2+0");

    @AfterEach
    void tearDown() {
        obje.clear();
    }

    @Test
    void size() {
        int expected = 0;
        int actual;

        actual = obje.size();
        assertEquals(expected,actual);

        obje.add(c1);obje.add(c2);obje.add(c3);

        expected = 3;
        actual = obje.size();
        assertEquals(expected,actual);
    }

    @Test
    void add() {
        String firstAdd,secondAdd;

        //add(int index,Object o)
        obje.add(c3,0);
        obje.add(c2,0);
        obje.add(c1,1);
        firstAdd = obje.toString();

        obje.clear();

        //add(Object o) default adds end of the list
        obje.add(c2);
        obje.add(c1);
        obje.add(c3);
        secondAdd = obje.toString();

        assertEquals(firstAdd,secondAdd);
    }

    @Test
    void remove() {
        Course expected;
        Course actual;

        obje.add(c2);
        obje.add(c1);
        obje.add(c3);

        expected = c2;
        actual = obje.remove(0);

        assertEquals(expected,actual);

        expected = c3;
        actual = obje.remove(c3);

        assertEquals(expected,actual);
    }

    @Test
    void next() {
        Course expected;
        Course actual;
        boolean hasNextActual;
        boolean hasNextExpected;

        obje.add(c1);
        obje.add(c2);
        obje.add(c3);
        GTUCoursesLinkedList.Iterator it = obje.new Iterator();

        expected = c1;
        actual = it.next();
        assertEquals(expected,actual);

        expected = c2;
        actual = it.next();
        assertEquals(expected,actual);

        hasNextExpected = true;
        hasNextActual = it.hasNext();
        assertEquals(hasNextExpected,hasNextActual);

        expected = c3;
        actual = it.next();
        assertEquals(expected,actual);

        hasNextExpected = false;
        hasNextActual = it.hasNext();
        assertEquals(hasNextExpected,hasNextActual);
    }

    @Test
    void nextInSemester() {
        Course expected;
        Course actual;
        boolean hasNextInSemesterActual;
        boolean hasNextInSemesterExpected;
        Course c4 = new Course("1;CSE 101;Introduction To Computer Engineering;8;3;3+0+0");
        Course c5 = new Course("1;MATH 101;Calculus I;7;5;5+0+0");
        obje.add(c1);
        obje.add(c2);
        obje.add(c4);
        obje.add(c3);
        obje.add(c5);
        GTUCoursesLinkedList.Iterator it = obje.new Iterator();

        expected = c1;
        actual = it.nextInSemester();
        assertEquals(expected,actual);

        hasNextInSemesterExpected = true;
        hasNextInSemesterActual = it.hasNextInSemester();
        assertEquals(hasNextInSemesterExpected,hasNextInSemesterActual);

        expected = c4;
        actual = it.nextInSemester();
        assertEquals(expected,actual);

        expected = c5;
        actual = it.nextInSemester();
        assertEquals(expected,actual);

        hasNextInSemesterExpected = false;
        hasNextInSemesterActual = it.hasNextInSemester();
        assertEquals(hasNextInSemesterExpected,hasNextInSemesterActual);

        expected = c1;
        actual = it.nextInSemester();
        assertEquals(expected,actual);

        it.next();
        expected = c2;
        actual = it.nextInSemester();
        assertEquals(expected,actual);

    }
}