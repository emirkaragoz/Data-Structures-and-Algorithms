package Part1;
import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * Main Class of Part1
 */
public class Part1_main {
    /**
     * main method
     * @param args  comment line arguments
     * @throws IOException  open/read file process possible crush cases
     */
    public static void main(String ... args) throws IOException {
        GTUCECourseStructure obje = new GTUCECourseStructure("Courses(CSV)(Updated).csv");
        System.out.println("          ---Course Structure---\n");
        System.out.println(obje.getCourses());

        System.out.printf("--------------------------------------------------\n");
        try {
            System.out.println(obje.getByCode("CSE 222"));
            System.out.println(obje.getByCode("CSE 241"));
            System.out.println(obje.getByCode("XXX XXX"));
            System.out.println(obje.getByCode("CSE 985"));
        } catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }
        System.out.printf("--------------------------------------------------\n");
        try {
            System.out.println(obje.listSemesterCourses(5));
            System.out.println(obje.listSemesterCourses(10));
        } catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }
        System.out.printf("--------------------------------------------------\n");
        try {
            System.out.println(obje.getByRange(20,23));
            System.out.println(obje.getByRange(30,3));
        } catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }
    }
}
