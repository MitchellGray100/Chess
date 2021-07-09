package com.robotchad.chess.client.board;

import com.robotchad.chess.client.pieces.Piece.Color;
import com.robotchad.chess.client.pieces.Piece.Type;
import com.robotchad.chess.client.pieces.PieceFactory;

/** Implementation of the chess board */
public class BoardImpl extends AbstractBoard implements Board {

	/** Factory for creating pieces on the board */
	PieceFactory factory = new PieceFactory();
	
	/**
	 * Creates an instance of a chess board 
	 */
	public BoardImpl() { 
		board[0][0] = factory.getPiece(Type.ROOK, Color.WHITE);
		board[0][1] = factory.getPiece(Type.KNIGHT, Color.WHITE);
		board[0][2] = factory.getPiece(Type.BISHOP, Color.WHITE);
		board[0][3] = factory.getPiece(Type.QUEEN, Color.WHITE);
		board[0][4] = factory.getPiece(Type.KING, Color.WHITE);
		board[0][5] = factory.getPiece(Type.BISHOP, Color.WHITE);
		board[0][6] = factory.getPiece(Type.KNIGHT, Color.WHITE);
		board[0][7] = factory.getPiece(Type.ROOK, Color.WHITE);
		for (int i = 0; i < board[1].length; i++)
			board[1][i] = factory.getPiece(Type.PAWN, Color.WHITE);
		board[7][0] = factory.getPiece(Type.ROOK, Color.BLACK);
		board[7][1] = factory.getPiece(Type.KNIGHT, Color.BLACK);
		board[7][2] = factory.getPiece(Type.BISHOP, Color.BLACK);
		board[7][3] = factory.getPiece(Type.QUEEN, Color.BLACK);
		board[7][4] = factory.getPiece(Type.KING, Color.BLACK);
		board[7][5] = factory.getPiece(Type.BISHOP, Color.BLACK);
		board[7][6] = factory.getPiece(Type.KNIGHT, Color.BLACK);
		board[7][7] = factory.getPiece(Type.ROOK, Color.BLACK);
		for (int i = 0; i < board[6].length; i++)
			board[6][i] = factory.getPiece(Type.PAWN, Color.BLACK);
		
	}
}
