package Board;

import Board.Piece.Piece;

public abstract class abstractWorld implements World {
	Piece[][] board = new Piece[8][8];

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidMove(int x, int y, int r, int c) {
		// TODO Auto-generated method stub
		return false;
	}
}
