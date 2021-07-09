package pieces;

public class Pawn extends AbstractPiece implements Piece {
	
	Pawn(Color pieceColor)
	{
		setType(Type.PAWN);
		setColor(pieceColor);
		setValue(1);
	}
}
