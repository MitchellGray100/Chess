package Board.Piece;

public class King extends abstractPiece implements Piece {
	
	King(color pieceColor)
	{
		pieceType = type.KING;
		this.pieceColor = pieceColor;
	}
}
