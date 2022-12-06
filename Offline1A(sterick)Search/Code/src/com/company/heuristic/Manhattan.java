package com.company.heuristic;

import com.company.Node;

public class Manhattan extends HeuristicType {

    @Override
    public int heuristicValue(Node node) {
        int distance = 0;
        int length = node.getMatrix().length;
        int[][] array = node.getMatrix();
        for (int i = 0; i <length ; i++) {
            for (int j = 0; j < length; j++) {
                if(array[i][j]==0)continue;
                distance+=Math.abs(i-((array[i][j]-1)/length));
                distance+=Math.abs(j-((array[i][j]-1)%length));
            }
        }
        return distance;
    }
}
