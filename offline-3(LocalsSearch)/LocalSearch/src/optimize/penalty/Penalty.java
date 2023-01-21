package optimize.penalty;

import student.Student;

public interface Penalty {
     int getPenalty(Student student);
     int getNewPenalty(Student student);
}
