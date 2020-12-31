package Board.Piece;

public class Bishop extends abstractPiece implements Piece {
	
	Bishop(color pieceColor)
	{
		setType(type.BISHOP);
		setColor(pieceColor);
		setValue(3);
	}

}
