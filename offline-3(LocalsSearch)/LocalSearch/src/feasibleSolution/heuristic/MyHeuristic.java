package feasibleSolution.heuristic;

import course.Course;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MyHeuristic implements ConstructiveHeuristic{
    private List<Course> courses;
    private int index=0;
    private List<Node> nodes;
    public MyHeuristic(List<Course> courses) {
        this.courses = courses;
        nodes=new ArrayList<>(courses.size());
        for (Course course : courses) {
            nodes.add(new Node(course));
        }
        nodes.sort(Comparator.comparingInt(o -> o.rand.nextInt()));
    }

    @Override
    public Course getCourse() {
        if(index<courses.size()){
            return nodes.get(index++).course;
        }
        return null;
    }
    private class Node{
        Random rand=new Random();
        public Course course;
        public Node(Course course) {
            this.course = course;
        }
    }
}
