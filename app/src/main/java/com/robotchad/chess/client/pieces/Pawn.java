package com.robotchad.chess.client.pieces;


import com.robotchad.chess.client.location.LocationImpl;

public class Pawn extends AbstractPiece implements Piece {

    private LocationImpl previousLocation;
    private int turnMoved;

    Pawn(Color pieceColor) {
        setType(Type.PAWN);
        setColor(pieceColor);
        setValue(1);
        setPrevLocation(new LocationImpl(-100, -100));
        setTurnMoved(0);
    }

    public void setTurnMoved(int turn) {
        turnMoved = turn;
    }

    public int getTurnMoved() {
        return turnMoved;
    }

    public void setPrevLocation(LocationImpl prev) {
        previousLocation = prev;
    }

    public LocationImpl getPrevLocation() {
        return previousLocation;
    }
}
