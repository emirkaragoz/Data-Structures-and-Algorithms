package Part2;

import Part1.Course;
import Part1.GTUCECourseStructure;

import java.security.InvalidParameterException;

/**
 * Main Class of Part2
 */
public class Part2_main {
    /**
     * main method
     * @param args  comment line arguments
     */
    public static void main(String ... args) {
        GTUCECourseStructure toEasilyAccessCourse = new GTUCECourseStructure("Courses(CSV)(Updated).csv");

        GTULinkedList <Course> obje = new GTULinkedList<>();
        for (int i=0;i<toEasilyAccessCourse.getCourses().size() ; ++i){
            obje.add(toEasilyAccessCourse.getCourses().get(i));
        }

        System.out.println(obje);
        System.out.printf("Size of linked list is = %d\n",obje.size());
        obje.disable(2);
        obje.disable(1);
        obje.disable(3);
        System.out.printf("Size of linked list is = %d\n",obje.size());
        obje.showDisable();

        try {
            obje.get(1);                                //get test
        } catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }

        try{
            obje.set(3,toEasilyAccessCourse.getCourses().get(12));      //set test
        } catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }

        try {
            obje.remove(2);                     //remove test
        } catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }

        try {
            obje.listIterator(1);               //listIterator test
        } catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }

        obje.enable(3);     //eski pozisyonlarına döndüklerini çıktıdan rahatlıkla görebilirsiniz
        obje.enable(2);
        obje.enable(1);

        System.out.println(obje);
        System.out.printf("Size of linked list is = %d\n",obje.size());
    }
}
