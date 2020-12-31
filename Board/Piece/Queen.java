package Board.Piece;

public class Queen extends abstractPiece implements Piece {
	
	Queen(color pieceColor)
	{
		pieceType = type.QUEEN;
		this.pieceColor = pieceColor;
	}
}
