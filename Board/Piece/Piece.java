package Board.Piece;

/**
 * 
 * Interface for all of the Pieces. Standardizes Type and Color.
 * null on the board represents no piece.
 */
public interface Piece 
{
	/**
	 * Gets the type of piece
	 * @return The type of the piece
	 */
	public type getType();

	/**
	 * Sets the type of piece
	 * @param pieceType The pieceType of the piece
	 */
	public void setType(type pieceType);


	/**
	 * Gets the Color of the piece
	 * @return The color of the piece
	 */
	public color getColor();

	/**
	 * Sets the Color of the piece
	 * @param pieceColor The color of the piece
	 */
	public void setColor(color pieceColor);

	enum type
	{
		PAWN,
		KNIGHT,
		BISHOP,
		ROOK,
		QUEEN,
		KING;
	}
	enum color
	{
		BLACK,
		WHITE;
	}
}
