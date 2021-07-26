package com.robotchad.chess.client.pieces;


public class Knight extends AbstractPiece implements Piece {

    Knight(Color pieceColor) {
        setType(Type.KNIGHT);
        setColor(pieceColor);
        setValue(3);
    }

}
