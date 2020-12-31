package Board.Piece;

public class Bishop extends abstractPiece implements Piece {
	
	Bishop(color pieceColor)
	{
		pieceType = type.BISHOP;
		this.pieceColor = pieceColor;
	}

}
