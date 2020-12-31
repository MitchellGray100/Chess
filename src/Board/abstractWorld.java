package Board;

import Board.Piece.Piece;

public abstract class abstractWorld implements World {
	Piece[][] board = new Piece[8][8];
	int whitePoints = 0;
	int blackPoints = 0;

	@Override
	public void move(int x, int y,int r, int c) {
		if(!(r>7) && !(r<0))
		{
			if(!(c>7) && !(c<0))
			{
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
				}
			}
		}

	}

	@Override
	public boolean isValidMove(int x, int y, int r, int c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changeScore(int r, int c)
	{
		if(whitePoints == blackPoints)
		{
			if(((Piece)(board[r][c])).getColor().equals(Piece.color.BLACK)) 
			{
				whitePoints += ((Piece)(board[r][c])).getValue();
			}
		}
		else if(((Piece)(board[r][c])).getColor().equals(Piece.color.BLACK)) 
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
		else if(((Piece)(board[r][c])).getColor().equals(Piece.color.WHITE)) 
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
}
