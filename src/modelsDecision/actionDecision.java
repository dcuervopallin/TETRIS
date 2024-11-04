package modelsDecision;

import heuristic.heuristic;
import utils.treeStaff.stateGame;

// Idea para dar soporte a varias implementaciones del SI
public interface actionDecision {
    public String getName();
    public int[] getAction(stateGame param, heuristic param1);
}
