package Board.Piece;

public abstract class abstractPiece implements Piece {
	public type pieceType;
	public color pieceColor;
	
	@Override
	public type getType() {
		return pieceType;
	}

	@Override
	public color getColor() {
		return pieceColor;
	}

	@Override
	public void setType(type pieceType) {
		this.pieceType = pieceType;
		
	}

	@Override
	public void setColor(color pieceColor) {
		this.pieceColor = pieceColor;
		
	}

}
