package heuristic;

import game.elements.matrixTetris;

public class huerisVerticalMaskOverMatrix implements heuristic{
    @Override
    public String getName() {
        return "Score method based on VerticalMaskOverMatrix";
    }

    @Override
    public double getValue(matrixTetris matrix) {
        int[][] matr = matrix.getMatrixTetrisInts();
        int cont = 0;
        for(int i = 0; i < matrix.getWidth(); i++){
            for(int j = 0; j < matrix.getHeight(); j++){
                if(matr[j][i] != 0 || j == matrix.getHeight() - 1){
                    cont += j;
                    break;
                }
            }
        }

        return cont;
    }
}
