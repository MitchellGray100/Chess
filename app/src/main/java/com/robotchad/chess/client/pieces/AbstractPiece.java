package com.robotchad.chess.client.pieces;

import com.robotchad.chess.client.location.Location;

public abstract class AbstractPiece implements Piece {
	private Type pieceType;
	private Color pieceColor;
	private int pieceValue;
	
	@Override
	public Type getType() {
		return pieceType;
	}

	@Override
	public Color getColor() {
		return pieceColor;
	}

	@Override
	public void setType(Type pieceType) {
		this.pieceType = pieceType;
		
	}

	@Override
	public void setColor(Color pieceColor) {
		this.pieceColor = pieceColor;
		
	}

	@Override
	public int getValue() {
		return pieceValue;
	}

	@Override
	public void setValue(int value) {
		pieceValue = value;
	}

}
