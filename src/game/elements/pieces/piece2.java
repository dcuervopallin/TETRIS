package game.elements.pieces;

import game.elements.piece;

//1 1
//1 1
public class piece2 extends piece {
    final static private boolean[][] state = {{true, true}, {true, true}};

    public piece2() {
        super(state, null, null, null);
    }

    public int getNumberPiece(){
        return 2;
    }

    @Override
    public int getTotalRotations() {
        return 1;
    }

    public piece2 clone(){
        return new piece2();
    }
}
