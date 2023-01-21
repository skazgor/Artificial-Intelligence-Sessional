package main;

import course.Course;
import feasibleSolution.Solution;
import feasibleSolution.heuristic.*;
import initialize.ReadFile;
import optimize.average.AveragePenalty;
import optimize.penalty.Exponential;
import optimize.penalty.Linear;
import optimize.penalty.Penalty;
import optimize.perturbative.Kempe;
import optimize.perturbative.PairSwap;
import student.Student;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String[] FILE_NAME ={ "\\data\\car-f-92", "\\data\\car-s-91", "\\data\\kfu-s-93","\\data\\tre-s-92","\\data\\yor-f-83"};
    public static void main(String[] args) {
        for (int i = 0; i < FILE_NAME.length; i++) {
            System.out.println("File name: "+FILE_NAME[i]);
            ReadFile readFile = new ReadFile(FILE_NAME[i]);
            List<Course> courses = new ArrayList<>();
            List<Student> students = new ArrayList<>();
            readFile.readAndInitialize(students, courses);
            runScheme1(courses, students);
            courses=new ArrayList<>();
            students=new ArrayList<>();
            readFile.readAndInitialize(students, courses);
            runScheme2(courses, students);
            courses=new ArrayList<>();
            students=new ArrayList<>();
            readFile.readAndInitialize(students, courses);
            runScheme3(courses, students);
            courses=new ArrayList<>();
            students=new ArrayList<>();
            readFile.readAndInitialize(students, courses);
            runScheme4(courses, students);
            courses=new ArrayList<>();
            students=new ArrayList<>();
            readFile.readAndInitialize(students, courses);
            runScheme5(courses, students);
            courses=new ArrayList<>();
            students=new ArrayList<>();
            readFile.readAndInitialize(students, courses);
            runScheme6(courses, students);
            courses=new ArrayList<>();
            students=new ArrayList<>();
            readFile.readAndInitialize(students, courses);
            runScheme7(courses, students);
            courses=new ArrayList<>();
            students=new ArrayList<>();
            readFile.readAndInitialize(students, courses);
            runScheme8(courses, students);
        }
    }

    private static void runScheme1(List<Course> courses, List<Student> students) {
        System.out.println("Scheme 1: LargestDegree+exponential penalty");
        ConstructiveHeuristic heuristic=new LargestDegree(courses);
        exponential(courses, students, heuristic);
    }
    private static void runScheme2(List<Course> courses, List<Student> students) {
        System.out.println("Scheme 2: SaturationDegree+exponential penalty");
        ConstructiveHeuristic heuristic=new SaturationDegree(courses);
        exponential(courses, students, heuristic);
    }
    private static void runScheme3(List<Course> courses, List<Student> students) {
        System.out.println( "Scheme 3: LargestEnrollment+exponential penalty");
        ConstructiveHeuristic heuristic=new LargestEnrollment(courses);
        exponential(courses, students, heuristic);
    }
    private static void runScheme4(List<Course> courses, List<Student> students) {
        System.out.println("Scheme 4: Random+Exponential penalty");
        ConstructiveHeuristic heuristic=new MyHeuristic(courses);
        exponential(courses, students, heuristic);
    }
private static void runScheme5(List<Course> courses, List<Student> students) {
        System.out.println("Scheme 5: LargestDegree+Linear penalty");
        ConstructiveHeuristic heuristic=new LargestDegree(courses);
        linear(courses, students, heuristic);
    }
    private static void runScheme6(List<Course> courses, List<Student> students) {
        System.out.println("Scheme 6: SaturationDegree+Linear penalty");
        ConstructiveHeuristic heuristic=new SaturationDegree(courses);
        linear(courses, students, heuristic);
    }
    private static void runScheme7(List<Course> courses, List<Student> students) {
        System.out.println( "Scheme 7: LargestEnrollment+Linear penalty");
        ConstructiveHeuristic heuristic=new LargestEnrollment(courses);
        linear(courses, students, heuristic);
    }
    private static void runScheme8(List<Course> courses, List<Student> students) {
        System.out.println("Scheme 8: Random+Linear penalty");
        ConstructiveHeuristic heuristic=new MyHeuristic(courses);
        linear(courses, students, heuristic);
    }


    private static void exponential(List<Course> courses, List<Student> students, ConstructiveHeuristic heuristic) {
        Solution solution=new Solution(heuristic);
        solution.solve(courses);
        Penalty penalty=new Exponential();
        printAndCheck(courses, students, solution, penalty);
    }
    private static void linear(List<Course> courses, List<Student> students, ConstructiveHeuristic heuristic) {
        Solution solution=new Solution(heuristic);
        solution.solve(courses);
        Penalty penalty=new Linear();
        printAndCheck(courses, students, solution, penalty);
    }

    private static void printAndCheck(List<Course> courses, List<Student> students, Solution solution, Penalty penalty) {
        AveragePenalty averagePenalty=new AveragePenalty(penalty);
        System.out.println(averagePenalty.getAveragePenalty(students));
        Kempe kempe=new Kempe(courses,penalty);
        kempe.kempeChainInterChange();
        System.out.println(solution.checkSolution(courses));
        System.out.println(averagePenalty.getAveragePenalty(students));
        PairSwap pairSwap=new PairSwap(courses,penalty);
        pairSwap.pairSwap();
        System.out.println(solution.checkSolution(courses));
        System.out.println(averagePenalty.getAveragePenalty(students));
    }
}