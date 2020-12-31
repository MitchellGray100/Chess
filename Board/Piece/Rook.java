package Board.Piece;

public class Rook extends abstractPiece implements Piece {
	
	Rook(color pieceColor)
	{
		pieceType = type.ROOK;
		this.pieceColor = pieceColor;
	}
}
