package game.elements;

// Cada una de las piezas que forman parte del juego
public abstract class piece implements Cloneable {
    // Cada una de las formas de la pieza en las 4 rotaciones, representadas mediante una matriz de bools
    final private boolean[][] state1;
    final private boolean[][] state2;
    final private boolean[][] state3;
    final private boolean[][] state4;
    // Variable donde almacenamos el estado rotacional de la pieza, SIEMPRE EMPIEZA EN 0!
    private int stateRotation;

    // Constructor con los distintas rotaciones de cada una de las piezas, stateRotation SIEMPRE EMPIEZA EN 0!
    public piece(boolean[][] state1, boolean[][] state2, boolean[][] state3, boolean[][] state4) {
        this.state1 = state1;
        this.state2 = state2;
        this.state3 = state3;
        this.state4 = state4;
        this.stateRotation = 0;
    }

    // Método abstracto para facilitar la diferenciación de las piezas mediante la asignación de un número identificativo
    public abstract int getNumberPiece();

    public abstract int getTotalRotations();
    // Según el estado de rotación devolvemos un estado de rotacion o otro
    public boolean[][] getStateRotation() {
        if (this.stateRotation == 0) {
            return state1;
        } else if (this.stateRotation == 1) {
            return state2;
        } else if (this.stateRotation == 2) {
            return state3;
        }else
            return state4;
    }
    public int getRotation() {
        return this.stateRotation;
    }
    // Cambiamos el estado de rotación en 1, se rota en sentido horario
    public void changeRotation() {
        this.stateRotation = (this.stateRotation + 1) % this.getTotalRotations();
    }

    // Devolvemos el tamaño del elemento actual
    public int[] getSize(){
        boolean[][] currentRotationPiece = getStateRotation();
        return new int[] {currentRotationPiece.length, currentRotationPiece[0].length};
    }

    // Método para clonar la pieza
    @Override
    public piece clone() {
        try {
            return (piece) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // No debería ocurrir, ya que implementamos Cloneable
        }
    }

    @Override
    public String toString() {
        boolean[][] currentRotationPiece = getStateRotation();
        String aux = "";
        for (int i = 0; i < currentRotationPiece.length; i++) {
            for (int j = 0; j < currentRotationPiece[i].length; j++) {
                aux += (currentRotationPiece[i][j] ? "1 " : "0 ");
            }
                aux += "\n";
            }
        return aux;
    }
}
