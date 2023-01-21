package feasibleSolution.heuristic;

import course.Course;

import java.util.ArrayList;
import java.util.List;

public class LargestEnrollment implements ConstructiveHeuristic{
    private List<Course> courses;
    private int index=0;
    private List<Node> nodes;
    public LargestEnrollment(List<Course> courses) {
        this.courses = courses;
        nodes=new ArrayList<>(courses.size());
        for (Course course : courses) {
            nodes.add(new Node(course));
        }
        nodes.sort((o1, o2) -> o2.course.getNofStudents() -o1.course.getNofStudents());
    }

    @Override
    public Course getCourse() {
        if(index<courses.size()){
            return nodes.get(index++).course;
        }
        return null;
    }
    private class Node{
        public Course course;
        public Node(Course course) {
            this.course = course;
        }
    }
}
