package utils;
import utils.treeStaff.stateGame;
import game.main.gameMainSI;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Representa el agente que interactúa con el juego
public class playerSI {
    public int[] initGame(configGame configGame) {
        gameMainSI game;
        BufferedWriter bufferedWriter = null;

        if (configGame.getPieceUsed() == null){
            game =  new gameMainSI();
        } else {
            game = new gameMainSI(configGame.getPieceUsed());
        }

        if(configGame.getPathStoreTrain() != null){
            try {
                File file = new File(configGame.getPathStoreTrain());
                File parentDir = file.getParentFile();
                
                // Crear el directorio si no existe
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }
                
                FileWriter writer = new FileWriter(file, true);
                bufferedWriter = new BufferedWriter(writer);
            }catch (IOException e){
                System.out.println(e);
            }
        }

        int iterations = 0, lastScore = 0, totalDataStore = 0;
        while(game.playing() && iterations < configGame.getIterations()){
            stateGame inputGame = game.getInput();
            int[] getAction = configGame.getActionDecision().getAction(inputGame, configGame.getHeuristic());
            game.playGame(getAction[0], getAction[1]);
            iterations++;
            if(configGame.isPrintMatrix()){
                game.showState();
            }
            
            if (bufferedWriter != null && lastScore + configGame.getScoreGapToStore() <= game.getScore()) {
                try{
                    bufferedWriter.write(inputGame.getStringToRNAA() + getAction[0] + "\t" + getAction[1] + "\t");
                    totalDataStore++;
                    lastScore = game.getScore();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
        }
        if (bufferedWriter != null) {
            try{
                bufferedWriter.close();
            }catch (IOException e){
                System.out.println(e);
            }
        }

        return new int[]{game.getScore(), iterations, totalDataStore};
    }
}
