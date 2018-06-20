package Part1;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GTUCECourseStructureTest {
    GTUCECourseStructure obje = new GTUCECourseStructure("Courses(CSV)(Updated).csv");

    @Test
    void getByCode() {
        LinkedList<Course> expected = new LinkedList<>();
        expected.add(obje.getCourses().get(3));     //3. indexte Math 101 dersi bulunmakta

        LinkedList<Course> actual = obje.getByCode("MATH 101");

        assertEquals(expected,actual);
    }

    @Test
    void listSemesterCourses() {
        LinkedList<Course> expected = new LinkedList<>();
        for (int i = 0 ; i<8 ; ++i)     //1. sömestırda 8 tane ders vardır.Bunlar da 0 ile 7. indexlerde bulunmaktadır.
            expected.add(obje.getCourses().get(i));

        LinkedList<Course> actual = obje.listSemesterCourses(1);

        assertEquals(expected,actual);
    }

    @Test
    void getByRange() {
        LinkedList<Course> expected = new LinkedList<>();
        for (int i=6; i<=10 ; ++i)      //6.7.8.9.10. indexleri içercek list
            expected.add(obje.getCourses().get(i));

        LinkedList<Course> actual = obje.getByRange(6,10);
        assertEquals(expected,actual);
    }
}