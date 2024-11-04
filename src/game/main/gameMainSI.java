package game.main;

import utils.treeStaff.stateGame;
import game.elements.*;

public class gameMainSI {
    private matrixTetris matrixTetris;
    private piece currentPiece, nextPiece;
    private final bagSystemPieces bagPieces;

    // Para implementar que se pueda solamente usar la piece en el juego
    public gameMainSI(piece piece) {
        this.bagPieces = new bagSystemPieces(piece);
        this.nextPiece = bagPieces.getPiece();
        this.matrixTetris = new matrixTetris();
    }

    // Cada pieza con la que se jugará será aleatoria
    public gameMainSI(){
        this.bagPieces = new bagSystemPieces();
        this.nextPiece = bagPieces.getPiece();
        this.matrixTetris = new matrixTetris();
    }

    // Muestra el número de cada columna despues de la visualización de la matriz del tetris
    private String getStringHUD(){
        String aux = "";
        int numberCol = 0;
        while (numberCol < matrixTetris.getWidth()){
            aux += numberCol + " ";
            numberCol++;
        }
        return aux;
    }

    // Muestra el estado actual de la partida
    public void showState(){
        System.out.println("POSICION TABLERO:");
        System.out.println(matrixTetris);
        System.out.println(getStringHUD());
        System.out.println("Score: " + matrixTetris.getScore());
    }

    // Devuelve el estado del juego para poder escoger una acción
    public stateGame getInput(){
        this.currentPiece = this.nextPiece;
        this.nextPiece = bagPieces.getPiece();
        return new stateGame(this.nextPiece, this.currentPiece, this.matrixTetris.getClone());
    }

    // Recoge una acción dada por el agente y la lleva a cabo en la matriz
    public void playGame(int col, int rotation){
        matrixTetris.setPlay(currentPiece, col, rotation);
    }

    // Getters
    public boolean playing() {
        return matrixTetris.getPlaying();
    }

    public int getScore(){
        return matrixTetris.getScore();
    }
}
