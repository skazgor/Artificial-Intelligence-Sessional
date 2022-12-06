package com.company;

import com.company.heuristic.Hamming;
import com.company.heuristic.HeuristicType;
import com.company.heuristic.Manhattan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file=new File("test.txt");
        Scanner scanner=new Scanner(file);
        int k=scanner.nextInt();
        int arr[][]=new int[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                String s= scanner.next("[0-9*]+");
                try {
                    arr[i][j]=Integer.parseInt(s);
                } catch (Exception e) {
                    arr[i][j]=0;
                }
            }
        }
        if(SolveAble.isSolveAble(arr)){
            System.out.println("Solvable!!!!!!!!!!");
        }else{
            System.out.println("Not solveAble!!!!!!!!!!!!");
            return;
        }
        HeuristicType hamming=new Hamming();
        HeuristicType manhattan=new Manhattan();
        ASearch search=new ASearch();
        Node node=new Node(arr);
        System.out.println("Solving using Manhattan Distance........");
        search.search(node,manhattan);
        System.out.println("Solving using Hamming Distance........");
        search.search(node,hamming);
    }
}
