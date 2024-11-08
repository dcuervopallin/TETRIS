package SI_V0.modelsDecision.decisionV2;
import SI_V0.heuristic.heurisScoreBasic;
import SI_V0.heuristic.heuristic;
import SI_V0.modelsDecision.actionDecision;
import utils.treeStaff.nodeGame;
import utils.treeStaff.stateGame;
import utils.treeStaff.transitionGame;

import java.util.ArrayList;
import java.util.Random;

public class decision2 implements actionDecision {
    @Override
    public String getName(){
        return "decision with two ExplorationTreeDeepth";
    }

    @Override
    public int[] getAction(stateGame param, heuristic param1) {
        return exploreTree(param, param1);
    }

    private int[] exploreTree(stateGame rootTree,  heuristic param1){
        ArrayList<nodeGame> firstDeepth = rootTree.exploreNode(param1);
        ArrayList<transitionGame> secondDeepth = new ArrayList<>();
        double maxScore = -500;

        for(nodeGame node : firstDeepth){
            ArrayList<nodeGame> aux = node.getState1().exploreNode(param1);

            if (!aux.isEmpty()){
                double scoreAux = new heurisScoreBasic().getValue(aux.getFirst().getState2().getMatrixTetris());
                if (scoreAux > maxScore) {
                    maxScore = scoreAux;
                    secondDeepth.clear();
                    secondDeepth.add(node.getTransition());
                } else if (scoreAux == maxScore) {
                    secondDeepth.add(node.getTransition());
                }
            }
        }

        if(secondDeepth.isEmpty()){
            return new int[]{0,0};
        }else {
            Random rand = new Random();
            int index = rand.nextInt(secondDeepth.size());
            return new int[] {secondDeepth.get(index).getColumna(), secondDeepth.get(index).getRotation()};
        }
    }
}
