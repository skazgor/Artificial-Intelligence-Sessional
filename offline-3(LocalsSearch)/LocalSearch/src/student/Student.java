package student;

import course.Course;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int studentID;
    private List<Course> courses;

    public Student(int studentID) {
        this.studentID = studentID;
        courses=new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "courses=" + courses +
                '}';
    }
}
