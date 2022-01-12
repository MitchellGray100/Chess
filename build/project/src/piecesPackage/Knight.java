package piecesPackage;

import piecesPackage.AbstractPiece;
import piecesPackage.Piece;

public class Knight extends AbstractPiece implements Piece {

    Knight(Color pieceColor) {
        setType(Type.KNIGHT);
        setColor(pieceColor);
        setValue(3);
    }

}
