package game.elements.pieces;

import game.elements.piece;

// 1 1 1 1
public class piece5 extends piece {
    final static private boolean[][] state1 = {{true, true, true, true}};
    final static private boolean[][] state2 = {{true}, {true}, {true}, {true}};

    public piece5() {
        super(state1,state2,null,null);
    }

    public int getNumberPiece(){
        return 5;
    }

    @Override
    public int getTotalRotations() {
        return 2;
    }

    @Override
    public piece5 clone(){
        return (piece5) super.clone();
    }
}
