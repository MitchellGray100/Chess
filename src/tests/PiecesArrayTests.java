package tests;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import board.Board;
import board.BoardImpl;
import piecesPackage.Piece;

public class PiecesArrayTests {

	@Test
	public void leftWhiteRookPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(0)).getType(), Piece.Type.ROOK);
		assertSame((chess.piecesGetter(0)).getColor(), Piece.Color.WHITE);
	}

	@Test
	public void leftWhiteKnightPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(1)).getType(), Piece.Type.KNIGHT);
		assertSame((chess.piecesGetter(1)).getColor(), Piece.Color.WHITE);
	}

	@Test
	public void leftWhiteBishopPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(2)).getType(), Piece.Type.BISHOP);
		assertSame((chess.piecesGetter(2)).getColor(), Piece.Color.WHITE);
	}

	@Test
	public void WhiteQueenPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(3)).getType(), Piece.Type.QUEEN);
		assertSame((chess.piecesGetter(3)).getColor(), Piece.Color.WHITE);
	}

	@Test
	public void WhiteKingPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(4)).getType(), Piece.Type.KING);
		assertSame((chess.piecesGetter(4)).getColor(), Piece.Color.WHITE);
	}

	@Test
	public void rightWhiteRookPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(7)).getType(), Piece.Type.ROOK);
		assertSame((chess.piecesGetter(7)).getColor(), Piece.Color.WHITE);
	}

	@Test
	public void rightWhiteKnightPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(6)).getType(), Piece.Type.KNIGHT);
		assertSame((chess.piecesGetter(6)).getColor(), Piece.Color.WHITE);
	}

	@Test
	public void rightWhiteBishopPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(5)).getType(), Piece.Type.BISHOP);
		assertSame((chess.piecesGetter(5)).getColor(), Piece.Color.WHITE);
	}

	@Test
	public void WhitePawnPosTest() {
		Board chess = new BoardImpl();
		for (int i = 8; i < 16; i++) {
			assertSame((chess.piecesGetter(i)).getType(), Piece.Type.PAWN);
			assertSame((chess.piecesGetter(i)).getColor(), Piece.Color.WHITE);
		}
	}

	@Test
	public void leftBlackRookPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(16)).getType(), Piece.Type.ROOK);
		assertSame((chess.piecesGetter(16)).getColor(), Piece.Color.BLACK);
	}

	@Test
	public void leftBlackKnightPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(17)).getType(), Piece.Type.KNIGHT);
		assertSame((chess.piecesGetter(17)).getColor(), Piece.Color.BLACK);
	}

	@Test
	public void leftBlackBishopPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(18)).getType(), Piece.Type.BISHOP);
		assertSame((chess.piecesGetter(18)).getColor(), Piece.Color.BLACK);
	}

	@Test
	public void BlackQueenPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(19)).getType(), Piece.Type.QUEEN);
		assertSame((chess.piecesGetter(19)).getColor(), Piece.Color.BLACK);
	}

	@Test
	public void BlackKingPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(20)).getType(), Piece.Type.KING);
		assertSame((chess.piecesGetter(20)).getColor(), Piece.Color.BLACK);
	}

	@Test
	public void rightBlackRookPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(23)).getType(), Piece.Type.ROOK);
		assertSame((chess.piecesGetter(23)).getColor(), Piece.Color.BLACK);
	}

	@Test
	public void rightBlackKnightPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(22)).getType(), Piece.Type.KNIGHT);
		assertSame((chess.piecesGetter(22)).getColor(), Piece.Color.BLACK);
	}

	@Test
	public void rightBlackBishopPosTest() {
		Board chess = new BoardImpl();
		assertSame((chess.piecesGetter(21)).getType(), Piece.Type.BISHOP);
		assertSame((chess.piecesGetter(21)).getColor(), Piece.Color.BLACK);
	}

	@Test
	public void BlackPawnPosTest() {
		Board chess = new BoardImpl();
		for (int i = 24; i < 32; i++) {
			assertSame((chess.piecesGetter(i)).getType(), Piece.Type.PAWN);
			assertSame((chess.piecesGetter(i)).getColor(), Piece.Color.BLACK);
		}
	}
}
