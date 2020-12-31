package Board.Piece;

public class Pawn extends abstractPiece implements Piece {
	
	Pawn(color pieceColor)
	{
		setType(type.PAWN);
		setColor(pieceColor);
		setValue(1);
	}
}
