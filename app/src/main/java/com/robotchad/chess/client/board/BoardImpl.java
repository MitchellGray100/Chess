package com.robotchad.chess.client.board;

import com.robotchad.chess.client.location.LocationImpl;
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
		pieces[0] = board[0][0];//Left White Rook
		pieces[0].setLocation(new LocationImpl(0,0));
		pieces[1] = board[0][1];//Left White Knight
		pieces[1].setLocation(new LocationImpl(0,1));
		pieces[2] = board[0][2];//Left White Bishop
		pieces[2].setLocation(new LocationImpl(0,2));
		pieces[3] = board[0][3];//White Queen
		pieces[3].setLocation(new LocationImpl(0,3));
		pieces[4] = board[0][4];//White King
		pieces[4].setLocation(new LocationImpl(0,4));
		pieces[5] = board[0][5];//Right White Bishop
		pieces[5].setLocation(new LocationImpl(0,5));
		pieces[6] = board[0][6];//Right White Knight
		pieces[6].setLocation(new LocationImpl(0,6));
		pieces[7] = board[0][7];//Right White Rook
		pieces[7].setLocation(new LocationImpl(0,7));
		pieces[8] = board[1][0];//Pos 0 White Pawn
		pieces[8].setLocation(new LocationImpl(1,0));
		pieces[9] = board[1][1];//Pos 1 White Pawn
		pieces[9].setLocation(new LocationImpl(1,1));
		pieces[10] = board[1][2];//Pos 2 White Pawn
		pieces[10].setLocation(new LocationImpl(1,2));
		pieces[11] = board[1][3];//Pos 3 White Pawn
		pieces[11].setLocation(new LocationImpl(1,3));
		pieces[12] = board[1][4];//Pos 4 White Pawn
		pieces[12].setLocation(new LocationImpl(1,4));
		pieces[13] = board[1][5];//Pos 5 White Pawn
		pieces[13].setLocation(new LocationImpl(1,5));
		pieces[14] = board[1][6];//Pos 6 White Pawn
		pieces[14].setLocation(new LocationImpl(1,6));
		pieces[15] = board[1][7];//Pos 7 White Pawn
		pieces[15].setLocation(new LocationImpl(1,7));
		pieces[16] = board[7][0];//Left Black Rook
		pieces[16].setLocation(new LocationImpl(7,0));
		pieces[17] = board[7][1];//Left Black Knight
		pieces[17].setLocation(new LocationImpl(7,1));
		pieces[18] = board[7][2];//Left Black Bishop
		pieces[18].setLocation(new LocationImpl(7,2));
		pieces[19] = board[7][3];//Black Queen
		pieces[19].setLocation(new LocationImpl(7,3));
		pieces[20] = board[7][4];//Black King
		pieces[20].setLocation(new LocationImpl(7,4));
		pieces[21] = board[7][5];//Right Black Bishop
		pieces[21].setLocation(new LocationImpl(7,5));
		pieces[22] = board[7][6];//Right Black Knight
		pieces[22].setLocation(new LocationImpl(7,6));
		pieces[23] = board[7][7];//Right Black Rook
		pieces[23].setLocation(new LocationImpl(7,7));
		pieces[24] = board[6][0];//Pos 0 Black Pawn
		pieces[24].setLocation(new LocationImpl(6,0));
		pieces[25] = board[6][1];//Pos 1 Black Pawn
		pieces[25].setLocation(new LocationImpl(6,1));
		pieces[26] = board[6][2];//Pos 2 Black Pawn
		pieces[26].setLocation(new LocationImpl(6,2));
		pieces[27] = board[6][3];//Pos 3 Black Pawn
		pieces[27].setLocation(new LocationImpl(6,3));
		pieces[28] = board[6][4];//Pos 4 Black Pawn
		pieces[28].setLocation(new LocationImpl(6,4));
		pieces[29] = board[6][5];//Pos 5 Black Pawn
		pieces[29].setLocation(new LocationImpl(6,5));
		pieces[30] = board[6][6];//Pos 6 Black Pawn
		pieces[30].setLocation(new LocationImpl(6,6));
		pieces[31] = board[6][7];//Pos 7 Black Pawn
		pieces[31].setLocation(new LocationImpl(6,7));
	}

}
