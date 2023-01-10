
import VariableHeuristicInterface.VariableHeuristic;
import Variable_Huristic.*;
import cspSolver.CSPSolverBT;
import cspSolver.CSPSolverFC;
import variable.Variable;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static String [] data={"\\data\\d-10-01.txt","\\data\\d-10-06.txt","\\data\\d-10-07.txt","\\data\\d-10-08.txt","\\data\\d-10-09.txt","\\data\\d-15-01.txt","\\data\\test.txt"};
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Do you want to run manually or automatically?");
        System.out.println("1. Manually");
        System.out.println("2. Automatically");
        int choice= scanner.nextInt();
        if(choice==1){
            System.out.println("Enter the number of the fileName you want to run:(1-6)");
            int fileNumber=scanner.nextInt();
            System.out.println("Enter the number of the variable heuristic you want to run:(1-5)");
            int varHuristic=scanner.nextInt();
            System.out.println("Enter the Solver you want to run:(1-2) 1. BackTracking 2. ForwardChecking");
            int solver=scanner.nextInt();
            Variable[][] variables=ReadInput.initialize(data[fileNumber-1]);
            if(solver==1) {
                if (varHuristic == 1) {
                    BT_VH1(variables);
                } else if (varHuristic == 2) {
                    BT_VH2(variables);
                } else if (varHuristic == 3) {
                    BT_VH3(variables);
                } else if (varHuristic == 4) {
                    BT_VH4(variables);
                } else if (varHuristic == 5) {
                    BT_VH5(variables);
                }
            }
            else if(solver==2){
                if (varHuristic == 1) {
                    FC_VH1(variables);
                } else if (varHuristic == 2) {
                    FC_VH2(variables);
                } else if (varHuristic == 3) {
                    FC_VH3(variables);
                } else if (varHuristic == 4) {
                    FC_VH4(variables);
                } else if (varHuristic == 5) {
                    FC_VH5(variables);
                }
            }
        }
        else if (choice==2) {
            for (int k = 0; k < 6; k++) {
                System.out.println("File Name: "+data[k]);
                Variable[][] variables = ReadInput.initialize(data[k]);
//                BT_VH1(variables);
//                 variables = ReadInput.initialize(data[k]);
//                BT_VH2(variables);
//                 variables = ReadInput.initialize(data[k]);
//                BT_VH3(variables);
//                 variables = ReadInput.initialize(data[k]);
//                BT_VH4(variables);
//                 variables = ReadInput.initialize(data[k]);
                BT_VH5(variables);
//                variables = ReadInput.initialize(data[k]);
//                FC_VH1(variables);
//               variables = ReadInput.initialize(data[k]);
//               FC_VH2(variables);
//                variables = ReadInput.initialize(data[k]);
//                FC_VH3(variables);
//                variables = ReadInput.initialize(data[k]);
//                FC_VH4(variables);
//                variables = ReadInput.initialize(data[k]);
//                FC_VH5(variables);
            }
        }
        else {
            System.out.println("Invalid choice");
        }

        }
        private static void BT_VH1(Variable[][] variables){
            VariableHeuristic varOder = new VAH1(variables);
            System.out.println("BT_VH1");
            long start = System.nanoTime();
            if (CSPSolverBT.BTSolver(varOder, variables)) {
                for (Variable[] variable : variables) {
                    for (int j = 0; j < variables.length; j++) {
                        System.out.print(variable[j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution");
            }
            long end = System.nanoTime();
            long elapsedTime = end - start;
            System.out.println("Time: " + elapsedTime/1000000.0+"ms");
            System.out.println("Number of nodes: "+CSPSolverBT.numberOfNode);
            System.out.println("Number of backtracks: "+CSPSolverBT.numberOfBackTrack);
        }
        private static void BT_VH2(Variable[][] variables){
            VariableHeuristic varOder = new VAH2(variables);
            System.out.println("BT_VH2");
            long start = System.currentTimeMillis();
            if (CSPSolverBT.BTSolver(varOder, variables)) {
                for (Variable[] variable : variables) {
                    for (int j = 0; j < variables.length; j++) {
                        System.out.print(variable[j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution");
            }
            long end = System.currentTimeMillis();
            long elapsedTime = end - start;
            System.out.println("Time: " + elapsedTime +"ms");
            System.out.println("Number of nodes: "+CSPSolverBT.numberOfNode);
            System.out.println("Number of backtracks: "+CSPSolverBT.numberOfBackTrack);
        }
        private static void BT_VH3(Variable[][] variables){
            VariableHeuristic varOder = new VAH3(variables);
            System.out.println("BT_VH3");
            long start = System.nanoTime();
            if (CSPSolverBT.BTSolver(varOder, variables)) {
                for (Variable[] variable : variables) {
                    for (int j = 0; j < variables.length; j++) {
                        System.out.print(variable[j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution");
            }
            long end = System.nanoTime();
            long elapsedTime = end - start;
            System.out.println("Time: " + elapsedTime/1000000.0+"ms");
            System.out.println("Number of nodes: "+CSPSolverBT.numberOfNode);
            System.out.println("Number of backtracks: "+CSPSolverBT.numberOfBackTrack);
        }
        private static void BT_VH4(Variable[][] variables){
            VariableHeuristic varOder = new VAH4(variables);
            System.out.println("BT_VH4");
            long start = System.nanoTime();
            if (CSPSolverBT.BTSolver(varOder, variables)) {
                for (Variable[] variable : variables) {
                    for (int j = 0; j < variables.length; j++) {
                        System.out.print(variable[j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution");
            }
            long end = System.nanoTime();
            long elapsedTime = end - start;
            System.out.println("Time: " + elapsedTime/1000000.0+"ms");
            System.out.println("Number of nodes: "+CSPSolverBT.numberOfNode);
            System.out.println("Number of backtracks: "+CSPSolverBT.numberOfBackTrack);
        }
        private static void BT_VH5(Variable[][] variables){
            VariableHeuristic varOder = new VAH5(variables);
            System.out.println("BT_VH5");
            long start = System.currentTimeMillis();
            if (CSPSolverBT.BTSolver(varOder, variables)) {
                for (Variable[] variable : variables) {
                    for (int j = 0; j < variables.length; j++) {
                        System.out.print(variable[j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution");
            }
            long end = System.currentTimeMillis();
            long elapsedTime = end - start;
            System.out.println("Time: " + elapsedTime+"ms");
            System.out.println("Number of nodes: "+CSPSolverBT.numberOfNode);
            System.out.println("Number of backtracks: "+CSPSolverBT.numberOfBackTrack);
        }
        private static void FC_VH1(Variable[][] variables){
            VariableHeuristic varOder = new VAH1(variables);
            System.out.println("FC_VH1");

            long start = System.nanoTime();
            if (CSPSolverFC.FCSolver(varOder, variables)) {
                for (Variable[] variable : variables) {
                    for (int j = 0; j < variables.length; j++) {
                        System.out.print(variable[j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution");
            }
            long end = System.nanoTime();
            long elapsedTime = end - start;
            System.out.println("Time: " + elapsedTime/1000000.0+"ms");
            System.out.println("Number of nodes: "+CSPSolverFC.numberOfNode);
            System.out.println("Number of backtracks: "+CSPSolverFC.numberOfBackTrack);
        }
        private static void FC_VH2(Variable[][] variables){
            VariableHeuristic varOder = new VAH2(variables);
            System.out.println("FC_VH2");
            long start = System.currentTimeMillis();
            if (CSPSolverFC.FCSolver(varOder, variables)) {
                for (Variable[] variable : variables) {
                    for (int j = 0; j < variables.length; j++) {
                        System.out.print(variable[j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution");
            }
            long end = System.currentTimeMillis();
            long elapsedTime = end - start;
            System.out.println("Time: " + elapsedTime+"ms");
            System.out.println("Number of nodes: "+CSPSolverFC.numberOfNode);
            System.out.println("Number of backtracks: "+CSPSolverFC.numberOfBackTrack);
        }
        private static void FC_VH3(Variable[][] variables){
            VariableHeuristic varOder = new VAH3(variables);
            System.out.println("FC_VH3");
            long start = System.nanoTime();
            if (CSPSolverFC.FCSolver(varOder, variables)) {
                for (Variable[] variable : variables) {
                    for (int j = 0; j < variables.length; j++) {
                        System.out.print(variable[j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution");
            }
            long end = System.nanoTime();
            long elapsedTime = end - start;
            System.out.println("Time: " + elapsedTime/1000000.0+"ms");
            System.out.println("Number of nodes: "+CSPSolverFC.numberOfNode);
            System.out.println("Number of backtracks: "+CSPSolverFC.numberOfBackTrack);
        }
        private static void FC_VH4(Variable[][] variables){
            VariableHeuristic varOder = new VAH4(variables);
            System.out.println("FC_VH4");
            long start = System.nanoTime();
            if (CSPSolverFC.FCSolver(varOder, variables)) {
                for (Variable[] variable : variables) {
                    for (int j = 0; j < variables.length; j++) {
                        System.out.print(variable[j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution");
            }
            long end = System.nanoTime();
            long elapsedTime = end - start;
            System.out.println("Time: " + elapsedTime/1000000.0+"ms");
            System.out.println("Number of nodes: "+CSPSolverFC.numberOfNode);
            System.out.println("Number of backtracks: "+CSPSolverFC.numberOfBackTrack);
        }
        private static void FC_VH5(Variable[][] variables){
            VariableHeuristic varOder = new VAH5(variables);
            System.out.println("FC_VH5");
            long start = System.currentTimeMillis();
            if (CSPSolverFC.FCSolver(varOder, variables)) {
                for (Variable[] variable : variables) {
                    for (int j = 0; j < variables.length; j++) {
                        System.out.print(variable[j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution");
            }
            long end = System.currentTimeMillis();
            long elapsedTime = end - start;
            System.out.println("Time: " + elapsedTime+"ms");
            System.out.println("Number of nodes: "+CSPSolverFC.numberOfNode);
            System.out.println("Number of backtracks: "+CSPSolverFC.numberOfBackTrack);
        }
}