package SI_V0.heuristic;

import game.elements.matrixTetris;

public interface heuristic {
    public String getName();
    public double getValue(matrixTetris matrix);
}
