package heuristic;

import game.elements.matrixTetris;

public class heurisScoreByNeighbor implements heuristic {
    @Override
    public String getName() {
        return "Score method based on Neighbor";
    }

    @Override
    public double getValue(matrixTetris matrixIn) {
        int [][] matrix = matrixIn.getMatrixTetrisInts();
        double count = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                count += scoreNodeByNeighbor(matrixIn, i, j);
            }
        }

        return count;
    }

    private double scoreNodeByNeighbor(matrixTetris matrix, int i, int j) {
        int[] neighbors = getNeighbors(matrix, i, j);
        double blocks = 1;

        for(int neighbor : neighbors) {
            if (neighbor > 0) blocks++;
        }

        return 1.0 / blocks;
    }

    private int[] getNeighbors(matrixTetris matrixIn, int row, int col) {
        int [][] matrix = matrixIn.getMatrixTetrisInts();
        int [] neighbors = new int[5];
        neighbors[0] = matrix[row][col];

        if( row - 1 >= 0 ) neighbors[1] = matrix[row-1][col];
        else neighbors[1] = -1;
        if (row + 1 < matrix.length) neighbors[2] = matrix[row+1][col];
        else neighbors[2] = -1;
        if (col - 1 >= 0 ) neighbors[3] = matrix[row][col-1];
        else neighbors[3] = -1;
        if (col + 1 < matrix[0].length) neighbors[4] = matrix[row][col+1];
        else neighbors[4] = -1;

        return neighbors;
    }
}
