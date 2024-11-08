package SI_V0.modelsDecision.decisionV1;
import SI_V0.modelsDecision.actionDecision;
import SI_V0.heuristic.heuristic;
import utils.treeStaff.nodeGame;
import utils.treeStaff.stateGame;
import utils.treeStaff.transitionGame;

import java.util.ArrayList;
import java.util.Random;

public class decision1 implements actionDecision {
    @Override
    public String getName(){
        return "decision with one ExplorationTreeDeepth";
    }

    @Override
    public int[] getAction(stateGame param, heuristic param1) {
        return exploreTree(param, param1);
    }

    private int[] exploreTree(stateGame rootTree, heuristic param1){
        ArrayList<nodeGame> firstDeepth = rootTree.exploreNode(param1);

        if (firstDeepth.isEmpty()){
            return new int[] {0, 0};
        }else{
            Random rand = new Random();
            transitionGame result = firstDeepth.get(rand.nextInt(firstDeepth.size())).getTransition();
            return new int[] {result.getColumna(), result.getRotation()};
        }
    }
}
