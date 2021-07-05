package com.robotchad.chess.client.pieces;


public class King extends AbstractPiece implements Piece {
	
	King(Color pieceColor)
	{
		setType(Type.KING);
		setColor(pieceColor);
		setValue(100);
	}
}
