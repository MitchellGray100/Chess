package com.robotchad.chess.client.controller;

import com.robotchad.chess.client.pieces.Piece;

/** The controller of the chess game */
public interface Controller {

    void printBoard();

    boolean move(int x, int y, int r, int c);

    void userMove();

    boolean isStalemate(Piece.Color color);

    boolean isCheckmate(Piece.Color color);

    void aiMove(Piece.Color color);

    int getScore(Piece.Color color);
	
}
