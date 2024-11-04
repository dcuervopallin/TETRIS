package game.elements;

public class matrixTetris {
    // Tamaño de la reja de elementos del tetris
    final private int height = 20, width = 10;

    // Reja del tetris representada con ints, 0-vacio
    private int[][] matrixTetrisInts = new int[height][width];

    // Cada matrix tendrá un score asignado y una variable playing que nos indica si se ha perdido o no
    private int score = 0;
    private boolean playing = true;

    public matrixTetris() {
    }

    public int[][] getMatrixTetrisInts() {
        return matrixTetrisInts;
    }

    private matrixTetris(int[][] matrixTetrisInts) {
        this.matrixTetrisInts = matrixTetrisInts;
    }

    // Getters
    public int getHeight() { return height; }
    public boolean getPlaying() {
        return playing;
    }
    public int getScore(){
        return score;
    }
    public int getWidth(){
        return width;
    }

    // Dada una situación de partida se añade una pieza en una columna y con una rotacion dada
    public void setPlay(piece piece, int col, int rotation){
        int [] pieceSize;
        int posCol;

        // Rotamos la pieza lo indicado
        while (rotation != 0){
            piece.changeRotation();
            rotation--;
        }
        pieceSize = piece.getSize();

        // Se ajusta la columna dada para que no se salga por la derecha de la requilla, todos los centros de las piezas están en 0,0
        if (col + pieceSize[1] >= this.width){
            posCol = col - (pieceSize[1] + col - this.width);
        }else {
            posCol = col;
        }

        // Se añade la pieza a la matriz en la columna ya calculada
        addPieceToMatrix(piece, posCol);

        // Una vez se añade la pieza se actualiza el score
        updateScore();
    }

    // Se actualiza el score, se añade 10, por el hecho de añadir piezas, además en caso de que una fila esté sin 0s
    // se elimina esa fila, bajando las superiores y añadiendo un score de 500
    private void updateScore(){
        int[] scores = {40, 100, 300, 1200};

        if (!this.playing){
            return;
        }
        boolean full = true;
        int totalRows = 0;
        for (int i = this.height - 1; i >= 0; i--){
            if (full){
                i = this.height - 1;
            }
            full = true;
            // Se comprueba que todos los valores son 0
            for (int j = 0; j < this.width; j++){
                if (this.matrixTetrisInts[i][j] == 0){
                    full = false;
                }
            }
            // En caso de que este full, no hay 0s, se intercambian todas las filas (la que está full e inferiores) por
            // la inmedientemente superior y se añade un vector de 0s a la fila menor
            if (full){
                if (totalRows > 3){
                    this.score += scores[3];
                }else{
                    this.score += scores[totalRows];
                }
                totalRows++;
                for(int h = i; h > 0; h--){
                    this.matrixTetrisInts[h] = this.matrixTetrisInts[h - 1];
                }
                this.matrixTetrisInts[0] = new int[width];
            }
        }
    }

    // Dada una columna dada, se comprueba si los elementos son dibujables, empezando desde la parte superior, y bajando
    // hasta que no sea dibujable, en caso de que no sea dibujable en la parte superior se considera el juego perdido
    public int[] addPieceToMatrix(piece piece, int col){
        boolean[][] pieceRot = piece.getStateRotation();
        int[] pieceSize = piece.getSize();
        int actPosFile = 0;

        this.playing = checkPieceOnMatrix(piece, col, actPosFile);
        while(this.playing){
            if (checkPieceOnMatrix(piece, col, actPosFile)) {
                actPosFile++;
            }else {
                actPosFile--;
                break;
            }
        }

        // Actualización de la matriz y dibujado de la pieza en la posición más inferior para la columna propuesta
        for (int i = 0; i < pieceSize[0]; i++){
            for (int j = 0; j < pieceSize[1]; j++){
                if (pieceRot[i][j]){
                    this.matrixTetrisInts[i + actPosFile][j + col] = piece.getNumberPiece();
                }
            }
        }

        return new int[] {actPosFile, col};
    }

    // Se comprueba si la pieza puede colocarse en un lugar específico de la matrix del tetris
    private boolean checkPieceOnMatrix(piece piece, int col, int fil){
        boolean[][] pieceRot = piece.getStateRotation();
        int[] pieceSize = piece.getSize();

        // Si no tiene espacio falso
        if (fil + pieceSize[0] - 1 >= this.height){
            return false;
        }

        // Para cada elemento positivo del mapa boleano de la pieza, se comprueba si la posicion que le corresponde en el
        // mapa es dibujable (contiene un o, es decir no hay elementos), devuelve false en caso de que una posicion de
        // la pieza no es dibujable
        for (int i = 0; i < pieceSize[0]; i++){
            for (int j = 0; j < pieceSize[1]; j++){
                if (pieceRot[i][j] && this.matrixTetrisInts[fil + i][col + j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public matrixTetris getClone() {
        int[][] newMatrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(this.matrixTetrisInts[i], 0, newMatrix[i], 0, width);
            newMatrix[i] = this.matrixTetrisInts[i].clone();
        }
        matrixTetris clone = new matrixTetris(newMatrix);
        clone.score = this.score;
        clone.playing = this.playing;
        return clone;
    }

    @Override
    public String toString() {
        String aux = "";
        for (int i = 0; i < this.matrixTetrisInts.length; i++) {
            for (int j = 0; j < this.matrixTetrisInts[i].length; j++) {
                if (this.matrixTetrisInts[i][j] == 0) {
                    aux += "0 ";
                }else {
                    aux += this.matrixTetrisInts[i][j] + " ";
                }
            }
            aux += "\n"; // Nueva línea al final de cada fila
        }
        return aux;
    }
}
