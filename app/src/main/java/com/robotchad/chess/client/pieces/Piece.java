package com.robotchad.chess.client.pieces;


import com.robotchad.chess.client.location.LocationImpl;

/**
 * 
 * Interface for all of the Pieces. Standardizes Type and Color.
 * null on the board represents no pieces.
 */
public interface Piece 
{
	/**
	 * Gets the Type of pieces
	 * @return The Type of the pieces
	 */
	public Type getType();

	/**
	 * Sets the Type of pieces
	 * @param pieceType The pieceType of the pieces
	 */
	public void setType(Type pieceType);


	/**
	 * Gets the Color of the pieces
	 * @return The Color of the pieces
	 */
	public Color getColor();

	/**
	 * Sets the Color of the pieces
	 * @param pieceColor The Color of the pieces
	 */
	public void setColor(Color pieceColor);

	/**
	 * Gets the value of the pieces
	 * returns the value of the pieces
	 */
	public int getValue();
	
	/**
	 * Sets the value of the pieces
	 * @param value the value to set
	 */
	public void setValue(int value);

	/**
	 * Sets the location of the piece
	 * @param loc the location to set
	 */
	public void setLocation(LocationImpl loc);

	/**
	 * Gets the location of the piece
	 * returns the location of the piece
	 */
	public LocationImpl getLocation();
	
	/** An enumeration of all possible pieces */
	public enum Type
	{
		PAWN,
		KNIGHT,
		BISHOP,
		ROOK,
		QUEEN,
		KING;
	}
	
	/** An enumeration of the team colors */
	public enum Color
	{
		BLACK,
		WHITE;
	}
	
}
