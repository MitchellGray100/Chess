package Board.Piece;

public abstract class abstractPiece implements Piece {
	private type pieceType;
	private color pieceColor;
	private int pieceValue;
	
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

	@Override
	public int getValue() {
		return pieceValue;
	}

	@Override
	public void setValue(int value) {
		pieceValue = value;
	}

}
