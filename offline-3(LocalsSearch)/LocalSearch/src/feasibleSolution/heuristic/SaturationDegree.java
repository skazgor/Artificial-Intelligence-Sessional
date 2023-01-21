package feasibleSolution.heuristic;

import course.Course;

import java.util.List;

public class SaturationDegree implements ConstructiveHeuristic{
    private List<Course> courses;

    public SaturationDegree(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course getCourse() {
        Course course = null;
        int max = -1;
        for (Course c : courses) {
            if(c.getTimeSlot()==-1 && c.getSaturationDegree()>max){
                    max = c.getSaturationDegree();
                    course = c;
            }
        }
        return course;
    }
}
