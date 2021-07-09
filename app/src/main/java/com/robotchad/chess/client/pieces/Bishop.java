package com.robotchad.chess.client.pieces;


public class Bishop extends AbstractPiece implements Piece {
	
	Bishop(Color pieceColor)
	{
		setType(Type.BISHOP);
		setColor(pieceColor);
		setValue(3);
	}

}
