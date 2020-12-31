package Board.Piece;

public class Queen extends abstractPiece implements Piece {
	
	Queen(color pieceColor)
	{
		setType(type.QUEEN);
		setColor(pieceColor);
		setValue(9);
	}
}
