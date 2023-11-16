/*
 *   Creates iCal file and writes events corresponding to course details
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class iCalCreator {
    //private ArrayList<Course> courses;
    private final File file;
    private final PrintWriter writer;

    public iCalCreator(/*ArrayList<Course> courses*/) throws IOException {
        /*this.courses = new ArrayList<>();
        this.courses.addAll(courses);*/

        System.out.println("Creating output file...");
        file = new File("output/output.txt");
        printOutputFileStatus();
        writer = new PrintWriter(new FileWriter(file));

    }

    private void printOutputFileStatus() throws IOException {
        if(file.createNewFile()) {
            System.out.println("Output file successfully created.");
        } else {
            System.out.println("File already exists. Overwriting...");
        }

    }

    public void beginCalendar() {
        writer.printf("BEGIN:VCALENDAR%nVERSION:2.0%n");

    }
    public void endCalendar() {
        writer.printf("END:VCALENDAR");
        writer.close();
    }

    //Creates event for each day of a given course
    //TODO: Verify is DTEND is needed if duration is also included
    //TODO: Manage input where variables are null
    public void createEvent(Course c) {
        String summary;
        for(int i = 0; i < c.getDay().length(); i++){
            writer.printf("BEGIN:VEVENT%n");

            summary = c.getTitle() + " " + c.getBuilding() + " " + c.getRoomNum();
            writer.printf("SUMMARY: %s%n", summary);

            char dateLetter = c.getDay().charAt(i);
            writer.printf("DTSTART;TZID=America/New_York:%sT%s00%n", formatDate(dateLetter), c.getStart());
            writer.printf("DTEND;TZID=America/New_York:%sT%s00%n", formatDate(dateLetter), c.getEnd());

            //Duration might be more complicated than expected, DTEND might be the better option
            //writer.printf("DURATION:PT%sH%n", c.getDuration());
            writer.printf("LOCATION:%s %s%n", c.getBuilding(), c.getRoomNum());

            writer.printf("RRULE:FREQ=WEEKLY");
            writer.printf("END:VEVENT%n");
        }
    }
    private String formatDate(char dateLetter){
        StringBuilder dateString = new StringBuilder("202401");

        switch (dateLetter) {
            case 'M' -> dateString.append("15");
            case 'T' -> dateString.append("16");
            case 'W' -> dateString.append("17");
            case 'R' -> dateString.append("18");
            case 'F' -> dateString.append("19");
        }
        return dateString.toString();
    }

}
