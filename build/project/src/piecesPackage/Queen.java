package piecesPackage;

public class Queen extends AbstractPiece implements Piece {

    Queen(Color pieceColor) {
        setType(Type.QUEEN);
        setColor(pieceColor);
        setValue(9);
    }
}
