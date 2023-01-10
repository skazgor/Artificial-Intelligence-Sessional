package cspSolver;

import VariableHeuristicInterface.VariableHeuristic;
import variable.Variable;

public class CSPSolverBT {
    public static long numberOfBackTrack=0;
    public static long numberOfNode=0;
    private static VariableHeuristic varOrder;
   private static Variable[][] variables;
   private static  Variable firstVar=null;
    public static boolean BTSolver(VariableHeuristic varOrder, Variable [][] variables){
        CSPSolverBT.variables = variables;
        CSPSolverBT.varOrder=varOrder;
        firstVar=null;
        numberOfBackTrack=0;
        numberOfNode=0;
        return recursiveBTSolver();
    }
    private static boolean recursiveBTSolver(){
        Variable var=varOrder.getNextVariable();
        if(firstVar==null){
            firstVar=var;
        }
        if(var==null){
            return true;
        }
//        System.out.println(var.row+" "+var.col);
//        int n=variables.length;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                for (int k = 0; k < n; k++) {
//                    System.out.print(variables[i][j].domain[k]+" ");
//                }
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
        numberOfNode++;
        for(int val= var.getAValue();val>0;val=var.getAValue()){
//            System.out.println(val);
//            for (int i = 0; i < variables.length; i++) {
//                for (int j = 0; j < variables.length; j++) {
//                    System.out.print(variables[i][j]);
//                }
//                System.out.println();
//            }
            update(var);
            boolean result=recursiveBTSolver();
            if(result){
                return true;
            }
            else{
                backTrack( var);
            }
        }
        if(var!=firstVar){
            varOrder.add(var);
        }
        numberOfBackTrack++;
        var.reset();
        return false;
    }

    private static void backTrack(Variable var) {
        for (int k = 0; k < variables.length; k++) {
            if(variables[var.row][k].value==0){
                variables[var.row][k].backTrack(var.value-1);
            }
        }
        for (int k = 0; k < variables.length; k++) {
            if(variables[k][var.col].value==0){
                variables[k][var.col].backTrack(var.value-1);
            }
        }
    }

    private static void update(Variable var) {
        for (int k = 0; k < variables.length; k++) {
            if(variables[var.row][k].value==0){
                variables[var.row][k].update(var.value-1);
            }

        }
        for (int k = 0; k < variables.length; k++) {
            if(variables[k][var.col].value==0){
                variables[k][var.col].update(var.value-1);
            }
        }
    }

}
