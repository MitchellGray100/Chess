package com.robotchad.chess.client.pieces;

import com.robotchad.chess.client.location.Location;
import com.robotchad.chess.client.location.LocationImpl;

public abstract class AbstractPiece implements Piece {
	private Type pieceType;
	private Color pieceColor;
	private int pieceValue;
	private LocationImpl location;
	private boolean moved;
	private int arrayLocation;


	public int getArrayLocation() {
		return arrayLocation;
	}

	public void setArrayLocation(int arrayLocation) {
		this.arrayLocation = arrayLocation;
	}


	//Thinking we can use a Location variable here so we can store all white pieces in an array and
	// all black pieces in an array. Then when we need to know where each piece is we can just
	// search for the piece and its location.
	
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

	@Override
	public LocationImpl getLocation() {
		return location;
	}

	@Override
	public void setLocation(LocationImpl loc) {
		location = loc;

	}

	@Override
	public boolean isMoved() {
		return moved;
	}

	@Override
	public void setMoved(boolean moved) {
		this.moved = moved;
	}
}
