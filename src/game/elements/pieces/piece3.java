package game.elements.pieces;

import game.elements.piece;

//0 1 0
//0 1 0
//0 1 1
public class piece3 extends piece {
    final static private boolean[][] state1 = {{true, false}, {true, false}, {true, true}};
    final static private boolean[][] state2 = {{true, true, true}, {true, false, false}};
    final static private boolean[][] state3 = {{true, true}, {false, true}, {false, true}};
    final static private boolean[][] state4 = {{false, false, true}, {true, true, true}};

    public piece3() {
        super(state1, state2, state3, state4);
    }

    public int getNumberPiece(){
        return 3;
    }

    @Override
    public int getTotalRotations() {
        return 4;
    }

    public piece3 clone(){
        return new piece3();
    }
}
