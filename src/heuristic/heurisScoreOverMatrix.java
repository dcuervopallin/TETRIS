package heuristic;

import game.elements.matrixTetris;

public class heurisScoreOverMatrix  implements heuristic {
    @Override
    public String getName() {
        return "Score Over Matrix";
    }

    @Override
    public double getValue(matrixTetris matrix) {
        int[][] matr = matrix.getMatrixTetrisInts();
        int cont = 0;
        for(int i = 0; i < matrix.getWidth(); i++) {
            boolean aux = true;
            for (int j = 0; j < matrix.getHeight(); j++) {
                if (matr[j][i] == 0){
                    if (aux){
                        cont += 1;
                    }else{
                        cont -= 1;
                    }
                }else {
                    aux = false;
                }
            }
        }
        return cont;
    }
}
