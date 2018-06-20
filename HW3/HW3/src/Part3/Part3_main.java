package Part3;

import Part1.GTUCECourseStructure;

/**
 * Main Class of Part3
 */
public class Part3_main {
    /**
     * main method
     * @param args comment line arguments
     */
    public static void main(String ... args) {
        GTUCECourseStructure toEasilyAccessCourse = new GTUCECourseStructure("Courses(CSV)(Updated).csv");

        GTUCoursesLinkedList obje = new GTUCoursesLinkedList();
        for (int i=0;i<toEasilyAccessCourse.getCourses().size() ; ++i){
            obje.add(toEasilyAccessCourse.getCourses().get(i));
        }

        System.out.printf("%s",obje);
        System.out.println("\nSize of linked list is "+obje.size());
        System.out.printf("--------------------------------------------------\n");

        GTUCoursesLinkedList.Iterator it = obje.new Iterator();

        while (it.hasNextInSemester())
            System.out.printf("%s",it.nextInSemester());
        System.out.printf("--------------------------------------------------\n");

        System.out.printf("%s",it.next());
        System.out.printf("%s",it.next());
        System.out.printf("--------------------------------------------------\n");

        obje.remove(1);
        obje.remove(10);
        obje.remove(22);
        obje.remove(32);

        System.out.printf("%s",obje);
        System.out.println("\nSize of linked list is "+obje.size());
        System.out.printf("--------------------------------------------------\n");
    }
}
