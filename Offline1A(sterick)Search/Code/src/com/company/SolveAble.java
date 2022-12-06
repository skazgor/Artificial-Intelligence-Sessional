package com.company;

public class SolveAble {
    public static boolean isSolveAble(int [][] matrix ){
        boolean solveAble=false;
        if((matrix.length&1)==1 ){
            if((inversionCount(matrix)&1)==0) solveAble=true;
        }
        else{
            if(((inversionCount(matrix)^emptyPosition(matrix))&1)==1){
                solveAble=true;
            }
        }
        return solveAble;
    }
    private static int inversionCount(int [][] matrix){
        int count=0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                for (int k = i; k < matrix.length; k++) {
                    if(k==i) {
                        for (int l = j + 1; l < matrix.length; l++) {
                            if ((matrix[k][l] != 0) && matrix[i][j] > matrix[k][l]) {
                                count++;
                            }
                        }
                    }
                    else{
                        for (int l = 0; l < matrix.length; l++) {
                            if((matrix[k][l] !=0) && matrix[i][j]>matrix[k][l] ){
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
    private static int emptyPosition(int [][] matrix){
        int pos=0;
        for (int i = matrix.length-1; i >=0; i--) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j]==0){
                    pos= matrix.length-i;
                }
            }
        }
        return pos;
    }
}
