package piecesPackage;

public class Rook extends AbstractPiece implements Piece {

    Rook(Color pieceColor) {
        setType(Type.ROOK);
        setColor(pieceColor);
        setValue(5);
    }
}
