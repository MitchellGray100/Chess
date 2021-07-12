package com.robotchad.chess.client.board;

import com.robotchad.chess.client.location.LocationImpl;
import com.robotchad.chess.client.pieces.Piece;

/** The board in a game of chess */
public interface Board {
	
	/**
	 * Moves the piece from one spot to another if valid
	 * @param x the x coordinate of the piece
	 * @param y the y coordinate of the piece
	 * @param r The x coordinate to move to
	 * @param c The y coordinate to move to
	 * @return whether or not the move is valid
	 */
	boolean move(int x,int y,int r,int c);

	/**
	 * Moves the piece from one spot to another even if not valid. Only used for testing.
	 * @param x the x coordinate of the piece
	 * @param y the y coordinate of the piece
	 * @param r The x coordinate to move to
	 * @param c The y coordinate to move to
	 */
	void forceMove(int x,int y,int r,int c);

	/**
	 * Determines whether the given move is a valid move for the pieces
	 * @param x The x coordinate of the pieces
	 * @param y The y coordinate of the pieces
	 * @param r The proposed x coordinate of the move
	 * @param c The proposed y coordinate of the move
	 * @return object array that at [0] is boolean. True for valid moves and False for invalid moves.
	 * [1] is a Location that is -100,-100 if no en passent and a specified location if it is an en passent.
	 * Location should be passed to move as passX and passY.
	 */
	LocationImpl isValidMove(int x, int y, int r, int c);

	boolean isValidMoveConverter(LocationImpl input);
	
	/**
	 * Changes the score of the game whenever a pieces is taken
	 * @param r The x position of the pieces being taken
	 * @param c The y position of the pieces being taken
	 */
	void changeScore(int r, int c);
	
	/**
	 * Returns the piece at the specified square or null if the square is empty
	 * @param x - x coordinate of the square
	 * @param y - y coordinate of the square
	 * @return the piece at the specified square, null if square is empty
	 */
	Piece squareInfo(int x, int y);

	/**
	 * Deletes piece on board at specified x and y coordinates
	 * @param x - x coordinate of the square
	 * @param y - y coordinate of the square
	 */
	void deleteSquare(int x, int y);

	/**
	 * Deletes piece on board at specified x and y coordinates
	 * @param x - x coordinate of the square
	 * @param y - y coordinate of the square
	 */
	 void changeSquare(int x, int y,Piece piece);

	boolean isCheck(int x, int y);

	boolean putsKingInCheck(int x, int y, int r, int c);

	int getWhitePoints();

	int getBlackPoints();
}

