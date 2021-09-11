package piecesPackage;

import piecesPackage.AbstractPiece;
import piecesPackage.Piece;

public class Bishop extends AbstractPiece implements Piece {

	Bishop(Color pieceColor) {
		setType(Type.BISHOP);
		setColor(pieceColor);
		setValue(3);
	}

}
