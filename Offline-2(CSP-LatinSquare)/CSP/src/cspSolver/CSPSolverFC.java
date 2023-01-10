package cspSolver;

import ValueHuristic.ValueHuristic;
import VariableHeuristicInterface.VariableHeuristic;
import variable.Variable;

public class CSPSolverFC {
    public static long numberOfBackTrack=0;
    public static long numberOfNode=0;
        private static VariableHeuristic varOrder;
        private static Variable[][] variables;
        private static  Variable firstVar=null;
        public static boolean FCSolver(VariableHeuristic varOrder, Variable [][] variables){
            cspSolver.CSPSolverFC.variables = variables;
            cspSolver.CSPSolverFC.varOrder=varOrder;
            firstVar=null;
            numberOfBackTrack=0;
            numberOfNode=0;
            return recursiveFCSolver();
        }
        private static boolean recursiveFCSolver(){
            Variable var=varOrder.getNextVariable();
            if(firstVar==null){
                firstVar=var;
            }
            if(var==null){
                return true;
            }
//            System.out.println(var.row+" "+var.col);
//             int n=variables.length;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                for (int k = 0; k < n; k++) {
//                    System.out.print(variables[i][j].domain[k]+" ");
//                }
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
            for(int val= var.getAValue();val>0;val=var.getAValue()){
//            System.out.println(val);
                ValueHuristic.forward(val);
//            for (int i = 0; i < variables.length; i++) {
//                for (int j = 0; j < variables.length; j++) {
//                    System.out.print(variables[i][j]);
//                }
//                System.out.println();
//            }
                boolean result= update(var);
                numberOfNode++;
                if(result){
                     if(recursiveFCSolver()){
                         return true;
                     }
                        else{
                            backTrack( var);
                            ValueHuristic.backTrack(val);
                        }
                }
                else{
                    backTrack( var);
                    ValueHuristic.backTrack(val);
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
                if (variables[var.row][k].value == 0) {

                    variables[var.row][k].backTrack(var.value-1);
                }
            }
            for (int k = 0; k < variables.length; k++) {
                if (variables[k][var.col].value == 0) {
                    variables[k][var.col].backTrack(var.value-1);
                }
            }
        }

        private static boolean update(Variable var) {
            boolean result=true;
            for (int k = 0; k < variables.length; k++) {
                if(variables[var.row][k].value==0) {
                    result &= variables[var.row][k].update(var.value - 1);
                }
            }
            for (int k = 0; k < variables.length; k++) {
                if(variables[k][var.col].value==0) {
                    result &= variables[k][var.col].update(var.value - 1);
                }
            }
            return result;
        }
}
