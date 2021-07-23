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
	 * @return a locationImpl. The output can be converted to boolean with locationToBoolean().
	 * 100,100 means true; -100,-100 means false; x,y (where x and y are coordinates of the chess
	 * board) means there is an en passant and (x,y) is the location of the pawn. Kings trying to
	 * castle will get 460, 420, 467,427 if successful.
	 * 460 -> white king king-side castling
	 * 420 -> white king queen-side castling
	 * 467 -> black king king-side castling
	 * 427 -> black king queen-side castling
	 */
	LocationImpl isValidMove(int x, int y, int r, int c);


	/**
	 * Determines whether the given move is a valid move for the pieces
	 * @param x The x coordinate of the pieces
	 * @param y The y coordinate of the pieces
	 * @param r The proposed x coordinate of the move
	 * @param c The proposed y coordinate of the move
	 * @return a locationImpl. The output can be converted to boolean with locationToBoolean().
	 * 100,100 means true; -100,-100 means false;
	 */
	LocationImpl isValidProtect(int x, int y, int r, int c);


	/**
	 * Returns the specified piece at the index in the pieces array
	 * @param num The index of the pieces array to grab
	 * @return The piece in the pieces array at index num
	 */
	public Piece piecesGetter(int num);

	/**
	 * Checks to see if the location is not -100,-100. If it is -100 -100 then false, otherwise true;
	 * @param input Location to be converted to boolean
	 * @return whether or not the location is valid or not.
	 */
	boolean locationToBoolean(LocationImpl input);

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

	/**
	 * Checks if a piece if being checked (made for king but works for any piece).
	 * @param x The x cord of piece
	 * @param y The y cord of piece
	 * @return the location of the piece checking the (x,y) piece.
	 */
	LocationImpl isCheck(int x, int y);

	/**
	 * Checks if a piece if being protected (made for king but works for any piece).
	 * @param x The x cord of piece
	 * @param y The y cord of piece
	 * @return the location of the piece checking the (x,y) piece.
	 */
	LocationImpl isProtect(int x, int y);

	/**
	 * Checks to see if a player's piece puts its own king in check
	 * @param x The x cord of the piece to move.
	 * @param y the y cord of the piece to move.
	 * @param r The proposed x cord of the move.
	 * @param c The proposed y cord of the move.
	 * @return Whether or not the player's king would be checked from their own move.
	 */
	boolean putsKingInCheck(int x, int y, int r, int c);

	/**
	 * Checks if a king can move itself out of check.
	 * @param color The color of the king to check is checkmated
	 * @return whether or not the king can move away from the checker
	 */
	boolean isCheckmateKingMove(Piece.Color color);

	/**
	 * Checks if a piece can block the check
	 * @param x The x cord of the piece putting the king in check
	 * @param y The y cord of the piece putting the king in check
	 * @return whether or not the king's pieces can block the attack
	 */
	boolean isCheckmatePieceMove(int x, int y);

	/**
	 * Checks if a piece can attack the checker
	 * @param x The x cord of the piece putting the king in check
	 * @param y The y cord of the piece putting the king in check
	 * @return whether or not the king's piece can attack the checker
	 */
	boolean isCheckmatePieceAttack(int x, int y);

	/**
	 * Checks if the king is checkmated.
	 * @param x The x cord of the piece putting the king in check
	 * @param y The y cord of the piece putting the king in check
	 * @return whether or not the king is checkmated.
	 */
	boolean isCheckmate(int x, int y);

	/**
	 * Checks to see if the specified color player is in stalemate.
	 * @param color The color to check for stalemate.
	 * @return Whether or not the player is in stalemate.
	 */
	boolean isStalemate(Piece.Color color);

	/**
	 * Returns the board's white points
	 * @return the board's white points
	 */
	int getWhitePoints();

	/**
	 * Returns the board's black points
	 * @return the board's black points
	 */
	int getBlackPoints();


}

