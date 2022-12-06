package com.company;

import java.util.Arrays;
import java.util.Comparator;

public class Node implements Comparator,Comparable {
    private int [][] matrix;
    private int h;
    private int g=0;

    public Node(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    private Node parent=null;

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Node(int[][] matrix, int g) {
        this.matrix = matrix;
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Node node1 = (Node) o1;
        Node node2 = (Node) o2;
        if(node1.g+ node1.h> node2.g+ node2.h){
            return  1;
        }
        else if(node1.g+ node1.h< node2.g+ node2.h)
            return -1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j]!=node.matrix[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }

    @Override
    public String toString() {
       String s="";
         for(int [] arr: matrix) {
            s=s+ Arrays.toString(arr)+"\n";
         }
         s=s+ h+"  "+g+"\n";
         return s;
    }

    @Override
    public int compareTo(Object o) {
        Node node = (Node) o;
        if(g+ h> node.g+ node.h){
            return  1;
        }
        else if(g+h< node.g+ node.h)
            return -1;
        return 0;
    }
}
