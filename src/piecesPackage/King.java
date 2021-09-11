package piecesPackage;

import piecesPackage.AbstractPiece;
import piecesPackage.Piece;

public class King extends AbstractPiece implements Piece {

    King(Color pieceColor) {
        setType(Type.KING);
        setColor(pieceColor);
        setValue(100);
        setMoved(false);
    }
}
