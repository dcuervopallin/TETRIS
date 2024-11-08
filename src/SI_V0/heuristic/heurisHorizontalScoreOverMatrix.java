package SI_V0.heuristic;

import game.elements.matrixTetris;

import java.util.ArrayList;

public class heurisHorizontalScoreOverMatrix implements heuristic{
    @Override
    public String getName() {
        return "Horizontal Score Over Matrix";
    }

    @Override
    public double getValue(matrixTetris matrixIn) {
        int[][] matrix = matrixIn.getMatrixTetrisInts();
        int lastInt;
        double cont = 0;

        for(int i = 0; i < matrix.length; i++){
            ArrayList<Integer> aux = new ArrayList<>();
            lastInt = 0;

            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] != 0){
                    aux.add(j - lastInt);
                    lastInt = j;
                }
            }
            cont += scoreOverHorDGaps(aux, matrix[i].length);
        }
        return cont;
    }

    private double scoreOverHorDGaps(ArrayList<Integer> dGaps, int total){
        double mean = meanOverDGaps(dGaps);
        if(dGaps.isEmpty()){
            return 1.5;
        }else if(dGaps.size() == 1){
            return 0.8;
        } else if(dGaps.size() == 2){
            if (dGaps.get(1) <= 2) {
                return 0.7;
            }
            else {
                return 0.55;
            }
        } else if (dGaps.size() == 3) {
            if (mean <= 2.5) {
                return 0.5;
            } else {
                return 0.35;
            }
        }else if(dGaps.size() > total * 0.8){
                return 0.4;
        } else {
            if (mean - (maxOverDGaps(dGaps) * (double) ( 1 / ( dGaps.size() - 1))) <= 2.5){
                return 0.3;
            } else  {
                return 0.2;
            }
        }
    }

    private double meanOverDGaps(ArrayList<Integer> dGaps){
        double total = 0;
        for(int i = 1; i < dGaps.size(); i++){
            total += dGaps.get(i);
        }
        return total / dGaps.size() - 1;
    }

    private int maxOverDGaps(ArrayList<Integer> dGaps){
        int max = 0;
        for(int i = 1; i < dGaps.size(); i++){
            if(dGaps.get(i) > 3.5){
                max ++;
            }
        }
        return max;
    }
}
