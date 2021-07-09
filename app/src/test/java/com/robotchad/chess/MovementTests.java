package com.robotchad.chess;

import com.robotchad.chess.client.board.Board;
import com.robotchad.chess.client.board.BoardImpl;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovementTests {

	//PAWN TESTS
	@Test
	public void pawnWhiteWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(1, 0, 1, 1));
	}

	@Test
	public void pawnObstacleMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(1,1,2,0);
		assertFalse(chess.isValidMove(1, 0, 2, 0));
	}

	@Test
	public void pawnEnemyMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(6,0,2,1);
		assertTrue(chess.isValidMove(1, 0, 2, 1));
	}

	@Test
	public void pawnWhiteCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		assertTrue(chess.isValidMove(1,0,2,0));
	}
	@Test
	public void pawnWhiteCorrectTwoForwardMovementTest()
	{
		Board chess = new BoardImpl();
		assertTrue(chess.isValidMove(1,0,3,0));
	}

	@Test
	public void pawnWhiteSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(1, 0, 1, 0));
	}

	@Test
	public void pawnBlackWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(6, 0, 6, 1));
	}

	@Test
	public void pawnBlackCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		assertTrue(chess.isValidMove(6,0,5,0));
	}

	@Test
	public void pawnBlackCorrectTwoForwardMovementTest()
	{
		Board chess = new BoardImpl();
		assertTrue(chess.isValidMove(6,0,4,0));
	}

	@Test
	public void pawnBlackSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(6, 0, 6, 0));
	}


	//KNIGHT TESTS
	@Test
	public void knightWhiteWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(0, 1, 1, 1));
	}

	@Test
	public void knightObstacleMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(1,1,2,2);
		assertFalse(chess.isValidMove(0, 1, 2, 2));
	}

	@Test
	public void knightEnemyMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(6,0,2,2);
		assertTrue(chess.isValidMove(0, 1, 2, 2));
	}

	@Test
	public void knightWhiteCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		assertTrue(chess.isValidMove(0,1,2,0));
	}

	@Test
	public void knightWhiteSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(1, 0, 1, 0));
	}

	@Test
	public void knightBlackWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(7, 1, 6, 1));
	}

	@Test
	public void knightBlackCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		assertTrue(chess.isValidMove(7,1,5,0));
	}

	@Test
	public void knightBlackSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(7, 1, 7, 1));
	}

	@Test
	public void knightMultipleMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(7,1,3,3);
		assertTrue(chess.isValidMove(3, 3, 5, 2));
		assertTrue(chess.isValidMove(3, 3, 4, 1));
		assertTrue(chess.isValidMove(3, 3, 2, 1));

		assertTrue(chess.isValidMove(3, 3, 1, 2));
		assertTrue(chess.isValidMove(3, 3, 1, 4));
		assertTrue(chess.isValidMove(3, 3, 2, 5));
		assertTrue(chess.isValidMove(3, 3, 4, 5));
		assertTrue(chess.isValidMove(3, 3, 5, 4));
		assertFalse(chess.isValidMove(3, 3, 2, 2));
		assertFalse(chess.isValidMove(3, 3, 2, 3));
		assertFalse(chess.isValidMove(3, 3, 2, 4));
		assertFalse(chess.isValidMove(3, 3, 3, 2));
		assertFalse(chess.isValidMove(3, 3, 3, 4));
		assertFalse(chess.isValidMove(3, 3, 4, 2));
		assertFalse(chess.isValidMove(3, 3, 4, 3));
		assertFalse(chess.isValidMove(3, 3, 4, 4));
		assertFalse(chess.isValidMove(3, 3, 1, 1));
		assertFalse(chess.isValidMove(3, 3, 1, 5));
		assertFalse(chess.isValidMove(3, 3, 3, 1));
		assertFalse(chess.isValidMove(3, 3, 3, 5));
		assertFalse(chess.isValidMove(3, 3, 5, 1));
		assertFalse(chess.isValidMove(3, 3, 5, 3));
		assertFalse(chess.isValidMove(3, 3, 5, 5));


	}


	//BISHOP TESTS
	@Test
	public void bishopWhiteWrongMovementTest()
	{
		Board chess = new BoardImpl();
		chess.deleteSquare(0,3);
		assertFalse(chess.isValidMove(0, 2, 0, 3));
	}

	@Test
	public void bishopObstacleMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(0, 2, 1, 3));
	}

	@Test
	public void bishopEnemyMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(6,0,1,3);
		assertTrue(chess.isValidMove(0, 2, 1, 3));
	}

	@Test
	public void bishopWhiteCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		chess.deleteSquare(1,3);
		assertTrue(chess.isValidMove(0,2,1,3));
	}

	@Test
	public void bishopWhiteSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(0, 2, 0, 2));
	}

	@Test
	public void bishopBlackWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(7, 2, 7, 1));
	}

	@Test
	public void bishopBlackCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		chess.deleteSquare(6,3);
		assertTrue(chess.isValidMove(7,2,6,3));
	}

	@Test
	public void bishopBlackSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(7, 2, 7, 2));
	}

	@Test
	public void bishopMultipleMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(7,2,3,3);
		assertTrue(chess.isValidMove(3, 3, 2, 2));
		assertTrue(chess.isValidMove(3, 3, 2, 4));
		assertTrue(chess.isValidMove(3, 3, 4, 2));
		assertTrue(chess.isValidMove(3, 3, 4, 4));
		assertTrue(chess.isValidMove(3, 3, 5, 1));
		assertTrue(chess.isValidMove(3, 3, 5, 5));
		assertTrue(chess.isValidMove(3, 3, 1, 5));
		assertTrue(chess.isValidMove(3, 3, 1, 1));
		assertFalse(chess.isValidMove(3, 3, 3, 2));
		assertFalse(chess.isValidMove(3, 3, 2, 3));
		assertFalse(chess.isValidMove(3, 3, 3, 4));
		assertFalse(chess.isValidMove(3, 3, 4, 3));
		assertFalse(chess.isValidMove(3, 3, 5, 2));
		assertFalse(chess.isValidMove(3, 3, 3, 3));
		assertFalse(chess.isValidMove(3, 3, 3, 4));
		assertFalse(chess.isValidMove(3, 3, 4, 1));
		assertFalse(chess.isValidMove(3, 3, 3, 1));
		assertFalse(chess.isValidMove(3, 3, 2, 1));
		assertFalse(chess.isValidMove(3, 3, 1, 2));
		assertFalse(chess.isValidMove(3, 3, 1, 3));
		assertFalse(chess.isValidMove(3, 3, 1, 4));
		assertFalse(chess.isValidMove(3, 3, 2, 5));
		assertFalse(chess.isValidMove(3, 3, 3, 5));
		assertFalse(chess.isValidMove(3, 3, 4, 5));

	}
	@Test
	public void bishopBlockedMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(7,2,3,3);
		chess.forceMove(1,0,4,2);
		chess.forceMove(1,1,4,4);
		chess.forceMove(1,2,2,2);
		chess.forceMove(1,3,2,4);
		assertFalse(chess.isValidMove(3,3,5,1));
		assertFalse(chess.isValidMove(3,3,5,5));
		assertFalse(chess.isValidMove(3,3,1,5));
		assertFalse(chess.isValidMove(3,3,1,1));

	}


	//ROOK TESTS
	@Test
	public void rookWhiteWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(0, 0, 1, 1));
	}

	@Test
	public void rookObstacleMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(7, 0, 5, 1));
	}

	@Test
	public void rookEnemyMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(1,0,6,0);
		assertTrue(chess.isValidMove(7, 0, 6, 0));
	}

	@Test
	public void rookWhiteCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		chess.deleteSquare(0,1);
		assertTrue(chess.isValidMove(0,0,0,1));
	}

	@Test
	public void rookWhiteSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(0, 0, 0, 0));
	}

	@Test
	public void rookBlackWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(7, 0, 6, 1));
	}

	@Test
	public void rookBlackCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		chess.deleteSquare(7,1);
		assertTrue(chess.isValidMove(7,0,7,1));
	}

	@Test
	public void rookBlackSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(7, 0, 7, 0));
	}

	@Test
	public void rookMultipleMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(7,0,3,3);
		assertFalse(chess.isValidMove(3, 3, 2, 2));
		assertFalse(chess.isValidMove(3, 3, 2, 4));
		assertFalse(chess.isValidMove(3, 3, 4, 2));
		assertFalse(chess.isValidMove(3, 3, 4, 4));
		assertFalse(chess.isValidMove(3, 3, 5, 1));
		assertFalse(chess.isValidMove(3, 3, 5, 5));
		assertFalse(chess.isValidMove(3, 3, 1, 5));
		assertFalse(chess.isValidMove(3, 3, 1, 1));
		assertTrue(chess.isValidMove(3, 3, 3, 2));
		assertTrue(chess.isValidMove(3, 3, 2, 3));
		assertTrue(chess.isValidMove(3, 3, 3, 4));
		assertTrue(chess.isValidMove(3, 3, 4, 3));
		assertFalse(chess.isValidMove(3, 3, 5, 2));
		assertFalse(chess.isValidMove(3, 3, 3, 3));
		assertTrue(chess.isValidMove(3, 3, 3, 4));
		assertFalse(chess.isValidMove(3, 3, 4, 1));
		assertTrue(chess.isValidMove(3, 3, 3, 1));
		assertFalse(chess.isValidMove(3, 3, 2, 1));
		assertFalse(chess.isValidMove(3, 3, 1, 2));
		assertTrue(chess.isValidMove(3, 3, 1, 3));
		assertFalse(chess.isValidMove(3, 3, 1, 4));
		assertFalse(chess.isValidMove(3, 3, 2, 5));
		assertTrue(chess.isValidMove(3, 3, 3, 5));
		assertFalse(chess.isValidMove(3, 3, 4, 5));
		assertFalse(chess.isValidMove(3, 3, 6, 3));
	}
	@Test
	public void rookBlockedMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(7,0,3,3);
		chess.forceMove(1,0,3,2);
		chess.forceMove(1,1,3,4);
		chess.forceMove(1,2,2,3);
		chess.forceMove(1,3,4,3);
		assertFalse(chess.isValidMove(3,3,3,0));
		assertFalse(chess.isValidMove(3,3,0,3));
		assertFalse(chess.isValidMove(3,3,3,7));
		assertFalse(chess.isValidMove(3,3,5,3));

	}


	//QUEEN TESTS
	@Test
	public void queenWhiteWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(0, 4, 2, 2));
	}

	@Test
	public void queenObstacleMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(0, 4, 4, 0));
	}

	@Test
	public void queenEnemyMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(6,0,1,4);
		assertTrue(chess.isValidMove(0, 4, 1, 4));
	}

	@Test
	public void queenWhiteCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		chess.deleteSquare(1,3);
		assertTrue(chess.isValidMove(0,3,6,3));
	}

	@Test
	public void queenWhiteSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(0, 4, 0, 4));
	}

	@Test
	public void queenBlackWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(7, 4, 6, 1));
	}

	@Test
	public void queenBlackCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		chess.deleteSquare(6,3);
		assertTrue(chess.isValidMove(7,3,1,3));
	}

	@Test
	public void queenBlackSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertFalse(chess.isValidMove(7, 4, 7, 4));
	}

	@Test
	public void queenMultipleMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(7,3,3,3);
		assertTrue(chess.isValidMove(3, 3, 2, 2));
		assertTrue(chess.isValidMove(3, 3, 2, 4));
		assertTrue(chess.isValidMove(3, 3, 4, 2));
		assertTrue(chess.isValidMove(3, 3, 4, 4));
		assertTrue(chess.isValidMove(3, 3, 5, 1));
		assertTrue(chess.isValidMove(3, 3, 5, 5));
		assertTrue(chess.isValidMove(3, 3, 1, 5));
		assertTrue(chess.isValidMove(3, 3, 1, 1));
		assertTrue(chess.isValidMove(3, 3, 3, 2));
		assertTrue(chess.isValidMove(3, 3, 2, 3));
		assertTrue(chess.isValidMove(3, 3, 3, 4));
		assertTrue(chess.isValidMove(3, 3, 4, 3));
		assertFalse(chess.isValidMove(3, 3, 5, 2));
		assertFalse(chess.isValidMove(3, 3, 3, 3));
		assertTrue(chess.isValidMove(3, 3, 3, 4));
		assertFalse(chess.isValidMove(3, 3, 4, 1));
		assertTrue(chess.isValidMove(3, 3, 3, 1));
		assertFalse(chess.isValidMove(3, 3, 2, 1));
		assertFalse(chess.isValidMove(3, 3, 1, 2));
		assertTrue(chess.isValidMove(3, 3, 1, 3));
		assertFalse(chess.isValidMove(3, 3, 1, 4));
		assertFalse(chess.isValidMove(3, 3, 2, 5));
		assertTrue(chess.isValidMove(3, 3, 3, 5));
		assertFalse(chess.isValidMove(3, 3, 4, 5));
		assertFalse(chess.isValidMove(3, 3, 6, 3));
	}
	@Test
	public void queenBlockedMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(7,0,3,3);
		chess.forceMove(1,0,3,2);
		chess.forceMove(1,1,3,4);
		chess.forceMove(1,2,2,3);
		chess.forceMove(1,3,4,3);
		assertFalse(chess.isValidMove(3,3,3,0));
		assertFalse(chess.isValidMove(3,3,0,3));
		assertFalse(chess.isValidMove(3,3,3,7));
		assertFalse(chess.isValidMove(3,3,5,3));

	}


	//KING TESTS
	@Test
	public void kingWhiteUpMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(0,3,3,3);
		assertTrue(chess.isValidMove(3,3,2,3));
	}

	@Test
	public void kingWhiteDownMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(0,3,3,3);
		assertTrue(chess.isValidMove(3,3,4,3));
	}

	@Test
	public void kingWhiteRightMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(0,3,3,3);
		assertTrue(chess.isValidMove(3,3,3,4));
	}

	@Test
	public void kingWhiteLeftMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(0,3,3,3);
		assertTrue(chess.isValidMove(3,3,3,2));
	}

	@Test
	public void kingWhiteUpLeftMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(0,3,3,3);
		assertTrue(chess.isValidMove(3,3,2,2));
	}

	@Test
	public void kingWhiteUpRightMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(0,3,3,3);
		assertTrue(chess.isValidMove(3,3,2,4));
	}

	@Test
	public void kingWhiteDownLeftMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(0,3,3,3);
		assertTrue(chess.isValidMove(3,3,4,2));
	}

	@Test
	public void kingWhiteDownRightMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(0,3,3,3);
		assertTrue(chess.isValidMove(3,3,4,4));
	}

	@Test
	public void kingWhitSameMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(0,3,3,3);
		assertFalse(chess.isValidMove(3,3,3,3));
	}
	@Test
	public void kingBlackUpMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(7,3,3,3);
		assertTrue(chess.isValidMove(3,3,2,3));
	}

	@Test
	public void kingBlackDownMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(7,3,3,3);
		assertTrue(chess.isValidMove(3,3,4,3));
	}

	@Test
	public void kingBlackRightMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(7,3,3,3);
		assertTrue(chess.isValidMove(3,3,3,4));
	}

	@Test
	public void kingBlackLeftMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(7,3,3,3);
		assertTrue(chess.isValidMove(3,3,3,2));
	}

	@Test
	public void kingBlackUpLeftMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(7,3,3,3);
		assertTrue(chess.isValidMove(3,3,2,2));
	}

	@Test
	public void kingBlackUpRightMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(7,3,3,3);
		assertTrue(chess.isValidMove(3,3,2,4));
	}

	@Test
	public void kingBlackDownLeftMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(7,3,3,3);
		assertTrue(chess.isValidMove(3,3,4,2));
	}

	@Test
	public void kingBlackDownRightMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(7,3,3,3);
		assertTrue(chess.isValidMove(3,3,4,4));
	}

	@Test
	public void kingBlackSameMovementTest()
	{
		Board chess  = new BoardImpl();
		chess.forceMove(7,3,3,3);
		assertFalse(chess.isValidMove(3,3,3,3));
	}

	@Test
	public void kingObstacleMovementTest()
	{
		Board chess = new BoardImpl();

		assertFalse(chess.isValidMove(0, 3, 1, 3));
	}

	@Test
	public void kingEnemyMovementTest()
	{
		Board chess = new BoardImpl();
		chess.forceMove(6,0,1,3);
		//assertEquals(Piece.Type.PAWN, chess.squareInfo(1,3));
		assertTrue(chess.isValidMove(0, 3, 1, 3));
	}
}
