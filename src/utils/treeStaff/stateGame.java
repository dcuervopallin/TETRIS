package utils.treeStaff;

import heuristic.heuristic;
import game.elements.matrixTetris;
import game.elements.piece;

import java.util.ArrayList;

public class stateGame {
    private final piece currentPiece;
    private final piece nextPiece;
    private final matrixTetris matrixTetris;

    public stateGame(piece nextPiece, piece currentPiece, game.elements.matrixTetris matrixTetris) {
        this.nextPiece = nextPiece;
        this.currentPiece = currentPiece;
        this.matrixTetris = matrixTetris;
    }

    public piece getCurrentPiece() {
        return currentPiece;
    }

    public piece getNextPiece() {
        return nextPiece;
    }

    public matrixTetris getMatrixTetris() {
        return matrixTetris;
    }

    public ArrayList<nodeGame> exploreNode(heuristic heuristic){
        piece piece = this.getCurrentPiece().clone();
        double maxScore = -500;
        ArrayList<nodeGame> maxStates = new ArrayList<nodeGame>();

        for (int j = 0; j < piece.getTotalRotations(); j++){
            for (int i = 0; i + piece.getSize()[1] - 1 < this.getMatrixTetris().getWidth(); i++){
                matrixTetris currentMatrix = this.getMatrixTetris().getClone();
                currentMatrix.setPlay(this.getCurrentPiece().clone(), i, j);
                double actScore = heuristic.getValue(currentMatrix);

                if (actScore >= maxScore && currentMatrix.getPlaying()){
                    maxStates.clear();
                    maxScore = actScore;
                    maxStates.add(new nodeGame(this, new transitionGame(i,j), new stateGame(null, this.getNextPiece(), currentMatrix)));
                }else if(actScore == maxScore && currentMatrix.getPlaying()){
                    maxStates.add(new nodeGame(this, new transitionGame(i,j), new stateGame(null, this.getNextPiece(), currentMatrix)));
                }
            }
            piece.changeRotation();
        }

        return maxStates;
    }

    public String getStringToRNAA(){
        String aux = this.currentPiece.getNumberPiece() + "\t" + this.nextPiece.getNumberPiece() + "\t";
        int [][] matrixTetris = this.matrixTetris.getMatrixTetrisInts();
        for (int i = 0; i < this.matrixTetris.getWidth(); i++){
            boolean aux1 = true;
            for (int j = 0; j < this.matrixTetris.getHeight(); j++){
                if (matrixTetris[j][i] != 0 && aux1){
                    aux += matrixTetris[j][i] + "\t";
                    aux1 = false;
                }
            }
            if (aux1){
                aux +=  "0\t";
            }
        }
        return aux + "\n";
    }

    @Override
    public String toString() {
        return "stateGame{" +
                "currentPiece=\n" + currentPiece +
                ", matrixTetris=\n" + matrixTetris +
                ", nextPiece=\n" + nextPiece +
                '}';
    }
}
