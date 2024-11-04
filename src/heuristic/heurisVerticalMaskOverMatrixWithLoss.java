package heuristic;

import game.elements.matrixTetris;

public class heurisVerticalMaskOverMatrixWithLoss implements heuristic {
    @Override
    public String getName() {
        return "Score method based on VerticalMaskOverMatrix with Loss";
    }

    @Override
    public double getValue(matrixTetris matrix) {
        int[][] matr = matrix.getMatrixTetrisInts();
        double cont = 0;

        for (int i = 0; i < matrix.getWidth(); i++) {
            boolean full = true;
            int aux = 0;
            double record = 0;
            for (int j = 0; j < matrix.getHeight(); j++) {
                if(matr[j][i] != 0 || j == matrix.getHeight() - 1){
                    if (full){
                        record += j;
                    }else{
                        record = record * Math.pow(0.8, aux);
                    }
                } else {
                    full = false;
                    aux ++;
                }
            }
            cont += record;
        }
        return cont;
    }
}
