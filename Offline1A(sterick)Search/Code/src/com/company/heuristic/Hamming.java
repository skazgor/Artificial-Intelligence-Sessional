package com.company.heuristic;

import com.company.Node;

public class Hamming extends HeuristicType{
    @Override
    public int heuristicValue(Node node) {
        int distance = 0;
        int[][] array = node.getMatrix();
        int length = node.getMatrix().length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (array[i][j] == 0) continue;
                if (array[i][j] == i * length + j + 1) distance++;
            }
        }
        return length*length-distance-1;
    }
}
