package Board.Piece;

public class Pawn extends abstractPiece implements Piece {
	
	Pawn(color pieceColor)
	{
		pieceType = type.PAWN;
		this.pieceColor = pieceColor;
	}
}
