/*
 * Takes tab-delimited csv file and organizes it into a hashmap
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


// TEST INPUT :
/*
20135
21234
23307
21228
21886
20121
*/

//TODO: Handle courses where any field is blank, especially if there are no times
public class CourseReader {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> courseCatalog = new HashMap<>();
        Scanner inFile = new Scanner(new File("input/Spring2024Final.txt"));

        /*String key, line;
        while(inFile.hasNext()){  //Input CSV into HashMap
            line = inFile.nextLine();
            key = line.substring(0,5);

            courseCatalog.putIfAbsent(key, line);
        }*/
        assembleHashmap(courseCatalog, inFile);
        ArrayList<Course> courses = getUserCourses(courseCatalog);

        for (Course c :
                courses) {
            System.out.println(c.toString());
        }

        iCalCreator finalOutput = new iCalCreator();

        finalOutput.beginCalendar();
        for(Course c : courses) {
            finalOutput.createEvent(c);
        }

        finalOutput.endCalendar();

        /*Course testCourse = new Course("title", "M", "01", "01", "bld", "room");
        ArrayList<Course> blank = new ArrayList<>();
        iCalCreator finalOutput = new iCalCreator();
        blank.add(testCourse);
        finalOutput.beginCalendar();
        for(Course c : blank){
            finalOutput.createEvent(c);*/

    }

    //Takes input file and inputs each line into hashmap
    public static void assembleHashmap(HashMap<String, String> hm, Scanner scnr) {
        String key, line;
        while (scnr.hasNext()) {  //Input CSV into HashMap
            line = scnr.nextLine();
            key = line.substring(0, 5);

            hm.putIfAbsent(key, line);
        }
    }

    //Allows user to input CRNs and adds them to an ArrayList
    public static ArrayList<Course> getUserCourses(HashMap<String, String> courseCatalog){
        ArrayList<Course> requestedCourses= new ArrayList<>();
        Scanner userIn = new Scanner(System.in);

        System.out.println("Input CSV: ");
        String csv = userIn.next();
        String foundCourse;

        while(!csv.equals("q")){ //User input loop
            if(courseCatalog.containsKey(csv)){
                foundCourse = courseCatalog.get(csv);
                requestedCourses.add(createCourse(foundCourse));
            }
            else {
                System.out.println("No Course Found for: " + csv);
            }
            csv = userIn.next();
        }

        return requestedCourses;
    }



    //Splits string from hashmap and creates Course object from data
    public static Course createCourse(String course){
        String[] columns = course.split("\t");

        String title = columns[4];
        String building = columns[9];
        String room = columns[10];
        String day = columns[6];
        String startTime = columns[7];
        String endTime = columns[8];

        return new Course(title, day, startTime, endTime, building, room);
    }

    //Unused for now
    /*public static void formatOutput(String course){ //Formats Output as [Title] [Building] [Room] [Day] [StartTm] [EndTm]
        String[] columns = course.split("\t");
        Need Columns 4, 6, 7, 8, 9, 10
                4 = title
                6 = day
                7 = Start
                8 = end
                9 = Building
                10 = room

        String title = columns[4];
        String building = columns[9];
        String room = columns[10];
        String day = columns[6];
        String startTime = columns[7];
        String endTime = columns[8];
        System.out.printf("Course %s is located in %s Room %s on %s from %s to %s", title, building,
                room, day, startTime, endTime);
    }*/
}
