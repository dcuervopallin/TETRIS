package game.elements.pieces;

import game.elements.piece;

//1 1 1
//0 1 0
public class piece4 extends piece {
    final static private boolean[][] state1 = {{true, true, true}, {false, true, false}};
    final static private boolean[][] state2 = {{false, true}, {true, true}, {false, true}};
    final static private boolean[][] state3 = {{false, true, false}, {true, true, true}};
    final static private boolean[][] state4 = {{true, false}, {true, true}, {true, false}};

    public piece4(){
        super(state1,state2,state3,state4);
    }

    public int getNumberPiece(){
        return 4;
    }

    @Override
    public int getTotalRotations() {
        return 4;
    }

    public piece4 clone(){
        return new piece4();
    }
}
