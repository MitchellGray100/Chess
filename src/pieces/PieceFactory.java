package pieces;

/** A factory class for creating chess pieces */
public class PieceFactory {

	/** 
	 * Creates a instance of the factory
	 */
	public PieceFactory() { }
	
	/**
	 * Returns a piece of the specified type and color
	 * @param type - type of the chess piece
	 * @param color - color of the chess piece
	 * @return chess piece of specified type and color
	 */
	public Piece getPiece(Piece.Type type, Piece.Color color) {
		switch (type) {
		case PAWN :
			return new Pawn(color);
		case KNIGHT :
			return new Knight(color);
		case BISHOP :
			return new Bishop(color);
		case ROOK :
			return new Rook(color);
		case QUEEN :
			return new Queen(color);
		default :
			return new King(color);
		}
	}
	
}
