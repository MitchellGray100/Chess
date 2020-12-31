package Board.Piece;

public class Knight extends abstractPiece implements Piece {
	
	Knight(color pieceColor)
	{
		setType(type.KNIGHT);
		setColor(pieceColor);
		setValue(3);
	}

}
