package SI_V1.training;

import SI_V0.heuristic.*;
import SI_V0.modelsDecision.decisionV1.decision1;
import utils.configGame;
import utils.playerSI;

import java.io.File;

public class getDataTrain {
    public static void trainTo(int iter){
        int i = 0;
        while(i < iter){
            playerSI a = new playerSI();
            heuristic[] bestHeuristics = {new heurisHorizontalScoreOverMatrix(),
                    new heurisScoreOverMatrix(),
                    new heurisVerticalMaskOverMatrixWithLoss(),
                    new huerisVerticalMaskOverMatrix()
            };
            for(heuristic heuristic : bestHeuristics){
                configGame b = new configGame.configBuilder(new decision1(), heuristic)
                        .trainPath(System.getProperty("user.home") +  File.separator + "TETRIS_PRUEBAS" + File.separator + "trainingData" + File.separator + "data" + (i / 150) + ".csv")
                        .trainScoreGapToStore(35)
                        .build();
                i += a.initGame(b)[2];
            }

        }

    }
}
