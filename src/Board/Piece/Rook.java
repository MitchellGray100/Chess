package Board.Piece;

public class Rook extends abstractPiece implements Piece {
	
	Rook(color pieceColor)
	{
		setType(type.ROOK);
		setColor(pieceColor);
		setValue(5);
	}
}
