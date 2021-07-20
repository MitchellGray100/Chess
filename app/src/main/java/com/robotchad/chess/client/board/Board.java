package com.robotchad.chess.client.board;

import com.robotchad.chess.client.location.LocationImpl;
import com.robotchad.chess.client.pieces.Piece;

/** The board in a game of chess */
public interface Board {

	/**
	 * Chooses the best ai move based on how much score the ai gains from the move.
	 * @param color The color of the ai playing.
	 * @return locationImpl array. [0] holds x and y, [1] holds r and c.
	 */
	LocationImpl[] aiMove(Piece.Color color);

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
	 * Moves the piece from one spot to another even if not valid and doesnt change score. Only used for testing.
	 * @param x the x coordinate of the piece
	 * @param y the y coordinate of the piece
	 * @param r The x coordinate to move to
	 * @param c The y coordinate to move to
	 */
	void forceMoveWithoutScore(int x,int y,int r,int c);

	/**
	 * Determines whether the given move is a valid move for the pieces
	 * @param x The x coordinate of the pieces
	 * @param y The y coordinate of the pieces
	 * @param r The proposed x coordinate of the move
	 * @param c The proposed y coordinate of the move
	 * @return a locationImpl. The output can be converted to boolean with isValidMoveConverter().
	 * 100,100 means true; -100,-100 means false; x,y (where x and y are coordinates of the chess
	 * board) means there is an en passant and (x,y) is the location of the pawn.
	 */
	LocationImpl isValidMove(int x, int y, int r, int c);


	public Piece piecesGetter(int num);

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

	LocationImpl isCheck(int x, int y);

	boolean putsKingInCheck(int x, int y, int r, int c);

	/**
	 * Checks if a king can move itself out of check.
	 * @param color The color of the king to check is checkmated
	 * @return whether or not the king is checkmated
	 */
	boolean isCheckmateKingMove(Piece.Color color);

	/**
	 * Checks if a piece can block the check
	 * @param x The x cord of the piece putting the king in check
	 * @param y The y cord of the piece putting the king in check
	 * @return whether or not the king is checkmated
	 */
	boolean isCheckmatePieceMove(int x, int y);

	/**
	 * Checks if a piece can block the check
	 * @param x The x cord of the piece putting the king in check
	 * @param y The y cord of the piece putting the king in check
	 * @return whether or not the king is checkmated
	 */
	boolean isCheckmatePieceAttack(int x, int y);

	int getWhitePoints();

	int getBlackPoints();
}

