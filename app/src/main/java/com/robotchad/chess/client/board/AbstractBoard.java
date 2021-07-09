package com.robotchad.chess.client.board;

import com.robotchad.chess.client.pieces.Piece;

//@TODO Add pawns moving 2 spaces if first move


/** An abstract implementation of the chess board */
public abstract class AbstractBoard implements Board {
	
	/** A 2d array of pieces representing the chess board */
	Piece[][] board = new Piece[8][8];
	/** Number of points for white 
	 * Invariant: nonnegative
	 */
	int whitePoints = 0;
	/** Number of points for black 
	 * Invariant: nonnegative
	 */
	int blackPoints = 0;

	public int getWhitePoints()
	{
		return whitePoints;
	}

	public int getBlackPoints()
	{
		return blackPoints;
	}

	@Override
	public Piece squareInfo(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7)
			return null;
		return board[x][y];
	}

	@Override
	public void deleteSquare(int x, int y) {
		board[x][y] = null;
	}

	@Override
	public void changeSquare(int x, int y, Piece piece)
	{
		board[x][y] = piece;
	}

	@Override
	public boolean move(int x, int y,int r, int c) {
		if(isValidMove(x,y,r,c))
		{
			if(board[r][c] == null)
			{
				board[r][c] = board[x][y];
				board[x][y] = null;
			}
			else
			{
				changeScore(r,c);
				board[r][c] = board[x][y];
				board[x][y] = null;
			}
			return true;
		}
		return false;
	}

	@Override
	public void forceMove(int x, int y,int r, int c) {
		if (!(r > 7 || r < 0 || c > 7 || c < 0 ||
				x > 7 || x < 0 || y > 7 || y < 0)) {
			if (board[r][c] == null) {
				board[r][c] = board[x][y];
				board[x][y] = null;
			} else {
				changeScore(r, c);
				board[r][c] = board[x][y];
				board[x][y] = null;
			}
		}
	}

	@Override
	public boolean isValidMove(int x, int y, int r, int c) {
		if (r > 7 || r < 0 || c > 7 || c < 0 ||
				x > 7 || x < 0 || y > 7 || y < 0) return false;
		if(x == r && y == c)
		{
			return false;
		}
		//if(putsKingInCheck(x,y,r,c))
		//{
		//	return false;
		//}
		try {
			Piece.Type pieceType = ((Piece)(board[x][y])).getType();
			switch(pieceType)
			{
			case PAWN:
				return pawnMove(x,y,r,c);
			case KNIGHT:
				return knightMove(x,y,r,c);
			case BISHOP:
				return bishopMove(x,y,r,c);
			case ROOK:
				return rookMove(x,y,r,c);
			case QUEEN:
				return queenMove(x,y,r,c);
			default:
				return kingMove(x,y,r,c);
			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	@Override
	public void changeScore(int r, int c)
	{
		if(whitePoints == blackPoints)
		{
			if(((Piece)(board[r][c])).getColor().equals(Piece.Color.BLACK))
			{
				whitePoints += ((Piece)(board[r][c])).getValue();
			}
			if(((Piece)(board[r][c])).getColor().equals(Piece.Color.WHITE))
			{
				blackPoints += ((Piece)(board[r][c])).getValue();
			}
		}
		else if(((Piece)(board[r][c])).getColor().equals(Piece.Color.BLACK))
		{
			if(whitePoints > blackPoints)
			{
				whitePoints += ((Piece)(board[r][c])).getValue();
			}
			else if((whitePoints < blackPoints) && (whitePoints + ((Piece)(board[r][c])).getValue() > blackPoints))
			{
				blackPoints = 0;
				whitePoints += ((Piece)(board[r][c])).getValue();
			}
			else if((whitePoints < blackPoints) && (whitePoints + ((Piece)(board[r][c])).getValue() < blackPoints))
			{
				blackPoints -= ((Piece)(board[r][c])).getValue();
			}
			else if((whitePoints < blackPoints) && (whitePoints + ((Piece)(board[r][c])).getValue() == blackPoints))
			{
				blackPoints = 0;
				whitePoints = 0;
			}
		}
		else if(((Piece)(board[r][c])).getColor().equals(Piece.Color.WHITE))
		{
			if(blackPoints > whitePoints)
			{
				blackPoints += ((Piece)(board[r][c])).getValue();
			}
			else if((blackPoints < whitePoints) && (blackPoints + ((Piece)(board[r][c])).getValue() > whitePoints))
			{
				whitePoints = 0;
				blackPoints += ((Piece)(board[r][c])).getValue();
			}
			else if((blackPoints < whitePoints) && (blackPoints + ((Piece)(board[r][c])).getValue() < whitePoints))
			{
				whitePoints -= ((Piece)(board[r][c])).getValue();
			}
			else if((blackPoints < whitePoints) && (blackPoints + ((Piece)(board[r][c])).getValue() == whitePoints))
			{
				whitePoints = 0;
				blackPoints = 0;
			}
		}

	}
	/**
	 * Checks to see if the move is valid
	 * @param x The row value of the piece
	 * @param y	The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a pawn
	 */
	public boolean pawnMove(int x, int y, int r, int c)
	{
		if((board[r][c] != null && ((Piece)(board[r][c])).getColor() != ((Piece)(board[x][y])).getColor()))
		{
			if(((Piece)(board[x][y])).getColor() == Piece.Color.WHITE)
			{
				if(r == x + 1 && (c == y + 1 || c == y - 1))
				{
					return true;
				}
			}
			if(((Piece)(board[x][y])).getColor() == Piece.Color.BLACK)
			{
				if(r == x - 1 && (c == y + 1 || c == y - 1))
				{
					return true;
				}
			}
		}
		if(board[r][c] == null)
		{
			if(((Piece)(board[x][y])).getColor() == Piece.Color.WHITE)
			{
				if(r == x + 1 && c == y)
				{
					return true;
				}
			}
			if(((Piece)(board[x][y])).getColor() == Piece.Color.BLACK)
			{
				if(r == x - 1 && c == y)
				{
					return true;
				}
			}
		}

		return false;//should be false
	}

	/**
	 * Checks to see if the move is valid
	 * @param x The row value of the piece
	 * @param y	The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a knight
	 */
	public boolean knightMove(int x, int y, int r, int c)
	{
		if(board[r][c] != null && ((Piece)(board[r][c])).getColor() != ((Piece)(board[x][y])).getColor() && (((Math.abs(x-r) == 1 && Math.abs(y-c) == 2) || Math.abs(x-r) == 2 && Math.abs(y-c) == 1)))
		{

				return true;
		}
		if((board[r][c] == null) && ((Math.abs(x-r) == 1 && Math.abs(y-c) == 2) || Math.abs(x-r) == 2 && Math.abs(y-c) == 1))
		{
				return true;
		}

		return false;
	}

	/**
	 *
	 * @param x The row value of the piece
	 * @param y	The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a bishop
	 */
	public boolean bishopMove(int x, int y, int r, int c)
	{
		if((board[r][c] != null && ((Piece)(board[r][c])).getColor() != ((Piece)(board[x][y])).getColor() && (Math.abs(y-c) == Math.abs(x-r))) || (board[r][c] == null && (Math.abs(y-c) == Math.abs(x-r))))
		{
				if(r < x && c > y)
				{
					for(int i = 1; i < x-r; i++)
					{
						if(board[x-i][y+i] != null)
						{
							return false;
						}
					}
					return true;
				}

			else if(r < x && c < y)
			{
				for(int i = 1; i < x-r; i++)
				{
					if(board[x-i][y-i] != null)
					{
						return false;
					}
				}
				return true;
			}
			else if(r > x && c < y)
			{
				for(int i = 1; i < r-x; i++)
				{
					if(board[x+i][y-i] != null)
					{
						return false;
					}
				}
				return true;
			}
			else if(r > x && c > y)
			{
				for(int i = 1; i < r-x; i++)
				{
					if(board[x+i][y+i] != null)
					{
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	/**
	 * Checks to see if the move is valid
	 * @param x The row value of the piece
	 * @param y	The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a rook
	 */
	public boolean rookMove(int x, int y, int r, int c)
	{
		if((board[r][c] != null && ((Piece)(board[r][c])).getColor() != ((Piece)(board[x][y])).getColor()) || board[r][c] == null)
		{
			if(r == x || c == y)
			{
				if(r == x && c > y)
				{
					for(int i = 1; i < c-y; i++)
					{
						if(board[x][y+i] != null)
						{
							return false;
						}
					}
					return true;
				}

				else if(r == x && c < y)
				{
					for(int i = 1; i < y-c; i++)
					{
						if(board[x][y-i] != null)
						{
							return false;
						}
					}
					return true;
				}
				else if(r > x && c == y)
				{
					for(int i = 1; i < r-x; i++)
					{
						if(board[x+i][y] != null)
						{
							return false;
						}
					}
					return true;
				}
				else if(r < x && c == y)
				{
					for(int i = 1; i < x-r; i++)
					{
						if(board[x-i][y] != null)
						{
							return false;
						}
					}
					return true;
				}
			}
		}

		return false;
	}
	/**
	 * Checks to see if the move is valid
	 * @param x The row value of the piece
	 * @param y	The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a queen
	 */
	public boolean queenMove(int x, int y, int r, int c)
	{
		if(bishopMove(x,y,r,c) || rookMove(x,y,r,c))
		{
			return true;
		}
		return false;
	}
	/**
	 * Checks to see if the move is valid
	 * @param x The row value of the piece
	 * @param y	The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a queen
	 */
	public boolean kingMove(int x, int y, int r, int c)
	{
		if(board[r][c] != null && ((Piece)(board[r][c])).getColor() != ((Piece)(board[x][y])).getColor())
		{
			if(Math.abs(r - x) == 1 && Math.abs(c - y) == 1)
			{
				return true;
			}
			else if(r == x && Math.abs(c-y) == 1)
			{
				return true;
			}
			else if(c == y && Math.abs(r-x) == 1)
			{
				return true;
			}
		}
		if(board[r][c] == null)
		{
			if(Math.abs(r - x) == 1 && Math.abs(c - y) == 1)
			{
				return true;
			}
			else if(r == x && Math.abs(c-y) == 1)
			{
				return true;
			}
			else if(c == y && Math.abs(r-x) == 1)
			{
				return true;
			}
		}
		return false;//false
	}

	/**
	 * Checks to see if the move is valid
	 * @param x The row value of the piece
	 * @param y	The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move puts the king in check
	 */
	public boolean putsKingInCheck(int x, int y, int r, int c) {
		Piece.Color pieceColor = ((board[x][y])).getColor();
		Piece temp1 = board[x][y];
		Piece temp2 = board[r][c];
		board[r][c] = board[x][y];
		board[x][y] = null;
		boolean returner = false;
		if(pieceColor == Piece.Color.WHITE)
		{
			for(int row = 0; row < board.length; row++)
			{
				for(int column = 0; column < board[0].length; column++)
				{
					if(board[row][column] != null && board[row][column].getType() == Piece.Type.KING && board[row][column].getColor() == Piece.Color.WHITE)
					{
						returner =  isCheck(row,column);
						board[x][y] = temp1;
						board[r][c] = temp2;
					}
				}
			}
		}
		else
		{
			for(int row = board.length - 1; row >= 0 ; row--)
			{
				for(int column = board[0].length - 1; column >= 0; column--)
				{
					if(board[row][column] != null && board[row][column].getType() == Piece.Type.KING && board[row][column].getColor() == Piece.Color.BLACK)
					{
						returner = isCheck(row,column);
						board[x][y] = temp1;
						board[r][c] = temp2;
					}
				}
			}
		}
		return returner;
	}
	/**
	 * Checks whether or not the king is in check.
	 * @return whether or not the king at the specified location is in check
	 */
	public boolean isCheck(int x, int y)
	{
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[0].length; c++)
			{
				if(board[r][c] != null && board[r][c].getColor() != board[x][y].getColor())
				{
					if(isValidMove(r,c,x,y))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
}
