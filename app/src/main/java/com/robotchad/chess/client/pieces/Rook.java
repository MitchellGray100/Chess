package com.robotchad.chess.client.pieces;


public class Rook extends AbstractPiece implements Piece {

    Rook(Color pieceColor) {
        setType(Type.ROOK);
        setColor(pieceColor);
        setValue(5);
    }
}
