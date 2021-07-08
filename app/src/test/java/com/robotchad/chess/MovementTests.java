package com.robotchad.chess;

import com.robotchad.chess.client.board.Board;
import com.robotchad.chess.client.board.BoardImpl;

import org.junit.Test;

import static org.junit.Assert.*;

import junit.framework.Assert;

public class MovementTests {

	@Test
	public void pawnWhiteWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertEquals(false,chess.isValidMove(1,0,1,1));
	}

	@Test
	public void pawnWhiteCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		assertTrue(chess.isValidMove(1,0,2,0));
	}

	@Test
	public void pawnWhiteSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertEquals(false,chess.isValidMove(1,0,1,0));
	}

	@Test
	public void pawnBlackWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertEquals(false,chess.isValidMove(6,0,6,1));
	}

	@Test
	public void pawnBlackCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		assertTrue(chess.isValidMove(6,0,5,0));
	}

	@Test
	public void pawnBlackSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertEquals(false,chess.isValidMove(6,0,6,0));
	}

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
}
