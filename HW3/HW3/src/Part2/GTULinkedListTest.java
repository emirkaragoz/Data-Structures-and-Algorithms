package Part2;

import Part1.Course;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GTULinkedListTest {
    GTULinkedList <Course> obje = new GTULinkedList<>();

    @BeforeEach
    void setUp() {
        Course c1 = new Course("1;PHYS 121;Physics I;6;4;3+0+0");
        Course c2 = new Course("2;CSE 102;Computer Programming;8;4;4+0+0");
        Course c3 = new Course("3;CSE 241;Object Oriented Programming;9;5;3+2+0");
        Course c4 = new Course("7;CSE 495;Graduation Project I;6;1;4+0+0");
        obje.add(c1);obje.add(c2);obje.add(c3);obje.add(c4);
    }

    @AfterEach
    void tearDown() {
        obje.clear();
    }

    @Test
    void disable() {
        boolean actual=true;
        boolean expected = false;
        int beforeSize = obje.size();
        int afterSize;

        Course oneCourse = new Course("7;ENG 400;Career Internship II;1;1;0+0+6");

        //object alan disable index alanı çağırdığı için bunu test etmek index alanın doğruluğunuda kanıtlar
        obje.disable(obje.get(2));

        afterSize = obje.size();
        assertEquals(afterSize,beforeSize-1);   //size test

        try {
            obje.get(2);                                //get test
        } catch (InvalidParameterException e){
            actual = false;
        }
        assertEquals(expected,actual);

        actual = true;
        try{
            obje.set(2,oneCourse);                      //set test
        } catch (InvalidParameterException e){
            actual = false;
        }
        assertEquals(expected,actual);

        actual = true;
        try {
            obje.remove(2);                     //remove test
        } catch (InvalidParameterException e){
            actual = false;
        }
        assertEquals(expected,actual);

        actual = true;
        try {
            obje.listIterator(2);               //listIterator test
        } catch (InvalidParameterException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    void enable() {
        boolean actual=true;
        boolean expected = true;
        int beforeSize = obje.size();
        int afterSize;

        Course oneCourse = new Course("7;ENG 400;Career Internship II;1;1;0+0+6");

        obje.disable(2);
        obje.enable(2);

        afterSize = obje.size();
        assertEquals(beforeSize,afterSize);

        try {
            obje.get(2);                                //get test
        } catch (InvalidParameterException e){
            actual = false;
        }
        assertEquals(expected,actual);

        try{
            obje.set(2,oneCourse);                      //set test
        } catch (InvalidParameterException e){
            actual = false;
        }
        assertEquals(expected,actual);

        try {
            obje.listIterator(2);               //listIterator test
        } catch (InvalidParameterException e){
            actual = false;
        }
        assertEquals(expected,actual);

        try {
            obje.remove(2);                     //remove test
        } catch (InvalidParameterException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }
}