package feasibleSolution.heuristic;

import course.Course;

import java.util.ArrayList;
import java.util.List;

public class LargestDegree implements ConstructiveHeuristic {
    private List<Course> courses;
    private int index=0;
    private List<Node> nodes;

    public LargestDegree(List<Course> courses) {
        this.courses = courses;
        nodes =new ArrayList<>(courses.size());
        for (int i = 0; i < courses.size(); i++) {
            nodes.add( new Node(courses.get(i)));
        }
        nodes.sort((o1, o2) -> o2.degree - o1.degree);
    }

    public Course getCourse() {
        if(index<courses.size()){
            return nodes.get(index++).course;
        }
        return null;
    }
    private class Node{
        public Course course;
        public int degree;

        public Node(Course course) {
            this.course = course;
            this.degree = course.getAdjacentList().size();
        }
    }
}


