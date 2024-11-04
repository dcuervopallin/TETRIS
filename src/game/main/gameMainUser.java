package game.main;
import game.elements.*;
import java.util.Scanner;

public class gameMainUser{
    final private matrixTetris matrixTetris = new matrixTetris();
    final private String HUDcolMatriz = getStringHUD();
    final private bagSystemPieces bagPieces = new bagSystemPieces();

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
    private void showState(){
        System.out.println("POSICION TABLERO:");
        System.out.println(matrixTetris);
        System.out.println(HUDcolMatriz);
        System.out.println("Score: " + matrixTetris.getScore());
    }

    // Bucle principal, se recoge la información del usuario y se vuelca en la matriz, así hasta que se pierda
    public int playGame(){
        Scanner scanner = new Scanner(System.in);
        piece secondP = bagPieces.getPiece();

        while(matrixTetris.getPlaying()){
            piece piece = secondP;
            secondP = bagPieces.getPiece();
            int parametres[] = newPlayByUser(piece,secondP, scanner);
            matrixTetris.setPlay(piece, parametres[0], parametres[1] % 4);
        }
        System.out.println("Perdiste, score final: " + matrixTetris.getScore());
        scanner.close();

        return matrixTetris.getScore();
    }

    // Interacción con el usuario, se muestra el estado actualy se recogen la posición y rotación deseada por el usuario
    private int[] newPlayByUser(piece piece, piece secondP, Scanner scanner) {
        int colInput = -1;
        int rotationInput = -1;

        // Loop for column input
        while (colInput < 0 || colInput >= matrixTetris.getWidth() || rotationInput < 0) {
            showState();
            System.out.println("SIGUIENTE PIEZA A COLOCAR:\n" + secondP);
            System.out.println("PIEZA A COLOCAR:\n" + piece);
            System.out.println("Introduce una columna (0-" + (matrixTetris.getWidth() -1) + "): ");

            String texto = scanner.nextLine();
            try {
                colInput = Integer.parseInt(texto);
                if (colInput < 0 || colInput >= 9) {
                    System.out.println("El número debe estar entre 0 y 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("El texto introducido no es un número entero válido.");
                colInput = -1; // Reset the value to continue the loop
            }
            System.out.println("Introduce una rotación para la pieza (0-3): ");
            texto = scanner.nextLine();
            try {
                rotationInput = Integer.parseInt(texto);
                if (rotationInput < 0 || rotationInput > 3) {
                    System.out.println("El número debe estar entre 0 y 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("El texto introducido no es un número entero válido.");
                rotationInput = -1; // Reset the value to continue the loop
            }
        }

        return new int[]{colInput, rotationInput};
    }
}
