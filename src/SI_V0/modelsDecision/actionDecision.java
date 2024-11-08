package SI_V0.modelsDecision;

import SI_V0.heuristic.heuristic;
import utils.treeStaff.stateGame;

// Idea para dar soporte a varias implementaciones del SI
public interface actionDecision {
    public String getName();
    public int[] getAction(stateGame param, heuristic param1);
}
