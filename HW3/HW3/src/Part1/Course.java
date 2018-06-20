package Part1;

/**
 * Course Class
 */
public class Course {
    /**
     * Semester of Course
     */
    private String semester="";
    /**
     * Code of Course
     */
    private String code="";
    /**
     * Name of Course
     */
    private String title="";
    /**
     * ECTS of Course
     */
    private String ects="";
    /**
     * GTU Credit of Course
     */
    private String credit="";
    /**
     * HTL of Course
     */
    private String htl="";

    /**
     * Constructor
     * @param line one line of Course CSV file that has all info of courses
     */
    public Course(String line){
        int i = 0;
        while (line.charAt(i) != ';'){  //read semester
            semester+=line.charAt(i);
            i++;
        }
        ++i;
        while (line.charAt(i) != ';'){  //read code
            code += line.charAt(i);
            ++i;
        }
        ++i;
        while (line.charAt(i) != ';'){  //read title
            title += line.charAt(i);
            ++i;
        }
        ++i;
        while (line.charAt(i) != ';'){  //read ects
            ects += line.charAt(i);
            ++i;
        }
        ++i;
        while (line.charAt(i) != ';'){  //read credit
            credit += line.charAt(i);
            ++i;
        }
        ++i;
        while (i<line.length()){    //read htl
            htl += line.charAt(i);
            ++i;
        }
    }

    /**
     * Gets Code
     * @return  code of course
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets Semester
     * @return  semester of course
     */
    public String getSemester() {
        return semester;
    }

    /**
     * toString method
     * @return  a string that has all info of course
     */
    @Override
    public String toString() {
        return (semester+","+code+","+title+","+ects+","+credit+","+htl+"\n");
    }

    /**
     * equals method
     * @param obj  object to be compared
     * @return  True or False
     */
    @Override
    public boolean equals(Object obj) {
        return (obj.toString().equals(this.toString()));
    }
}
