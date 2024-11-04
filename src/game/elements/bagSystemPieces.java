package game.elements;

import game.elements.pieces.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class bagSystemPieces {
    private final piece[] totalPieces = {new piece1(),
            new piece2(),
            new piece3(),
            new piece4(),
            new piece5(),
            new piece6(),
            new piece7()};
    private final ArrayList<piece> bagPieces;
    private final Random rand = new Random();
    private final piece usedPiece;

    public bagSystemPieces(piece usedPiece) {
        this.bagPieces = new ArrayList<>();
        this.usedPiece = usedPiece;
    }

    public bagSystemPieces() {
        this.bagPieces = new ArrayList<>();
        this.usedPiece = null;
    }

    private void refillBag(){
        Collections.addAll(this.bagPieces, this.totalPieces);
        Collections.shuffle(this.bagPieces, this.rand);
    }

    public piece getPiece() {
        if (usedPiece != null) {
            return usedPiece;
        } else {
            if(bagPieces.isEmpty()) {
                refillBag();
            }

            return bagPieces.removeLast();
        }
    }
}