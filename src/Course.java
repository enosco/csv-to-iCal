/*
 *  Stores course data needed to create iCal event
 */

public class Course {
    final private String title, day, start, end, building, roomNum;
    //int duration;

    //TODO: Need to handle case where duration is null (Consider removing it)
    public Course(String title, String meetDay, String meetStart,
                            String meetEnd, String building, String roomNum) {
        this.title = title;
        this.day = meetDay;
        this.start = meetStart;
        this.end = meetEnd;
        this.building = building;
        this.roomNum = roomNum;
        //this.duration = Integer.parseInt(meetEnd) - Integer.parseInt(meetStart);
    }

    public String getTitle() {
        return title;
    }

    public String getDay() {
        return day;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoomNum() {
        return roomNum;
    }

    /*public int getDuration() {
        return duration;
    }*/

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", day='" + day + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", building='" + building + '\'' +
                ", roomNum='" + roomNum + '\'' +
                /*", duration=" + duration +*/
                '}';
    }
}
