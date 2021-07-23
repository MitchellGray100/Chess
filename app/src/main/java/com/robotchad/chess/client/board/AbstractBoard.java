package com.robotchad.chess.client.board;

import android.hardware.camera2.params.BlackLevelPattern;

import com.robotchad.chess.client.location.LocationImpl;
import com.robotchad.chess.client.pieces.AbstractPiece;
import com.robotchad.chess.client.pieces.Pawn;
import com.robotchad.chess.client.pieces.Piece;

import java.util.ArrayList;

//@TODO AI and Castleing


/** An abstract implementation of the chess board */
public abstract class AbstractBoard implements Board {

	/**
	 * A 2d array of pieces representing the chess board
	 */
	Piece[][] board = new Piece[8][8];
	/**
	 * Number of points for white
	 * Invariant: nonnegative
	 */
	int whitePoints = 0;
	/**
	 * Number of points for black
	 * Invariant: nonnegative
	 */
	int blackPoints = 0;

	/**
	 * How many turns have been taken in the game. Each move is a turn.
	 */
	int turn = 0;

	/**
	 * All pieces of the game in an array.
	 */
	Piece[] pieces = new Piece[32];

	/**
	 *Getter for the pieces array
	 * @param num The pos in the pieces array needed
	 * @return The piece at the pieces[num]
	 */
	public Piece piecesGetter(int num)
	{
		return pieces[num];
	}

	public int getWhitePoints() {
		return whitePoints;
	}

