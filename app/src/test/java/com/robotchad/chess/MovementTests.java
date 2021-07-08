package com.robotchad.chess;

import com.robotchad.chess.client.board.Board;
import com.robotchad.chess.client.board.BoardImpl;

import org.junit.Test;

import static org.junit.Assert.*;

import junit.framework.Assert;

public class MovementTests {

	@Test
	public void pawnWrongMovementTest()
	{
		Board chess = new BoardImpl();
		assertEquals(false,chess.isValidMove(1,0,1,1));
	}

	@Test
	public void pawnCorrectMovementTest()
	{
		Board chess = new BoardImpl();
		assertTrue(chess.isValidMove(1,0,2,0));
	}

	@Test
	public void pawnSameMovementTest()
	{
		Board chess = new BoardImpl();
		assertEquals(false,chess.isValidMove(1,0,1,0));
	}

}
