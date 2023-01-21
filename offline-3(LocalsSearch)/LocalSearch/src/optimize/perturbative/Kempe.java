package optimize.perturbative;

import course.Course;
import optimize.penalty.Penalty;
import student.Student;

import java.util.*;

public class Kempe {
    private final int maxIterations ;
   private final List<Course> courses;
   private Penalty penalty;

    public Kempe(List<Course> courses, Penalty penalty, int maxIterations) {
        this.courses = courses;
        this.penalty = penalty;
        this.maxIterations = maxIterations;
    }
    public Kempe(List<Course> courses, Penalty penalty) {
        this.courses = courses;
        this.penalty = penalty;
        this.maxIterations = 50000;
    }

    public void kempeChainInterChange() {
        int iteration=0;
        while (iteration<maxIterations ) {
            Course course=courses.get(getRand(courses.size()-1));
            if(course.getAdjacentList().size()==0){
                continue;
            }
            Course adjacentCourse=course.getAdjacentList().get(getRand(course.getAdjacentList().size()-1));
            HashSet<Course> kempeChain1=new HashSet<>();
            HashSet<Course> kempeChain2=new HashSet<>();
            kempeChain1.add(course);
            kempeChain2.add(adjacentCourse);
            int timeSlot1=course.getTimeSlot();
            int timeSlot2=adjacentCourse.getTimeSlot();
            List<Course> unExplored=new ArrayList<>();
            unExplored.add(course);
            unExplored.add(adjacentCourse);
            extracted(kempeChain1, kempeChain2, timeSlot1, timeSlot2, unExplored);
            setTimeslot(kempeChain1, kempeChain2, timeSlot1, timeSlot2);
            List<Student> students=getImpactedStudents(kempeChain1,kempeChain2);
            int prevPenalty=getPrevPenalty(students);
            int newPenalty=getNewPenalty(students);
            if(newPenalty<prevPenalty) {
                update(kempeChain1, kempeChain2);
            }
            iteration++;
        }
    }

    private void setTimeslot(HashSet<Course> kempeChain1, HashSet<Course> kempeChain2, int timeSlot1, int timeSlot2) {
        for (Course course1: kempeChain1) {
            course1.setNewTimeSlot(timeSlot2);
        }
        for (Course course1: kempeChain2) {
            course1.setNewTimeSlot(timeSlot1);
        }
    }

    private void update(HashSet<Course> kempeChain1, HashSet<Course> kempeChain2) {
        for (Course course1 : kempeChain1) {
            course1.updateTimeSlot();
        }
        for (Course course1 : kempeChain2) {
            course1.updateTimeSlot();
        }
    }
    private  void extracted(HashSet<Course> kempeChain1, HashSet<Course> kempeChain2, int timeSlot1, int timeSlot2, List<Course> unExplored) {
        while (!unExplored.isEmpty()){
            Course course1= unExplored.remove(unExplored.size()-1);
            for (Course course2:course1.getAdjacentList()) {
                  if(course1.getTimeSlot()== timeSlot1){
                       if(course2.getTimeSlot()== timeSlot2){
                           if(!kempeChain2.contains(course2)){
                               kempeChain2.add(course2);
                               unExplored.add(course2);
                           }
                       }
                    }else if(course1.getTimeSlot()== timeSlot2){
                       if(course2.getTimeSlot()== timeSlot1){
                           if(!kempeChain1.contains(course2)){
                               kempeChain1.add(course2);
                               unExplored.add(course2);
                           }
                       }
                    }
            }
        }
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

    private List<Student> getImpactedStudents(HashSet <Course> kempeChain1,HashSet <Course> kempeChain2){
        Set<Student> students;
        students = new HashSet<>();
        for (Course course:kempeChain1) {
            students.addAll(course.getStudents());
        }
        for (Course course:kempeChain2) {
            students.addAll(course.getStudents());
        }
        return new ArrayList<Student>(students);
    }
    private int getRand(int max){
        return (int) (Math.random()*max);
    }
}
