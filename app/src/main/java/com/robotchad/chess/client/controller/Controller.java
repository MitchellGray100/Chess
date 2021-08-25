package com.robotchad.chess.client.controller;

import com.robotchad.chess.client.location.LocationImpl;
import com.robotchad.chess.client.pieces.Piece;

import java.util.ArrayList;

/** The controller of the chess game */
public interface Controller {

    /**
     * Prints a visualization of the board.
     * Black squares and White squares are shown respectively.
     * Kings are X
     * Knights are K
     * Queens are Q
     * Pawns are P
     * Bishops are B
     * Rooks are R
     */
    void printBoard();

    /**
     * Move the piece and if not return false.
     * @param x X cord of the piece to move
     * @param y Y cord of the piece to move
     * @param r X cord of the spot to move to
     * @param c Y cord of the spot to move to
     * @return whether or not the move was successful
     */
    boolean move(int x, int y, int r, int c);

    /**
     * Prompts the user for the x,y,r,c values for move() and then runs move(x,y,r,c).
     */
    void userMove();

    /**
     * Checks to see if the player is in stalemate
     * @param color the color of the player
     * @return whether or not the player is in stalemate
     */
    boolean isStalemate(Piece.Color color);

    /**
     * Checks to see if the player is in checkmate
     * @param color the color of the player
     * @return whether or not the player is in checkmate
     */
    boolean isCheckmate(Piece.Color color);

    /**
     * Makes a move for the ai
     * @param color the color of the ai
     */
    void aiMove(Piece.Color color);

    /**
     * Returns the score of the specified player color
     * @param color color of the player to get score
     * @return the score of the specified player
     */
    int getScore(Piece.Color color);

    /**
     * Returns the piece at the specified square or null if the square is empty
     *
     * @param x - x coordinate of the square
     * @param y - y coordinate of the square
     * @return the piece at the specified square, null if square is empty
     */
    Piece squareInfo(int x, int y);

    /**
     * Precondition: x and y making a valid location of a piece, not an empty square.
     * validMoveList returns an arraylist of all valid moves of the indicated pieces as LocationImpl's
     * @param x The x coordinate of the piece
     * @param y The y coordinate of the piece
     * @return An ArrayList of all valid moves of a piece.
     */
    ArrayList<LocationImpl> validMoveList(int x, int y);

    LocationImpl isValidMove(int x, int y, int r, int c);

}
