import SI_V1.RNA.activationFuncs.funcSoftPlus;
import SI_V1.RNA.neuralNetwork;
import game.main.gameMainUser;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        neuralNetwork neuralNetwork = new neuralNetwork(new int[]{1, 3, 2}, new funcSoftPlus());
        String path =System.getProperty("user.home") +  File.separator + "TETRIS_PRUEBAS" + File.separator + "RNAS" + File.separator + "prueba.txt";
        neuralNetwork.writeNN(path);
        neuralNetwork b = new neuralNetwork(path);
        System.out.println(neuralNetwork);
        System.out.println(b);
                /*getDataTrain.trainTo(1);
        analysis a = new analysis.analysisBuilder()
                .setToDocResults(true)
                .setMaxIterations(150)
                .setTrainingEpochs(50)
                .setTopToSee(5)
                .build();*/
        //a.analysisSystem();
        /*playerSI a = new playerSI();
        configGame b = new configGame.configBuilder(new decision1(), new heurisHorizontalScoreOverMatrix())
                .iterations(60)
                .setPiece(new piece1())
                .printMatrix(true)
                .build();
        int[] c = a.initGame(b);
        System.out.print(c[0] + ", " + c[1]);*/
    }
}