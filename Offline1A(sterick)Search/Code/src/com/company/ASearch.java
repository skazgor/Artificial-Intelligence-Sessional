package com.company;

import com.company.heuristic.HeuristicType;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class ASearch {
    private int explored=0;
    private int expanded=1;
    private PriorityQueue<Node> open=new PriorityQueue<>();
    private Set<Node> closeSet= new HashSet<>();

    void search(Node node, HeuristicType type){
        int h=type.heuristicValue(node);
        node.setH(h);
       // System.out.println(h);
        open.add(node);
        Node test=new Node(copy(node.getMatrix()));
        closeSet.add(node);
        int i=0;
        while(true) {
            i++;
            if (open.isEmpty()) {
                System.out.println("Failed!!!!!!!!");
                return;
            }
            //System.out.println(open);
            Node current =open.poll();
//            System.out.println(current);
            explored++;

            if(type.heuristicValue(current)==0){
              success(current);
              return;
            }
            if(expand(current,type)){
                break;
            }
        }
    }
    private void success(Node node){
        System.out.println("Success!!!!!!!!!");
        System.out.println("The optimal cost to reach the goal state is:"+node.getG());
        System.out.println("Here is the solution step------");
        print(node);
        System.out.println("Number of explored nodes:"+explored+" and expanded nodes:"+expanded);
        return;
    }
    private boolean expand(Node node,HeuristicType type) {
//        System.out.println("Exapand ");
//        System.out.println(node);
        boolean findGoal=false;
        int step=node.getG();
        step++;
        int [][] array=node.getMatrix();
        int length= array.length;
        for (int i = 0; i <length ; i++) {
            for (int j = 0; j < length; j++) {
                if(array[i][j]==0){
                    if(j+1<length){
                        int [][] newArray=copy(array);
                       newArray[i][j]=newArray[i][j+1];
                       newArray[i][j+1]=0;
                       Node temp=new Node(newArray,step);
                       int h=type.heuristicValue(temp);
                        //System.out.println(h);
                       temp.setH(h);
                       temp.setParent(node);
                       if(closeSet.add(temp)){
                           open.add(temp);
                           expanded++;
                       }
                       if(h==0) {
                           findGoal=true;
                           success(temp);
                       }
//                        System.out.println(temp);
                    }
                    if(j-1>-1){
                        int [][] newArray=copy(array);
                        newArray[i][j]=newArray[i][j-1];
                        newArray[i][j-1]=0;
                        Node temp=new Node(newArray,step);
                        int h=type.heuristicValue(temp);
                        temp.setH(h);
                        temp.setParent(node);
                        if(closeSet.add(temp)){
                            open.add(temp);
                            expanded++;
                        }
                        if(h==0) {
                            findGoal=true;
                            success(temp);
                        }
//                        System.out.println(temp);
                    }
                    if(i+1<length){
                        int [][] newArray=copy(array);
                        newArray[i][j]=newArray[i+1][j];
                        newArray[i+1][j]=0;
                        Node temp=new Node(newArray,step);
                        int h=type.heuristicValue(temp);
                        temp.setH(h);
                        temp.setParent(node);
                        if(closeSet.add(temp)){
                            open.add(temp);
                            expanded++;
                        }
                        if(h==0) {
                            findGoal=true;
                            success(temp);
                        }
//                        System.out.println(temp);
                    }
                    if(i-1>-1){
                        int [][] newArray=copy(array);
                        newArray[i][j]=newArray[i-1][j];
                        newArray[i-1][j]=0;
                        Node temp=new Node(newArray,step);
                        int h=type.heuristicValue(temp);
                        temp.setH(h);
                        temp.setParent(node);
                        if(closeSet.add(temp)){
                            open.add(temp);
                            expanded++;
                        }
                        if(h==0) {
                            findGoal=true;
                            success(temp);
                        }
//                        System.out.println(temp);
                    }
//                    System.out.println(open.size());
                }
            }
        }
        return findGoal;
    }
   private int [][] copy(int [][] arr){
       int length= arr.length;
       int [][] newArray=new int[length][length];
       for (int i = 0; i < length; i++) {
           for (int j = 0; j < length; j++) {
               newArray[i][j]=arr[i][j];
           }
       }
       return newArray;
    }
    void print(Node node){
        if(node ==null) return;
            print(node.getParent());
        System.out.println("------------->>>>>");
        System.out.println(node);
    }
}
