package game.elements.pieces;

import game.elements.piece;

//1 1 0
//0 1 1
public class piece6 extends piece {
    final static private boolean[][] state1 = {{true, true, false}, {false, true, true}};
    final static private boolean[][] state2 = {{false, true}, {true, true}, {true, false}};

    public piece6() {
        super(state1, state2, null, null);
    }

    public int getNumberPiece(){
        return 6;
    }

    @Override
    public int getTotalRotations() {
        return 2;
    }

    public piece6 clone(){
        return new piece6();
    }


}
