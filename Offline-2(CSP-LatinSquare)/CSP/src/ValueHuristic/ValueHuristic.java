package ValueHuristic;

import variable.Variable;

import java.util.Arrays;

public class ValueHuristic {
    public static Node [] arr;
    public static void initialize(int n, Variable[][] variables){
        arr=new Node[n];
        for (int i = 0; i < n; i++) {
            arr[i]=new Node(i);
        }
        for (int i = 0; i < variables.length; i++) {
            for (int j = 0; j < n; j++) {
                if(variables[i][j].value!=0){
                    arr[variables[i][j].value-1].count++;
                }
            }
        }
    }
    public static void forward(int value){
        arr[value-1].count++;
    }
    public static void backTrack(int value){
        arr[value-1].count--;
    }
    public static Node [] getPreference(){
        Node [] temp=new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i]=arr[i];
        }
        Arrays.sort(temp);
        return temp;
    }
}
