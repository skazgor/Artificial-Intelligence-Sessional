package course;

import student.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Course {
    private final int courseID;
    private int nofStudents;
    private List<Student> students;

    private HashSet<Integer> adjacentTimeslot;
    private int timeSlot=-1;
    private List<Course> adjacentList;
    private int newTimeSlot=-1;

    public List<Student> getStudents() {
        return students;
    }

    public Course(int courseID) {
        adjacentTimeslot =new HashSet<>();
        this.courseID = courseID;
    }

    public Course(int courseID, int nofStudents) {
        this.courseID = courseID;
        adjacentTimeslot =new HashSet<>();
        this.nofStudents = nofStudents;
    }

    public int getCourseID() {
        return courseID;
    }

    public Course(int courseID, List<Course> adjacentList) {
        this.courseID = courseID;
        this.adjacentList = adjacentList;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getSaturationDegree() {
        return adjacentTimeslot.size();
    }

    public int getNewTimeSlot() {
        return newTimeSlot;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public int getNofStudents() {
        return nofStudents;
    }

    public void setAdjacentList(ArrayList<Course> courses) {
        this.adjacentList = courses;
    }

    @Override
    public String toString() {
       return Integer.toString(courseID);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public void setNewTimeSlot(int newTimeSlot) {
        this.newTimeSlot = newTimeSlot;
    }
    public void updateTimeSlot(){
        timeSlot=newTimeSlot;
    }
    public List<Course> getAdjacentList() {
        return adjacentList;
    }

    public void addAdjacentTimeslot(int timeslot) {
        adjacentTimeslot.add(timeslot);
    }
}
