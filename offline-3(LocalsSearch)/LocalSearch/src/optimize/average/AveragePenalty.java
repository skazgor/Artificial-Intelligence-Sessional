package optimize.average;

import optimize.penalty.Penalty;
import student.Student;

import java.util.List;

public class AveragePenalty {
    private final Penalty penalty;

    public AveragePenalty(Penalty penalty) {
        this.penalty = penalty;
    }
    public double getAveragePenalty(List<Student> students){
        double sum=0;
        for (Student student : students) {
            sum += penalty.getPenalty(student);
        }
        return sum/students.size();
    }
}
