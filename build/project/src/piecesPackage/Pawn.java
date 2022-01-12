package piecesPackage;

public class Pawn extends AbstractPiece implements Piece {

	private location.LocationImpl previousLocation;
	private int turnMoved;

	Pawn(Color pieceColor) {
		setType(Type.PAWN);
		setColor(pieceColor);
		setValue(1);
		setPrevLocation(new location.LocationImpl(-100, -100));
		setTurnMoved(0);
	}

	public void setTurnMoved(int turn) {
		turnMoved = turn;
	}

	public int getTurnMoved() {
		return turnMoved;
	}

	public void setPrevLocation(location.LocationImpl prev) {
		previousLocation = prev;
	}

	public location.LocationImpl getPrevLocation() {
		return previousLocation;
	}
}
