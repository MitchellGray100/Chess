package piecesPackage;

import location.LocationImpl;

/**
 * Interface for all of the Pieces. Standardizes Type and Color. null on the
 * board represents no pieces.
 */
public interface Piece {
	/**
	 * Gets the Type of pieces
	 *
	 * @return The Type of the pieces
	 */
	Type getType();

	/**
	 * Sets the Type of pieces
	 *
	 * @param pieceType The pieceType of the pieces
	 */
	void setType(Type pieceType);

	/**
	 * Gets the Color of the pieces
	 *
	 * @return The Color of the pieces
	 */
	Color getColor();

	/**
	 * Sets the Color of the pieces
	 *
	 * @param pieceColor The Color of the pieces
	 */
	void setColor(Color pieceColor);

	/**
	 * Gets the value of the pieces returns the value of the pieces
	 */
	int getValue();

	/**
	 * Sets the value of the pieces
	 *
	 * @param value the value to set
	 */
	void setValue(int value);

	/**
	 * Sets the location of the piece
	 *
	 * @param loc the location to set
	 */
	void setLocation(LocationImpl loc);

	/**
	 * Gets the location of the piece returns the location of the piece
	 */
	LocationImpl getLocation();

	void setMoved(boolean moved);

	boolean isMoved();

	void setArrayLocation(int arrayLocation);

	int getArrayLocation();

	/**
	 * An enumeration of all possible pieces
	 */
	enum Type {
		PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING
	}

	/**
	 * An enumeration of the team colors
	 */
	enum Color {
		BLACK, WHITE
	}

}
