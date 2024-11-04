package game.elements.pieces;

import game.elements.piece;

//0 1 0
//0 1 0
//1 1 0
public class piece1 extends piece {
    final static private boolean[][] state1 = {{false, true}, {false, true}, {true, true}};
    final static private boolean[][] state2 = {{true, false, false}, {true, true, true}};
    final static private boolean[][] state3 = {{true, true}, {true, false}, {true, false}};
    final static private boolean[][] state4 = {{true, true, true}, {false, false, true}};

    public piece1() {
        super(state1, state2, state3, state4);
    }

    public int getNumberPiece(){
        return 1;
    }

    @Override
    public int getTotalRotations() {
        return 4;
    }

    @Override
    public piece1 clone(){
        return (piece1) super.clone();
    }
}