	public int getBlackPoints() {
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
	public void changeSquare(int x, int y, Piece piece) {
		board[x][y] = piece;
	}


	@Override
	public LocationImpl[] aiMove(Piece.Color color)
	{
		int maxScore = 0;
		LocationImpl maxLocation;
		LocationImpl pieceLocation;
		LocationImpl[] returner = new LocationImpl[2];

		if(color == Piece.Color.WHITE)
		{
			for(int i = 0; i < 16; i++)
			{
				for(int r = 0; r < 8; r++)
				{
					for(int c = 0; c < 8; c++)
					{
						if(pieces[i] != null && locationToBoolean(isValidMove(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis(),r,c)) && !putsKingInCheck(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis(),r,c))
						{
							if(board[r][c] == null)
							{
								if(maxScore == 0)
								{
									maxScore = -1;
									maxLocation = new LocationImpl(r,c);
									pieceLocation = new LocationImpl(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis());
								}
							}
							else
							{
								if(putsOppositeKingInCheck(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis(),r,c))
								{
									maxScore = 100;
									maxLocation = new LocationImpl(r,c);
									pieceLocation = new LocationImpl(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis());
								}
								else if(board[r][c].getValue() > maxScore)
								{
									maxScore = board[r][c].getValue();
									maxLocation = new LocationImpl(r,c);
									pieceLocation = new LocationImpl(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis());
								}
							}
						}
					}
				}
			}
		}
		return returner;
	}
								 @Override
	public boolean move(int x, int y, int r, int c) {
		LocationImpl location = isValidMove(x, y, r, c);
		if (locationToBoolean(location) && !putsKingInCheck(x, y, r, c)) {
			if (board[r][c] != null) {
				changeScore(r, c);
			}
			if(Math.abs(location.getXAxis()) != 100 && Math.abs(location.getYAxis()) != 100 &&
					location.getXAxis() != 460 && location.getXAxis() != 420 &&
					location.getXAxis() != 427 && location.getXAxis() != 467)
			{
				changeScore(location.getXAxis(),location.getYAxis());
				board[location.getXAxis()][location.getYAxis()] = null;
			}

			if(location.getXAxis() == 460 || location.getXAxis() == 420 ||
					location.getXAxis() == 427 || location.getXAxis() == 467)
			{
				if(location.getXAxis() == 460)
				{
					board[0][6] = board[0][4];
					board[0][4] = null;
					pieces[4].setLocation(new LocationImpl(0,6));
					board[0][5] = board[0][7];
					pieces[7].setLocation(new LocationImpl(0,5));
					board[0][7] = null;
					board[0][6].setMoved(true);
					board[0][5].setMoved(true);
				}
				else if(location.getXAxis() == 420)
				{
					board[0][2] = board[0][4];
					board[0][4] = null;
					pieces[4].setLocation(new LocationImpl(0,2));
					board[0][3] = board[0][0];
					pieces[0].setLocation(new LocationImpl(0,3));
					board[0][0] = null;
					board[0][2].setMoved(true);
					board[0][3].setMoved(true);
				}
				else if(location.getXAxis() == 427)
				{
					board[7][2] = board[7][4];
					board[7][4] = null;
					pieces[20].setLocation(new LocationImpl(7,2));
					board[7][3] = board[7][0];
					pieces[16].setLocation(new LocationImpl(7,3));
					board[7][0] = null;
					board[7][2].setMoved(true);
					board[7][3].setMoved(true);
				}
				else if(location.getXAxis() == 467)
				{
					board[7][6] = board[7][4];
					board[7][4] = null;
					pieces[20].setLocation(new LocationImpl(7,6));
					board[7][5] = board[7][7];
					pieces[23].setLocation(new LocationImpl(7,5));
					board[7][7] = null;
					board[7][6].setMoved(true);
					board[7][5].setMoved(true);
				}
			}
			else {
				if (board[x][y].getType() == Piece.Type.PAWN) {
					((Pawn) board[x][y]).setPrevLocation(new LocationImpl(x, y));
					((Pawn) board[x][y]).setTurnMoved(turn);
				}


				board[x][y].setMoved(true);
				board[x][y].setLocation(new LocationImpl(r, c));
				board[r][c] = board[x][y];
				board[x][y] = null;

			}
			turn++;
			return true;
		}
		return false;
	}

	@Override
	public void forceMove(int x, int y, int r, int c) {
		if (!(r > 7 || r < 0 || c > 7 || c < 0 ||
				x > 7 || x < 0 || y > 7 || y < 0)) {
			Piece holder;
			board[x][y].setLocation(new LocationImpl(r,c));
			holder = board[x][y];
			board[x][y].setMoved(true);
			if (board[r][c] != null) {
				changeScore(r, c);
			}

			board[x][y] = null;
			board[r][c] = holder;
		}
	}

	@Override
	public void forceMoveWithoutScore(int x, int y, int r, int c) {
		if (!(r > 7 || r < 0 || c > 7 || c < 0 ||
				x > 7 || x < 0 || y > 7 || y < 0)) {
			Piece holder;
			board[x][y].setLocation(new LocationImpl(r,c));
			holder = board[x][y];

			board[x][y] = null;
			board[r][c] = holder;
		}
	}

	@Override
	public LocationImpl isValidMove(int x, int y, int r, int c) {
		if (r > 7 || r < 0 || c > 7 || c < 0 ||
				x > 7 || x < 0 || y > 7 || y < 0) return (new LocationImpl(-100,-100));
		if (x == r && y == c) {
			return (new LocationImpl(-100,-100));
		}

		try {
			if ((board[r][c] != null && ((Piece) (board[r][c])).getColor() == ((Piece) (board[x][y])).getColor()))
			{
				return (new LocationImpl(-100,-100));
			}
			Piece.Type pieceType = ((Piece) (board[x][y])).getType();
			switch (pieceType) {
				case PAWN:
					return pawnMove(x, y, r, c);
				case KNIGHT:
					return knightMove(x, y, r, c);
				case BISHOP:
					return bishopMove(x, y, r, c);
				case ROOK:
					return rookMove(x, y, r, c);
				case QUEEN:
					return queenMove(x, y, r, c);
				default:
					return kingMove(x, y, r, c);
			}
		} catch (NullPointerException e) {
			return (new LocationImpl(-100,-100));
		}
	}

	@Override
	public LocationImpl isValidProtect(int x, int y, int r, int c) {
		if (r > 7 || r < 0 || c > 7 || c < 0 ||
				x > 7 || x < 0 || y > 7 || y < 0) return (new LocationImpl(-100,-100));
		if (x == r && y == c) {
			return (new LocationImpl(-100,-100));
		}

		try {
			if ((board[r][c] != null && ((Piece) (board[r][c])).getColor() != ((Piece) (board[x][y])).getColor()))
			{
				return (new LocationImpl(-100,-100));
			}
			Piece.Type pieceType = ((Piece) (board[x][y])).getType();
			switch (pieceType) {
				case PAWN:
					return pawnMove(x, y, r, c);
				case KNIGHT:
					return knightMove(x, y, r, c);
				case BISHOP:
					return bishopMove(x, y, r, c);
				case ROOK:
					return rookMove(x, y, r, c);
				case QUEEN:
					return queenMove(x, y, r, c);
				default:
					return kingMove(x, y, r, c);
			}
		} catch (NullPointerException e) {
			return (new LocationImpl(-100,-100));
		}
	}

	/**
	 * Converts the output of isValidMove to boolean
	 * @param input Location
	 * @return whether or not the move was valid or not. All negative locations are invalid and all postiive locations are positive
	 */
	public boolean locationToBoolean(LocationImpl input)
	{
		if(input.getXAxis() >= 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public void changeScore(int r, int c) {
		if (whitePoints == blackPoints) {
			if (((Piece) (board[r][c])).getColor().equals(Piece.Color.BLACK)) {
				whitePoints += ((Piece) (board[r][c])).getValue();
			}
			if (((Piece) (board[r][c])).getColor().equals(Piece.Color.WHITE)) {
				blackPoints += ((Piece) (board[r][c])).getValue();
			}
		} else if (((Piece) (board[r][c])).getColor().equals(Piece.Color.BLACK)) {
			if (whitePoints > blackPoints) {
				whitePoints += ((Piece) (board[r][c])).getValue();
			} else if ((whitePoints < blackPoints) && (whitePoints + ((Piece) (board[r][c])).getValue() > blackPoints)) {
				blackPoints = 0;
				whitePoints += ((Piece) (board[r][c])).getValue();
			} else if ((whitePoints < blackPoints) && (whitePoints + ((Piece) (board[r][c])).getValue() < blackPoints)) {
				blackPoints -= ((Piece) (board[r][c])).getValue();
			} else if ((whitePoints < blackPoints) && (whitePoints + ((Piece) (board[r][c])).getValue() == blackPoints)) {
				blackPoints = 0;
				whitePoints = 0;
			}
		} else if (((Piece) (board[r][c])).getColor().equals(Piece.Color.WHITE)) {
			if (blackPoints > whitePoints) {
				blackPoints += ((Piece) (board[r][c])).getValue();
			} else if ((blackPoints < whitePoints) && (blackPoints + ((Piece) (board[r][c])).getValue() > whitePoints)) {
				whitePoints = 0;
				blackPoints += ((Piece) (board[r][c])).getValue();
			} else if ((blackPoints < whitePoints) && (blackPoints + ((Piece) (board[r][c])).getValue() < whitePoints)) {
				whitePoints -= ((Piece) (board[r][c])).getValue();
			} else if ((blackPoints < whitePoints) && (blackPoints + ((Piece) (board[r][c])).getValue() == whitePoints)) {
				whitePoints = 0;
				blackPoints = 0;
			}
		}

	}

	/**
	 * Checks to see if the move is valid
	 *
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a pawn
	 */
	public LocationImpl pawnMove(int x, int y, int r, int c) {
		if (board[r][c] != null) {
			if (((Piece) (board[x][y])).getColor() == Piece.Color.WHITE) {
				if (r == x + 1 && (c == y + 1 || c == y - 1)) {
					return (new LocationImpl(100,100));
				}
			}
			if (((Piece) (board[x][y])).getColor() == Piece.Color.BLACK) {
				if (r == x - 1 && (c == y + 1 || c == y - 1)) {
					return (new LocationImpl(100,100));
				}
			}
		}
		else {
			if (((Piece) (board[x][y])).getColor() == Piece.Color.WHITE) {
				if (((r == x + 1) || (x == 1 && c == y && r == x + 2)) && c == y) {
					return (new LocationImpl(100,100));
				}
				if(((r == x + 1) && (c == y+ 1 || c == y-1))) {
					if(board[r-1][c] != null && board[r-1][c].getType() == Piece.Type.PAWN &&
							((Pawn)board[r-1][c]).getTurnMoved() == turn - 1 &&
							((Pawn)board[r-1][c]).getPrevLocation().getXAxis() == r + 1)
					{
						return (new LocationImpl(r-1,c));
					}
				}
			}
			if (((Piece) (board[x][y])).getColor() == Piece.Color.BLACK) {
				if (((r == x - 1) || (x == 6 && c == y && r == x - 2)) && c == y) {
					return (new LocationImpl(100,100));
				}
				if(((r == x - 1) && (c == y - 1 || c == y + 1))) {
					if(board[r+1][c] != null && board[r+1][c].getType() == Piece.Type.PAWN &&
							((Pawn)board[r+1][c]).getTurnMoved() == turn - 1 &&
							((Pawn)board[r+1][c]).getPrevLocation().getXAxis() == r - 1)
					{
						return (new LocationImpl(r+1,c));
					}
				}
			}
		}

		return (new LocationImpl(-100,-100));//should be false
	}

	/**
	 * Checks to see if the move is valid
	 *
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a knight
	 */
	public LocationImpl knightMove(int x, int y, int r, int c) {
		if (board[r][c] != null && (((Math.abs(x - r) == 1 && Math.abs(y - c) == 2) || Math.abs(x - r) == 2 && Math.abs(y - c) == 1))) {

			return (new LocationImpl(100,100));
		}
		if ((board[r][c] == null) && ((Math.abs(x - r) == 1 && Math.abs(y - c) == 2) || Math.abs(x - r) == 2 && Math.abs(y - c) == 1)) {
			return (new LocationImpl(100,100));
		}

		return (new LocationImpl(-100,-100));
	}

	/**
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a bishop
	 */
	public LocationImpl bishopMove(int x, int y, int r, int c) {
		if ((board[r][c] != null && (Math.abs(y - c) == Math.abs(x - r))) || (board[r][c] == null && (Math.abs(y - c) == Math.abs(x - r)))) {
			if (r < x && c > y) {
				for (int i = 1; i < x - r; i++) {
					if (board[x - i][y + i] != null) {
						return (new LocationImpl(-100,-100));
					}
				}
				return (new LocationImpl(100,100));
			} else if (r < x && c < y) {
				for (int i = 1; i < x - r; i++) {
					if (board[x - i][y - i] != null) {
						return (new LocationImpl(-100,-100));
					}
				}
				return (new LocationImpl(100,100));
			} else if (r > x && c < y) {
				for (int i = 1; i < r - x; i++) {
					if (board[x + i][y - i] != null) {
						return (new LocationImpl(-100,-100));
					}
				}
				return (new LocationImpl(100,100));
			} else if (r > x && c > y) {
				for (int i = 1; i < r - x; i++) {
					if (board[x + i][y + i] != null) {
						return (new LocationImpl(-100,-100));
					}
				}
				return (new LocationImpl(100,100));
			}
		}
		return (new LocationImpl(-100,-100));
	}

	/**
	 * Checks to see if the move is valid
	 *
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a rook
	 */
	public LocationImpl rookMove(int x, int y, int r, int c) {
		if (board[r][c] != null || board[r][c] == null) {
			if (r == x || c == y) {
				if (r == x && c > y) {
					for (int i = 1; i < c - y; i++) {
						if (board[x][y + i] != null) {
							return (new LocationImpl(-100,-100));
						}
					}
					return (new LocationImpl(100,100));
				} else if (r == x && c < y) {
					for (int i = 1; i < y - c; i++) {
						if (board[x][y - i] != null) {
							return (new LocationImpl(-100,-100));
						}
					}
					return (new LocationImpl(100,100));
				} else if (r > x && c == y) {
					for (int i = 1; i < r - x; i++) {
						if (board[x + i][y] != null) {
							return (new LocationImpl(-100,-100));
						}
					}
					return (new LocationImpl(100,100));
				} else if (r < x && c == y) {
					for (int i = 1; i < x - r; i++) {
						if (board[x - i][y] != null) {
							return (new LocationImpl(-100,-100));
						}
					}
					return (new LocationImpl(100,100));
				}
			}
		}

		return (new LocationImpl(-100,-100));
	}

	/**
	 * Checks to see if the move is valid
	 *
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a queen
	 */
	public LocationImpl queenMove(int x, int y, int r, int c) {
		if (bishopMove(x, y, r, c).getXAxis() == 100 || rookMove(x, y, r, c).getXAxis() == 100) {
			return (new LocationImpl(100,100));
		}
		return (new LocationImpl(-100,-100));
	}

	/**
	 * Checks to see if the move is valid
	 *
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a queen
	 */
	public LocationImpl kingMove(int x, int y, int r, int c) {
		if (board[r][c] != null) {
			if (Math.abs(r - x) == 1 && Math.abs(c - y) == 1) {
				return (new LocationImpl(100,100));
			} else if (r == x && Math.abs(c - y) == 1) {
				return (new LocationImpl(100,100));
			} else if (c == y && Math.abs(r - x) == 1) {
				return (new LocationImpl(100,100));
			}
		}
		else {
			if (Math.abs(r - x) == 1 && Math.abs(c - y) == 1) {
				return (new LocationImpl(100,100));
			} else if (r == x && Math.abs(c - y) == 1) {
				return (new LocationImpl(100,100));
			} else if (c == y && Math.abs(r - x) == 1) {
				return (new LocationImpl(100,100));
			}
			else if(x == 0 && y == 4 && !board[x][y].isMoved() && r == 0 && c == 6 &&
					board[0][7] != null && !board[0][7].isMoved() && board[0][5] == null &&
					!putsKingInCheck(0,4,0,5))
			{
				return (new LocationImpl(460,460));
			}
			else if(x == 0 && y == 4 && !board[x][y].isMoved() && r == 0 && c == 2 &&
					board[0][0] != null && !board[0][0].isMoved() && board[0][3] == null &&
					!putsKingInCheck(0,4,0,3))
			{
				return (new LocationImpl(420,420));
			}
			else if(x == 7 && y == 4 && !board[x][y].isMoved() && r == 7 && c == 2 &&
					board[7][0] != null && !board[7][0].isMoved() && board[7][3] == null &&
					!putsKingInCheck(7,4,7,3))
			{
				return (new LocationImpl(427,427));
			}
			else if(x == 7 && y == 4 && !board[x][y].isMoved() && r == 7 && c == 6 &&
					board[7][7] != null && !board[7][7].isMoved() && board[7][5] == null &&
					!putsKingInCheck(7,4,7,5))
			{
				return (new LocationImpl(467,467));
			}
		}
		return (new LocationImpl(-100,-100));
	}

	/**
	 * Checks to see if the move puts own king in check
	 *
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move puts the king in check
	 */
	public boolean putsKingInCheck(int x, int y, int r, int c) {
		Piece.Color pieceColor = ((board[x][y])).getColor();
		Piece temp1 = board[x][y];
		Piece temp2 = board[r][c];
		forceMoveWithoutScore(x,y,r,c);

		boolean returner = false;
		if (pieceColor == Piece.Color.WHITE) {

						returner = locationToBoolean(isCheck((pieces[4]).getLocation().getXAxis(),((LocationImpl)(pieces[4]).getLocation()).getYAxis()));
			//board[r][c].setLocation(new LocationImpl(r,c));
			//pieces[4].setLocation(new LocationImpl(x,y));
		} else {
						returner = locationToBoolean(isCheck((pieces[20]).getLocation().getXAxis(),((LocationImpl)(pieces[20]).getLocation()).getYAxis()));
			//pieces[20].setLocation(new LocationImpl(x,y));
		}
		board[x][y] = temp1;
		board[r][c] = temp2;
		board[x][y].setLocation(new LocationImpl(x,y));

		return returner;
	}

	/**
	 * Checks to see if the move puts the enemies king in check
	 *
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move puts the king in check
	 */
	public boolean putsOppositeKingInCheck(int x, int y, int r, int c) {
		Piece.Color pieceColor = ((board[x][y])).getColor();
		Piece temp1 = board[x][y];
		Piece temp2 = board[r][c];
		forceMoveWithoutScore(x,y,r,c);

		boolean returner = false;
		if (pieceColor == Piece.Color.WHITE) {

			returner = locationToBoolean(isCheck((pieces[20]).getLocation().getXAxis(),((LocationImpl)(pieces[4]).getLocation()).getYAxis()));
			//board[r][c].setLocation(new LocationImpl(r,c));
			//pieces[4].setLocation(new LocationImpl(x,y));
		} else {
			returner = locationToBoolean(isCheck((pieces[4]).getLocation().getXAxis(),((LocationImpl)(pieces[20]).getLocation()).getYAxis()));
			//pieces[20].setLocation(new LocationImpl(x,y));
		}
		board[x][y] = temp1;
		board[r][c] = temp2;
		board[x][y].setLocation(new LocationImpl(x,y));

		return returner;
	}


	/**
	 * Checks whether or not the king is in check.
	 *
	 * @return whether or not the king at the specified location is in check
	 */
	public LocationImpl isCheck(int x, int y)
	{
		if(board[x][y].getColor() == Piece.Color.BLACK)
		{
			for(int i = 0; i < 16; i++)
			{
				if(pieces[i] != null)
				{
					if(locationToBoolean(isValidMove(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis(),x,y)))
					{
						return new LocationImpl(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis());
					}
				}
			}
			return new LocationImpl(-100,-100);
		}
		else
		{
			for(int i = 16; i < 32; i++)
			{
				if(pieces[i] != null)
				{
					if(locationToBoolean(isValidMove(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis(),x,y)))
					{
						return new LocationImpl(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis());
					}
				}
			}
			return new LocationImpl(-100,-100);
		}
	}

	/**
	 * Checks whether or not the piece is protected.
	 *
	 * @return whether or not the piece at the specified location is protected
	 */
	public LocationImpl isProtect(int x, int y)
	{
		if(board[x][y].getColor() == Piece.Color.BLACK)
		{
			for(int i = 16; i < 32; i++)
			{
				if(pieces[i] != null)
				{
					if(locationToBoolean(isValidMove(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis(),x,y)))
					{
						return new LocationImpl(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis());
					}
				}
			}
			return new LocationImpl(-100,-100);
		}
		else
		{
			for(int i = 0; i < 16; i++)
			{
				if(pieces[i] != null)
				{
					if(locationToBoolean(isValidMove(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis(),x,y)))
					{
						return new LocationImpl(pieces[i].getLocation().getXAxis(),pieces[i].getLocation().getYAxis());
					}
				}
			}
			return new LocationImpl(-100,-100);
		}
	}

	public boolean isCheckmateKingMove(Piece.Color color)
	{
		int x;
		int y;

		LocationImpl isCheckLocation;
		if(color.equals(Piece.Color.WHITE))
		{
			x = pieces[4].getLocation().getXAxis();
			y = pieces[4].getLocation().getYAxis();
			 isCheckLocation = isCheck(x,y);
			if(isCheckLocation.getXAxis() != -100 && isCheckLocation.getYAxis() != -100) {
				for (int i = x - 1; i <= x + 1; i++) {
					for (int k = y - 1; k <= y + 1; k++) {
						if (locationToBoolean(isValidMove(x,y,i,k)) && !putsKingInCheck(x,y,i,k)) {
							return false;
						}
					}
				}

			}
			else
			{
				return false;
			}
		}
		if(color.equals(Piece.Color.BLACK))
		{
			x = pieces[20].getLocation().getXAxis();
			y = pieces[20].getLocation().getYAxis();
			isCheckLocation = isCheck(x,y);
			if(isCheckLocation.getXAxis() != -100 && isCheckLocation.getYAxis() != -100) {
				for (int i = x - 1; i <= x + 1; i++) {
					for (int k = y - 1; k <= y + 1; k++) {
						if (locationToBoolean(isValidMove(x, y, i, k)) && !putsKingInCheck(x,y,i, k)) {
							return false;
						}
					}
				}
			}
			else
			{
				return false;
			}
		}
		return true;
	}

	public boolean isCheckmatePieceAttack(int x, int y)
	{
		LocationImpl canBeTaken = isCheck(x,y);
		 if(locationToBoolean(canBeTaken) && !putsKingInCheck(canBeTaken.getXAxis(),canBeTaken.getYAxis(),x,y))
		 {
		 	return false;
		 }
		 return true;

	}
	public boolean isCheckmatePieceMove(int x, int y)
	{
		int xCord;
		int yCord;
		LocationImpl holder;
		ArrayList<LocationImpl> list = new ArrayList<>();
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				holder = isValidMove(x,y,r,c);
				if(locationToBoolean(holder))
				{
					list.add(new LocationImpl(r,c));
				}
			}
		}

		if(board[x][y].getColor() != Piece.Color.WHITE)
		{
			for(int i = 0; i < 16; i++)
			{
				if(i!=4) {
					xCord = pieces[i].getLocation().getXAxis();
					yCord = pieces[i].getLocation().getYAxis();
					for (int k = 0; k < list.size(); k++) {

						if (locationToBoolean(isValidMove(xCord, yCord, list.get(k).getXAxis(), list.get(k).getYAxis())) && !(putsKingInCheck(xCord, yCord, list.get(k).getXAxis(), list.get(k).getYAxis()))) {
							return false;//should be false
						}
					}
				}
			}
		}
		else
		{
			for(int i = 16; i < 32; i++)
			{
				if(i!=20) {
					xCord = pieces[i].getLocation().getXAxis();
					yCord = pieces[i].getLocation().getYAxis();
					for (int k = 0; k < list.size(); k++) {
						if (locationToBoolean(isValidMove(xCord, yCord, list.get(k).getXAxis(), list.get(k).getYAxis())) && !putsKingInCheck(xCord, yCord, list.get(k).getXAxis(), list.get(k).getYAxis())) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public boolean isCheckmate(int x, int y)
	{

		Piece.Color color;
		if(board[x][y].getColor() == Piece.Color.WHITE)
		{
			color = Piece.Color.BLACK;
		}
		else
		{
			color = Piece.Color.WHITE;
		}

		if(isCheckmateKingMove(color) == false || isCheckmatePieceAttack(x,y) == false || isCheckmatePieceMove(x,y)== false)
		{
			return false;
		}

		return true;
	}

	public boolean isStalemate(Piece.Color color)
	{
		int xCord;
		int yCord;

		if(color == Piece.Color.WHITE)
		{
			for(int i = 0; i < 16; i++)
			{

				xCord = pieces[i].getLocation().getXAxis();
				yCord = pieces[i].getLocation().getYAxis();
				for(int r = 0; r < 8; r++)
				{
					for(int c = 0; c < 8; c++)
					{
						if(locationToBoolean(isValidMove(xCord,yCord,r,c)) && !(putsKingInCheck(xCord,yCord,r,c)))
						{
							return false;
						}
					}
				}
			}
		}
		else
		{
			for(int i = 16; i < 32; i++)
			{
				xCord = pieces[i].getLocation().getXAxis();
				yCord = pieces[i].getLocation().getYAxis();
				for(int r = 0; r < 8; r++)
				{
					for(int c = 0; c < 8; c++)
					{
						if (locationToBoolean(isValidMove(xCord, yCord, r, c)) && !putsKingInCheck(xCord, yCord, r, c)) {
								return false;
							}
					}
				}
			}
		}
		return true;
	}

//OLD ISCHECK()
//	public boolean isCheck(int x, int y) {
//		for (int r = 0; r < board.length; r++) {
//			for (int c = 0; c < board[0].length; c++) {
//				if (board[r][c] != null && board[r][c].getColor() != board[x][y].getColor()) {
//					if (locationToBoolean(isValidMove(r, c, x, y))) {
//						return true;
//					}
//				}
//			}
//		}
//		return false;
//	}



}