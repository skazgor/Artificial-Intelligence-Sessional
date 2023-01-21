package initialize;

import course.Course;
import student.Student;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    private String fileName;
    public ReadFile(String fileName) {
        this.fileName = fileName;
    }
    public void readAndInitialize(List<Student> students, List<Course> courses){
        List<HashSet<Course>> studentCourses=new ArrayList<>();
        List<List<Student>> studentList=new ArrayList<>();
        readCRSFile(courses);
        for (int i = 0; i < courses.size(); i++) {
            studentCourses.add(new HashSet<>());
            studentList.add(new ArrayList<>());
        }
        String path=new File("").getAbsolutePath();
        File file = new File(path+fileName+".stu");
        Scanner scanner = null;
        int studentID=0;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                Student student=new Student(studentID);
                for (int i = 0; i < parts.length; i++) {
                    int courseID;
                    try{
                        courseID=Integer.parseInt(parts[i]);
                    }
                    catch (NumberFormatException e){
                        continue;
                    }
                    student.addCourse(courses.get(courseID-1));
                }
                students.add(student);
                studentID++;
                for (Course course:
                     student.getCourses()) {
                    studentList.get(course.getCourseID()).add(student);
                    for (Course course1: student.getCourses()) {
                        if (course.getCourseID()!=course1.getCourseID()){
                            studentCourses.get(course.getCourseID()).add(course1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        for (int i = 0; i < courses.size(); i++) {
            courses.get(i).setAdjacentList(new ArrayList<>(studentCourses.get(i)));
            courses.get(i).setStudents(studentList.get(i));
        }
    }
    private void readCRSFile(List<Course> courses){
        String path=new File("").getAbsolutePath();
        File file = new File(path+fileName+".crs");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                int courseID=Integer.parseInt(parts[0])-1;
                int nofStudents=Integer.parseInt(parts[1]);
                courses.add(new Course(courseID,nofStudents));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
