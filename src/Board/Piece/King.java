package Board.Piece;

public class King extends abstractPiece implements Piece {
	
	King(color pieceColor)
	{
		setType(type.KING);
		setColor(pieceColor);
		setValue(100);
	}
}
