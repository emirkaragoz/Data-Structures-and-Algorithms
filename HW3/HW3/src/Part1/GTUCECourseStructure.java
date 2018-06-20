package Part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

/**
 * GTU Computer Engineering Course Structure
 */
public class GTUCECourseStructure {
    /**
     * Courses Linked List
     */
    private LinkedList<Course> courses = new LinkedList<>();
    /**
     * Input File Name
     */
    private String filename;

    /**
     * Constructor
     * @param inputFileName Input File Name
     */
    public GTUCECourseStructure(String inputFileName){
        try {
            //checks whether there is a file called given file name
            BufferedReader br = new BufferedReader(new FileReader(inputFileName));
            br.close();
            filename = inputFileName;
            readFile();     //can throw IOException
        }
        catch (IOException e){
            System.out.println("Invalid File Name!");
            System.exit(1);
        }
    }

    /**
     * Course Getter
     * @return  All Courses Linked List
     */
    public LinkedList<Course> getCourses() {
        return courses;
    }

    /**
     * Reads Courses from CSV file to courses linked list
     * @throws IOException  open/read file process possible crush cases
     */
    private void readFile() throws IOException {
        String oneLine = "";
        int titleKey=0;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while ((oneLine = br.readLine())!=null && oneLine.length()!=0){
            if (titleKey == 0)  //pass first head line of file
                titleKey = 1;
            else{
                Course oneCourse = new Course(oneLine);     //read a line
                courses.add(oneCourse);                     //add to courses this line
            }
        }
    }

    /**
     * Gets courses by code of those courses
     * @param code  code of course
     * @return  linked list of courses that has given course code
     */
    public LinkedList<Course> getByCode(String code) {
        LinkedList<Course> results = new LinkedList<>();
        for(int i=0; i<courses.size() ; ++i){
            if (courses.get(i).getCode().equals(code))
                results.add(courses.get(i));
        }
        if (results.size() == 0)
            throw new InvalidParameterException("Invalid course code!");

        return results;
    }

    /**
     * Gets courses in given semester
     * @param semester  semester
     * @return  linked list of courses in given semester
     */
    public LinkedList<Course> listSemesterCourses(Integer semester) {
        if (semester < 1 || semester > 8)
            throw new InvalidParameterException("Invalid semester! (1-8)");

        LinkedList<Course> results = new LinkedList<>();
        for(int i=0; i<courses.size() ; ++i){
            if (courses.get(i).getSemester().equals(semester.toString()))
                results.add(courses.get(i));
        }
        return results;
    }

    /**
     * Gets courses in given range
     * @param start_index   start index of range
     * @param last_index    last index of range
     * @return  linked list of courses in given range
     */
    public LinkedList<Course> getByRange(int start_index, int last_index){
        if (start_index>=0 && start_index <last_index && last_index<courses.size()) {
            LinkedList<Course> results = new LinkedList<>();
            for (int i = start_index; i <= last_index; ++i) {
                results.add(courses.get(i));
            }
            return results;
        }
        else
            throw new InvalidParameterException("Invalid index range!");
    }
}
