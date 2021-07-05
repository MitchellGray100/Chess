package com.robotchad.chess.client.board;

import com.robotchad.chess.client.pieces.Piece;

/** The board in a game of chess */
public interface Board {
	
	/**
	 * Moves the piece from one spot to another
	 * @param x the x coordinate of the piece
	 * @param y the y coordinate of the piece
	 * @param r The x coordinate to move to
	 * @param c The y coordinate to move to
	 * @return whether or not the move is valid
	 */
	public boolean move(int x,int y,int r,int c);
	
	
	/**
	 * Determines whether the given move is a valid move for the pieces
	 * @param x The x coordinate of the pieces
	 * @param y The y coordinate of the pieces
	 * @param r The proposed x coordinate of the move
	 * @param c The proposed y coordinate of the move
	 * @return whether or not the move is valid
	 */
	public boolean isValidMove(int x, int y, int r, int c);
	
	
	/**
	 * Changes the score of the game whenever a pieces is taken
	 * @param r The x position of the pieces being taken
	 * @param c The y position of the pieces being taken
	 */
	public void changeScore(int r, int c);
	
	/**
	 * Returns the piece at the specified square or null if the square is empty
	 * @param x - x coordinate of the square
	 * @param y - y coordinate of the square
	 * @return the piece at the specified square, null if square is empty
	 */
	public Piece squareInfo(int x, int y);
}

