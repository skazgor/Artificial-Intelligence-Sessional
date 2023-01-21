package course;

import student.Student;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseID;
    private int nofStudents;
    private List<Student> students;

    private int saturationDegree=0;
    private int timeSlot=-1;
    private List<Course> adjacentList;
    private int newTimeSlot=-1;

    public List<Student> getStudents() {
        return students;
    }

    public Course(int courseID) {
        this.courseID = courseID;
    }

    public Course(int courseID, int nofStudents) {
        this.courseID = courseID;
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
        return saturationDegree;
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

    public void increaseSaturationDegree() {
        saturationDegree++;
    }
}
