package game.elements.pieces;

import game.elements.piece;

//0 1 1
//1 1 0
public class piece7 extends piece {
    final static private boolean[][] state1 = {{false, true, true}, {true, true, false}};
    final static private boolean[][] state2 = {{true, false}, {true, true}, {false, true}};

    public piece7() {
        super(state1, state2, null, null);
    }

    public int getNumberPiece(){
        return 7;
    }

    @Override
    public int getTotalRotations() {
        return 2;
    }

    public piece7 clone(){
        return new piece7();
    }
}
