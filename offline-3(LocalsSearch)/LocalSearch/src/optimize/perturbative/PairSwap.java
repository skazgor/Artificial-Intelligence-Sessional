package optimize.perturbative;

import course.Course;
import optimize.penalty.Penalty;
import student.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PairSwap {
    private final List<Course> courses;
    private final int maxIterations;
    private final Penalty penalty;

    public PairSwap(List<Course> courses, int maxIterations, Penalty penalty) {
        this.courses = courses;
        this.maxIterations = maxIterations;
        this.penalty = penalty;
    }

    public PairSwap(List<Course> courses, Penalty penalty) {
        this.courses = courses;
        this.penalty = penalty;
        this.maxIterations = 5000000;
    }
    public void pairSwap(){
        int iteration=0;
        while (iteration<maxIterations  ){
            Course course=courses.get(getRand(courses.size()-1));

            Course adjacentCourse=courses.get(getRand(courses.size()-1));
            if(course==adjacentCourse || course.getTimeSlot()==adjacentCourse.getTimeSlot()){
                continue;
            }
            int timeSlot1=course.getTimeSlot();
            int timeSlot2=adjacentCourse.getTimeSlot();
            course.setNewTimeSlot(timeSlot2);
            adjacentCourse.setNewTimeSlot(timeSlot1);
            List<Course> crs=new ArrayList<>();
            crs.add(course);
            crs.add(adjacentCourse);
            List<Student> students=getAffectedStudent(crs);
             if(isFeasible(course,adjacentCourse)){
                 if(getNewPenalty(students)<getPrevPenalty(students)){
                     course.updateTimeSlot();
                     adjacentCourse.updateTimeSlot();
                 }
             }
            iteration++;
        }
    }
    private List<Student> getAffectedStudent(List<Course> courses){
        HashSet<Student> students=new HashSet<>();
        for(Course course:courses){
            students.addAll(course.getStudents());
        }
        return new ArrayList<>(students);
    }
    private int getNewPenalty(List<Student> students) {
        int prevPenalty=0;
        for (Student student:students) {
            prevPenalty+=penalty.getNewPenalty(student);
        }
        return prevPenalty;
    }

    private int getPrevPenalty(List<Student> students) {
        int prevPenalty=0;
        for (Student student:students) {
            prevPenalty+=penalty.getPenalty(student);
        }
        return prevPenalty;
    }
    private boolean isFeasible(Course course ,Course adjacentCourse){
        for (Course crs:course.getAdjacentList()) {
            if(crs!=adjacentCourse && crs.getTimeSlot()==course.getNewTimeSlot()){
                    return false;
            }
        }
        for (Course crs:adjacentCourse.getAdjacentList()) {
            if(crs!=course && crs.getTimeSlot()==adjacentCourse.getNewTimeSlot()){
                return false;
            }
        }
        return true;
    }
    private int getRand(int max){
        return (int)(Math.random()*max);
    }
}
