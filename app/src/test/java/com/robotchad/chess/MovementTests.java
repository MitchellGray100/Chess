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

}
