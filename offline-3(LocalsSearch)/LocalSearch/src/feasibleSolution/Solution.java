package feasibleSolution;

import course.Course;
import feasibleSolution.heuristic.ConstructiveHeuristic;

import java.util.List;

public class Solution {
    private final ConstructiveHeuristic heuristic;

    public Solution(ConstructiveHeuristic heuristic) {
        this.heuristic = heuristic;
    }
    public void solve(List<Course> courses) {
        boolean[][] domain=new boolean[courses.size()][courses.size()];
        initialize(courses, domain);
        assignTimeSlot(courses, domain);
        System.out.println("Total Number of Time Slot Used:" + getTotalTimeSlot(courses));
    }

    private int getTotalTimeSlot(List<Course> courses) {
        int maxTimeSlot = 0;
        for (Course course : courses) {
            if (course.getTimeSlot() > maxTimeSlot) {
                maxTimeSlot = course.getTimeSlot();
            }
        }
        return maxTimeSlot + 1;
    }

    private void assignTimeSlot(List<Course> courses, boolean[][] domain) {
        for (int i = 0; i < courses.size(); i++) {
            Course course = heuristic.getCourse();
            course.setTimeSlot(getATimeSlot(domain,course.getCourseID()));
            updateDomain(domain,course);
        }
    }

    private void initialize(List<Course> courses, boolean[][] domain) {
        for (int i = 0; i < courses.size(); i++) {
            domain[i]=new boolean[courses.size()];
            for (int j = 0; j < courses.size(); j++) {
                domain[i][j]=true;
            }
        }
    }

    private void updateDomain(boolean[][] domain, Course course ) {
        for(Course adjacentCourse:course.getAdjacentList()){
            domain[adjacentCourse.getCourseID()][course.getTimeSlot()]=false;
            adjacentCourse.increaseSaturationDegree();
        }
    }

    private int getATimeSlot(boolean[][] domain, int courseID) {
        for (int i = 0; i < domain.length; i++) {
            if(domain[courseID][i]){
                return i;
            }
        }
        return -1;
    }
    public boolean checkSolution(List<Course> courses) {
        for (Course course : courses) {
            for (Course adjacentCourse : course.getAdjacentList()) {
                if (course.getTimeSlot() == adjacentCourse.getTimeSlot()) {
                    return false;
                }
            }
        }
        return true;
    }
}
