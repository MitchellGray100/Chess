package board;

import pieces.Piece;

public abstract class AbstractBoard implements Board {
	Piece[][] board = new Piece[8][8];
	int whitePoints = 0;
	int blackPoints = 0;

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
	public boolean isValidMove(int x, int y, int r, int c) {
		if (r > 7 || r < 0 || c > 7 || c < 0 || 
				x > 7 || x < 0 || y > 7 || y < 0) return false;
		try {
			Piece.Type pieceType = ((Piece)(board[r][c])).getType();
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
	public boolean pawnMove(int x, int y, int r, int c)
	{
		if(board[r][c] != null && ((Piece)(board[r][c])).getColor() != ((Piece)(board[x][y])).getColor())
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
		return false;
	}
	public boolean knightMove(int x, int y, int r, int c)
	{
		return false;
	}
	public boolean bishopMove(int x, int y, int r, int c)
	{
		return false;
	}
	public boolean rookMove(int x, int y, int r, int c)
	{
		return false;
	}
	public boolean queenMove(int x, int y, int r, int c)
	{
		return false;
	}
	public boolean kingMove(int x, int y, int r, int c)
	{
		return false;
	}
}
