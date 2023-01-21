package optimize.penalty;

import course.Course;
import student.Student;

import java.util.ArrayList;
import java.util.List;

public class Exponential implements Penalty {


    @Override
    public int getPenalty(Student student) {
        List<Integer> timeSlots = new ArrayList<>();
        for (Course course : student.getCourses()) {
            timeSlots.add(course.getTimeSlot());
        }
        return getTotalPenalty(timeSlots);
    }

    @Override
    public int getNewPenalty(Student student) {
        List<Integer> timeSlots = new ArrayList<>();
        for (Course course : student.getCourses()) {
            timeSlots.add(course.getNewTimeSlot());
        }
        return getTotalPenalty(timeSlots);
    }

    private int getTotalPenalty(List<Integer> timeSlots) {
        timeSlots.sort(Integer::compareTo);
        int penalty = 0;
        for (int i = 0; i < timeSlots.size()-1; i++) {
            if(timeSlots.get(i+1)-timeSlots.get(i)<=5){
                penalty+=Math.pow(2,(5-(timeSlots.get(i+1)-timeSlots.get(i))));
            }
        }
        return penalty;
    }
}
