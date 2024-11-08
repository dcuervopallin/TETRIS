package SI_V0.heuristic;

import game.elements.matrixTetris;

public class heurisScoreBasic implements heuristic{
    @Override
    public String getName() {
        return "MatrixScore Basic";
    }

    @Override
    public double getValue(matrixTetris matrix) {
        return matrix.getScore();
    }
}
