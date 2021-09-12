package board;

import java.util.ArrayList;

import location.LocationImpl;
import piecesPackage.Pawn;
import piecesPackage.Piece;
import piecesPackage.PieceFactory;

/**
 * An abstract implementation of the chess board
 */
public abstract class AbstractBoard implements Board {

	/**
	 * A 2d array of pieces representing the chess board
	 */
	Piece[][] board = new Piece[8][8];
	/**
	 * Number of points for white Invariant: non negative
	 */
	int whitePoints = 0;
	/**
	 * Number of points for black Invariant: non negative
	 */
	int blackPoints = 0;

	/**
	 * How many turns have been taken in the game. Each move is a turn.
	 */
	int turn = 0;

	/**
	 * All pieces of the game in an array.
	 */
	protected Piece[] pieces = new piecesPackage.Piece[32];

	/**
	 * Getter for the pieces array
	 *
	 * @param num The pos in the pieces array needed
	 * @return The piece at the pieces[num]
	 */
	public Piece piecesGetter(int num) {
		return pieces[num];
	}

	/**
	 * Setter for the pieces array
	 *
	 * @param num The pos in the pieces array needed
	 */
	public void piecesSetter(int num, Piece piece) {
		pieces[num] = piece;
	}

	public int getWhitePoints() {
		return whitePoints;
	}

	public int getBlackPoints() {
		return blackPoints;
	}

	public piecesPackage.Piece squareInfo(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7)
			return null;
		return board[x][y];
	}

	public void deleteSquare(int x, int y) {
		board[x][y] = null;
	}

	public void changeSquare(int x, int y, Piece piece) {
		board[x][y] = piece;
	}

	public location.LocationImpl[] aiMove(piecesPackage.Piece.Color color) {
		int maxScore = -1000;
		location.LocationImpl maxLocation = new location.LocationImpl(-100, -100);
		location.LocationImpl pieceLocation = new location.LocationImpl(-100, -100);
		location.LocationImpl[] returner = new location.LocationImpl[2];
		int start;
		int end;

		if (color == Piece.Color.WHITE) {
			start = 0;
			end = 16;
		} else {
			start = 16;
			end = 32;
		}

		for (int i = start; i < end; i++) {
			if (pieces[i] == null) {
				continue;
			}
			int xAXis = pieces[i].getLocation().getXAxis();
			int yAxis = pieces[i].getLocation().getYAxis();
			int pieceValue = pieces[i].getValue();
			for (int r = 0; r < 8; r++) {
				for (int c = 0; c < 8; c++) {
					int adder = 0;

					if (maxScore >= 300) {
						break;
					}

					if (color == Piece.Color.WHITE) {
						if (putsEnemyInStalemate(xAXis, yAxis, r, c)) {
							if (whitePoints < blackPoints)
								maxScore = 250;
						}
					} else {
						if (putsEnemyInStalemate(xAXis, yAxis, r, c)) {
							if (blackPoints < whitePoints) {
								maxScore = 250;
							}
						}
					}

					if (pieces[i] != null && locationToBoolean(isValidMove(xAXis, yAxis, r, c))
							&& !putsKingInCheck(xAXis, yAxis, r, c)) {

						if (putsPieceInProtection(xAXis, yAxis, r, c)) {
							adder++;
						}
						if (locationToBoolean(isProtect(xAXis, yAxis))) {
							adder--;
						}
						if (isProtecting(xAXis, yAxis)) {
							adder--;
						}
						if (putsPieceInProtecting(xAXis, yAxis, r, c)) {
							adder++;
						}
						if (r >= 2 && c >= 2 && r <= 5 && c <= 5) {
							adder++;
						}
						if (pieces[i].getType() == Piece.Type.PAWN && (r == 0 || r == 7)) {
							adder += 50;
						}

						Piece temp1 = board[xAXis][yAxis];
						Piece temp2 = board[r][c];
						forceMoveWithoutScore(xAXis, yAxis, r, c);

						Piece.Color enemyColor;

						if (color == Piece.Color.WHITE) {
							enemyColor = Piece.Color.BLACK;
						} else {
							enemyColor = Piece.Color.WHITE;
						}

						int enemyAIScore;
						enemyAIScore = enemyAiMove(enemyColor);

						board[xAXis][yAxis] = temp1;
						board[r][c] = temp2;
						board[xAXis][yAxis].setLocation(new LocationImpl(xAXis, yAxis));

						adder -= enemyAIScore;

						if (board[r][c] == null) {

							if (maxScore == -1000) {

								maxScore = adder;
								if (putsEnemyKingInCheckmate(xAXis, yAxis, r, c)) {
									maxScore += 300;
								} else if (putsOppositeKingInCheck(xAXis, yAxis, r, c)
										&& putsPieceInProtection(xAXis, yAxis, r, c)) {
									maxScore += 150;
								}
								maxLocation = new LocationImpl(r, c);
								pieceLocation = new LocationImpl(xAXis, yAxis);
							} else if (putsEnemyKingInCheckmate(xAXis, yAxis, r, c)) {
								maxScore = 300;
								maxLocation = new LocationImpl(r, c);
								pieceLocation = new LocationImpl(xAXis, yAxis);
							} else if (putsOppositeKingInCheck(xAXis, yAxis, r, c)
									&& putsPieceInProtection(xAXis, yAxis, r, c)) {
								maxScore = 150 + adder;

								maxLocation = new LocationImpl(r, c);
								pieceLocation = new LocationImpl(xAXis, yAxis);
							} else if (pieces[i].getType() == Piece.Type.PAWN && Math.abs(r - xAXis) == 2
									&& maxScore <= 1) {
								maxScore = 1 + adder;
								maxLocation = new LocationImpl(r, c);
								pieceLocation = new LocationImpl(xAXis, yAxis);
							} else if (maxScore <= adder) {

								maxScore = adder;

								if (putsEnemyKingInCheckmate(xAXis, yAxis, r, c)) {
									maxScore += 300;
								} else if (putsOppositeKingInCheck(xAXis, yAxis, r, c)
										&& putsPieceInProtection(xAXis, yAxis, r, c)) {
									maxScore += 150;
								}

								maxLocation = new LocationImpl(r, c);
								pieceLocation = new LocationImpl(xAXis, yAxis);
							}
						} else {
							int oppositePieceValue = board[r][c].getValue();
							if (isProtecting(r, c)) {
								adder++;
							}
							if (locationToBoolean(isProtect(r, c))) {
								adder--;
							}

							if (putsEnemyKingInCheckmate(xAXis, yAxis, r, c)) {
								maxScore = 300;
								maxLocation = new LocationImpl(r, c);
								pieceLocation = new LocationImpl(xAXis, yAxis);
							} else if (putsOppositeKingInCheck(xAXis, yAxis, r, c)
									&& putsPieceInProtection(xAXis, yAxis, r, c)) {
								maxScore = 150 + adder;

								maxLocation = new LocationImpl(r, c);
								pieceLocation = new LocationImpl(xAXis, yAxis);
							} else if (pieceValue + adder < oppositePieceValue) {
								maxScore = 100 + adder;

								maxLocation = new LocationImpl(r, c);
								pieceLocation = new LocationImpl(xAXis, yAxis);
							} else if (pieceValue <= oppositePieceValue) {
								maxScore = 50 + adder;

								maxLocation = new LocationImpl(r, c);
								pieceLocation = new LocationImpl(xAXis, yAxis);
							} else if (oppositePieceValue + adder >= maxScore) {
								maxScore = oppositePieceValue + adder;
								maxLocation = new LocationImpl(r, c);
								pieceLocation = new LocationImpl(xAXis, yAxis);
							}
						}
					}
				}
			}
		}

		returner[0] = pieceLocation;
		returner[1] = maxLocation;
		return returner;
	}

	public int enemyAiMove(Piece.Color color) {
		int maxScore = 0;
		int start;
		int end;

		if (color == Piece.Color.WHITE) {
			start = 0;
			end = 16;
		} else {
			start = 16;
			end = 32;
		}

		for (int i = start; i < end; i++) {
			if (pieces[i] == null) {
				continue;
			}
			int xAXis = pieces[i].getLocation().getXAxis();
			int yAxis = pieces[i].getLocation().getYAxis();
			int pieceValue = pieces[i].getValue();
			for (int r = 0; r < 8; r++) {
				for (int c = 0; c < 8; c++) {

					if (maxScore == 250) {
						break;
					}

					if (color == Piece.Color.WHITE) {
						if (putsEnemyInStalemate(xAXis, yAxis, r, c)) {
							if (whitePoints < blackPoints)
								maxScore = 250;
						}
					} else {
						if (putsEnemyInStalemate(xAXis, yAxis, r, c)) {
							if (blackPoints < whitePoints) {
								maxScore = 250;
							}
						}
					}

					if (pieces[i] != null && locationToBoolean(isValidMove(xAXis, yAxis, r, c))
							&& !putsKingInCheck(xAXis, yAxis, r, c)) {

						int adder = 0;
						if (putsPieceInProtection(xAXis, yAxis, r, c)) {
							adder++;
						}
						if (locationToBoolean(isProtect(xAXis, yAxis))) {
							adder--;
						}
						if (isProtecting(xAXis, yAxis)) {
							adder--;
						}
						if (putsPieceInProtecting(xAXis, yAxis, r, c)) {
							adder++;
						}
						if (pieces[i].getType() == Piece.Type.PAWN && (r == 0 || r == 7)) {
							adder += 50;
						}

						if (board[r][c] == null) {

							if (maxScore <= 0) {
								maxScore = adder;

								if (putsEnemyKingInCheckmate(xAXis, yAxis, r, c)) {
									maxScore += 250;
								} else if (putsOppositeKingInCheck(xAXis, yAxis, r, c)
										&& putsPieceInProtection(xAXis, yAxis, r, c)) {
									maxScore += 150;
								}
							} else if (pieces[i].getType() == Piece.Type.PAWN && Math.abs(r - xAXis) == 2
									&& maxScore <= 1) {
								maxScore = 1 + adder;
							} else if (maxScore <= adder) {
								maxScore = adder;
							}
						} else {
							int oppositePieceValue = board[r][c].getValue();
							if (isProtecting(r, c)) {
								adder++;
							}
							if (locationToBoolean(isProtect(r, c))) {
								adder--;
							}

							if (putsEnemyKingInCheckmate(xAXis, yAxis, r, c)) {
								maxScore = 250;
							} else if (putsOppositeKingInCheck(xAXis, yAxis, r, c)
									&& putsPieceInProtection(xAXis, yAxis, r, c)) {
								maxScore = 150 + adder;

							} else if (pieceValue + adder < oppositePieceValue) {
								maxScore = 100 + adder;
							} else if (pieceValue <= oppositePieceValue) {
								maxScore = 50 + adder;
							} else if (oppositePieceValue + adder >= maxScore) {
								maxScore = oppositePieceValue + adder;
							}
						}
					}
				}
			}
		}

		return maxScore;
	}

	public boolean move(int x, int y, int r, int c, Piece.Type type) {
		LocationImpl location = isValidMove(x, y, r, c);

		if (locationToBoolean(location) && !putsKingInCheck(x, y, r, c)) {
			if (board[r][c] != null) {
				changeScore(r, c);
				pieces[board[r][c].getArrayLocation()] = null;
			}
			if (Math.abs(location.getXAxis()) != 100 && Math.abs(location.getYAxis()) != 100
					&& location.getXAxis() != 350 && location.getXAxis() != 310 && location.getXAxis() != 317
					&& location.getXAxis() != 357) {
				changeScore(location.getXAxis(), location.getYAxis());
				board[location.getXAxis()][location.getYAxis()] = null;
			}

			if (location.getXAxis() == 350 || location.getXAxis() == 310 || location.getXAxis() == 317
					|| location.getXAxis() == 357) {
				if (location.getXAxis() == 350) {
					board[0][5] = board[0][3];
					board[0][3] = null;
					pieces[3].setLocation(new LocationImpl(0, 5));
					board[0][4] = board[0][7];
					pieces[7].setLocation(new LocationImpl(0, 4));
					board[0][7] = null;
					board[0][5].setMoved(true);
					board[0][4].setMoved(true);
				} else if (location.getXAxis() == 310) {
					board[0][1] = board[0][3];
					board[0][3] = null;
					pieces[3].setLocation(new LocationImpl(0, 1));
					board[0][2] = board[0][0];
					pieces[0].setLocation(new LocationImpl(0, 2));
					board[0][0] = null;
					board[0][1].setMoved(true);
					board[0][2].setMoved(true);
				} else if (location.getXAxis() == 317) {
					board[7][1] = board[7][3];
					board[7][3] = null;
					pieces[19].setLocation(new LocationImpl(7, 1));
					board[7][2] = board[7][0];
					pieces[16].setLocation(new LocationImpl(7, 2));
					board[7][0] = null;
					board[7][1].setMoved(true);
					board[7][2].setMoved(true);
				} else if (location.getXAxis() == 357) {
					board[7][5] = board[7][3];
					board[7][3] = null;
					pieces[19].setLocation(new LocationImpl(7, 5));
					board[7][4] = board[7][7];
					pieces[23].setLocation(new LocationImpl(7, 4));
					board[7][7] = null;
					board[7][5].setMoved(true);
					board[7][4].setMoved(true);
				}
			} else {
				if (board[x][y].getType() == Piece.Type.PAWN) {
					((Pawn) board[x][y]).setPrevLocation(new LocationImpl(x, y));
					((Pawn) board[x][y]).setTurnMoved(turn);
				}

				board[x][y].setMoved(true);
				board[x][y].setLocation(new LocationImpl(r, c));
				board[r][c] = board[x][y];
				board[x][y] = null;

				// Promoting
				if ((r == 0 || r == 7) && board[r][c].getType() == Piece.Type.PAWN) {
					PieceFactory factory = new PieceFactory();
					int arrayLocation = board[r][c].getArrayLocation();
					board[r][c] = factory.getPiece(type, board[r][c].getColor());
					board[r][c].setMoved(true);
					board[r][c].setArrayLocation(arrayLocation);
					board[r][c].setLocation(new LocationImpl(r, c));
					if (board[r][c].getColor() == Piece.Color.WHITE) {
						whitePoints--;
					} else {
						blackPoints--;
					}
					board[r][c].setValue(-board[r][c].getValue());
					changeScore(r, c);
					board[r][c].setValue(-board[r][c].getValue());
					pieces[arrayLocation] = board[r][c];
				}

			}
			turn++;
			return true;
		}
		return false;
	}

	public void forceMove(int x, int y, int r, int c) {
		if (!(r > 7 || r < 0 || c > 7 || c < 0 || x > 7 || x < 0 || y > 7 || y < 0)) {
			Piece holder;
			board[x][y].setLocation(new LocationImpl(r, c));
			holder = board[x][y];
			board[x][y].setMoved(true);
			if (board[r][c] != null) {
				changeScore(r, c);
			}

			board[x][y] = null;
			board[r][c] = holder;
		}
	}

	public void forceMoveWithoutScore(int x, int y, int r, int c) {
		if (!(r > 7 || r < 0 || c > 7 || c < 0 || x > 7 || x < 0 || y > 7 || y < 0)) {
			Piece holder;
			board[x][y].setLocation(new LocationImpl(r, c));
			holder = board[x][y];

			board[x][y] = null;
			board[r][c] = holder;
		}
	}

	public LocationImpl isValidMove(int x, int y, int r, int c) {
		if (r > 7 || r < 0 || c > 7 || c < 0 || x > 7 || x < 0 || y > 7 || y < 0)
			return (new LocationImpl(-100, -100));
		if (x == r && y == c) {
			return (new LocationImpl(-100, -100));
		}

		try {
			if ((board[r][c] != null && board[r][c].getColor() == board[x][y].getColor())) {
				return (new LocationImpl(-100, -100));
			}
			Piece.Type pieceType = board[x][y].getType();
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
			return (new LocationImpl(-100, -100));
		}
	}

	public LocationImpl isValidProtect(int x, int y, int r, int c) {
		if (r > 7 || r < 0 || c > 7 || c < 0 || x > 7 || x < 0 || y > 7 || y < 0)
			return (new LocationImpl(-100, -100));
		if (x == r && y == c) {
			return (new LocationImpl(-100, -100));
		}

		try {
			if ((board[r][c] != null && board[r][c].getColor() != board[x][y].getColor())) {
				return (new LocationImpl(-100, -100));
			}
			Piece.Type pieceType = board[x][y].getType();
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
			return (new LocationImpl(-100, -100));
		}
	}

	/**
	 * Determines if the given piece location's piece is protecting another piece.
	 *
	 * @return Whether or not the piece is protecting another piece.
	 */
	public boolean isProtecting(int x, int y) {
		if (board[x][y].getColor() == Piece.Color.WHITE) {
			for (int i = 0; i < 16; i++) {
				if (pieces[i] != null && locationToBoolean(
						isValidProtect(x, y, pieces[i].getLocation().getXAxis(), pieces[i].getLocation().getYAxis()))) {
					return true;
				}
			}
		} else {
			for (int i = 16; i < 32; i++) {
				if (pieces[i] != null && locationToBoolean(
						isValidProtect(x, y, pieces[i].getLocation().getXAxis(), pieces[i].getLocation().getYAxis()))) {
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * Determines if the move puts a piece in a spot that protects an ally piece.
	 *
	 * @return whether or not it does.
	 */
	public boolean putsPieceInProtecting(int x, int y, int r, int c) {
		Piece temp1 = board[x][y];
		Piece temp2 = board[r][c];
		forceMoveWithoutScore(x, y, r, c);

		boolean returner;

		returner = isProtecting(r, c);

		board[x][y] = temp1;
		board[r][c] = temp2;
		board[x][y].setLocation(new LocationImpl(x, y));

		return returner;
	}

	/**
	 * Converts the output of isValidMove to boolean
	 *
	 * @param input Location
	 * @return whether or not the move was valid or not. All negative locations are
	 *         invalid and all positive locations are positive
	 */
	public boolean locationToBoolean(LocationImpl input) {
		return input.getXAxis() >= 0;
	}

	public void changeScore(int r, int c) {
		if (whitePoints == blackPoints) {
			if (((board[r][c])).getColor().equals(Piece.Color.BLACK)) {
				whitePoints += ((board[r][c])).getValue();
			}
			if (((board[r][c])).getColor().equals(Piece.Color.WHITE)) {
				blackPoints += ((board[r][c])).getValue();
			}
		} else if (board[r][c].getColor().equals(Piece.Color.BLACK)) {
			if (whitePoints > blackPoints) {
				whitePoints += ((board[r][c])).getValue();
			} else if ((whitePoints < blackPoints) && (whitePoints + ((board[r][c])).getValue() > blackPoints)) {
				whitePoints += ((board[r][c])).getValue() - blackPoints;
				blackPoints = 0;
			} else if ((whitePoints < blackPoints) && (whitePoints + ((board[r][c])).getValue() < blackPoints)) {
				blackPoints -= board[r][c].getValue();
			} else if ((whitePoints < blackPoints) && (whitePoints + ((board[r][c])).getValue() == blackPoints)) {
				blackPoints = 0;
				whitePoints = 0;
			}
		} else if (board[r][c].getColor().equals(Piece.Color.WHITE)) {
			if (blackPoints > whitePoints) {
				blackPoints += ((board[r][c])).getValue();
			} else if ((blackPoints < whitePoints) && (blackPoints + board[r][c].getValue() > whitePoints)) {
				blackPoints += ((board[r][c])).getValue() - whitePoints;
				whitePoints = 0;
			} else if ((blackPoints < whitePoints) && (blackPoints + ((board[r][c])).getValue() < whitePoints)) {
				whitePoints -= board[r][c].getValue();
			} else if ((blackPoints < whitePoints) && (blackPoints + ((board[r][c])).getValue() == whitePoints)) {
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
		boolean calc = r == x + 1 && (c == y + 1 || c == y - 1);
		if (board[r][c] != null) {
			if (((board[x][y])).getColor() == Piece.Color.WHITE) {

				if (calc) {
					return (new LocationImpl(100, 100));
				}
			}
			if (((board[x][y])).getColor() == Piece.Color.BLACK) {
				if (r == x - 1 && (c == y + 1 || c == y - 1)) {
					return (new LocationImpl(100, 100));
				}
			}
		} else {
			if (((board[x][y])).getColor() == Piece.Color.WHITE) {
				if (((r == x + 1) || (x == 1 && c == y && r == x + 2 && board[2][y] == null)) && c == y) {
					return (new LocationImpl(100, 100));
				}
				if ((calc)) {
					if (board[r - 1][c] != null && board[r - 1][c].getType() == Piece.Type.PAWN
							&& ((Pawn) board[r - 1][c]).getTurnMoved() == turn - 1
							&& ((Pawn) board[r - 1][c]).getPrevLocation().getXAxis() == r + 1) {
						return (new LocationImpl(r - 1, c));
					}
				}
			}
			if (((board[x][y])).getColor() == Piece.Color.BLACK) {
				if (((r == x - 1) || (x == 6 && c == y && r == x - 2 && board[5][y] == null)) && c == y) {
					return (new LocationImpl(100, 100));
				}
				if (((r == x - 1) && (c == y - 1 || c == y + 1))) {
					if (board[r + 1][c] != null && board[r + 1][c].getType() == Piece.Type.PAWN
							&& ((Pawn) board[r + 1][c]).getTurnMoved() == turn - 1
							&& ((Pawn) board[r + 1][c]).getPrevLocation().getXAxis() == r - 1) {
						return (new LocationImpl(r + 1, c));
					}
				}
			}
		}

		return (new LocationImpl(-100, -100));// should be false
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
		boolean calc = (Math.abs(x - r) == 1 && Math.abs(y - c) == 2) || Math.abs(x - r) == 2 && Math.abs(y - c) == 1;
		if (board[r][c] != null && calc) {

			return (new LocationImpl(100, 100));
		}
		if ((board[r][c] == null) && calc) {
			return (new LocationImpl(100, 100));
		}

		return (new LocationImpl(-100, -100));
	}

	/**
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move is valid for a bishop
	 */
	public LocationImpl bishopMove(int x, int y, int r, int c) {
		boolean calc = Math.abs(y - c) == Math.abs(x - r);
		if ((board[r][c] != null && calc) || (board[r][c] == null && calc)) {
			if (r < x && c > y) {
				for (int i = 1; i < x - r; i++) {
					if (board[x - i][y + i] != null) {
						return (new LocationImpl(-100, -100));
					}
				}
				return (new LocationImpl(100, 100));
			} else if (r < x && c < y) {
				for (int i = 1; i < x - r; i++) {
					if (board[x - i][y - i] != null) {
						return (new LocationImpl(-100, -100));
					}
				}
				return (new LocationImpl(100, 100));
			} else if (r > x && c < y) {
				for (int i = 1; i < r - x; i++) {
					if (board[x + i][y - i] != null) {
						return (new LocationImpl(-100, -100));
					}
				}
				return (new LocationImpl(100, 100));
			} else if (r > x && c > y) {
				for (int i = 1; i < r - x; i++) {
					if (board[x + i][y + i] != null) {
						return (new LocationImpl(-100, -100));
					}
				}
				return (new LocationImpl(100, 100));
			}
		}
		return (new LocationImpl(-100, -100));
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
							return (new LocationImpl(-100, -100));
						}
					}
					return (new LocationImpl(100, 100));
				} else if (r == x && c < y) {
					for (int i = 1; i < y - c; i++) {
						if (board[x][y - i] != null) {
							return (new LocationImpl(-100, -100));
						}
					}
					return (new LocationImpl(100, 100));
				} else if (r > x) {
					for (int i = 1; i < r - x; i++) {
						if (board[x + i][y] != null) {
							return (new LocationImpl(-100, -100));
						}
					}
					return (new LocationImpl(100, 100));
				} else if (r < x) {
					for (int i = 1; i < x - r; i++) {
						if (board[x - i][y] != null) {
							return (new LocationImpl(-100, -100));
						}
					}
					return (new LocationImpl(100, 100));
				}
			}
		}

		return (new LocationImpl(-100, -100));
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
			return (new LocationImpl(100, 100));
		}
		return (new LocationImpl(-100, -100));
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

		boolean calc1 = Math.abs(r - x) == 1 && Math.abs(c - y) == 1;
		boolean calc2 = r == x && Math.abs(c - y) == 1;
		boolean calc3 = c == y && Math.abs(r - x) == 1;
		if (board[r][c] != null) {
			if (calc1) {
				return (new LocationImpl(100, 100));
			} else if (calc2) {
				return (new LocationImpl(100, 100));
			} else if (calc3) {
				return (new LocationImpl(100, 100));
			}
		} else {
			if (calc1) {
				return (new LocationImpl(100, 100));
			} else if (calc2) {
				return (new LocationImpl(100, 100));
			} else if (calc3) {
				return (new LocationImpl(100, 100));
			} else if (x == 0 && y == 3 && board[x][y].isMoved() && r == 0 && c == 5 && board[0][7] != null
					&& board[0][7].isMoved() && board[0][5] == null && board[0][6] == null && board[0][4] == null
					&& !putsKingInCheck(0, 3, 0, 5) && !putsKingInCheck(0, 3, 0, 4)) {
				return (new LocationImpl(350, 350));
			} else if (x == 0 && y == 3 && board[x][y].isMoved() && r == 0 && c == 1 && board[0][0] != null
					&& board[0][0].isMoved() && board[0][2] == null && board[0][1] == null
					&& !putsKingInCheck(0, 3, 0, 1) && !putsKingInCheck(0, 3, 0, 2)) {
				return (new LocationImpl(310, 310));
			} else if (x == 7 && y == 3 && board[x][y].isMoved() && r == 7 && c == 1 && board[7][0] != null
					&& board[7][0].isMoved() && board[7][2] == null && board[7][1] == null
					&& !putsKingInCheck(7, 3, 7, 1) && !putsKingInCheck(7, 3, 7, 2)) {
				return (new LocationImpl(317, 317));
			} else if (x == 7 && y == 3 && board[x][y].isMoved() && r == 7 && c == 5 && board[7][7] != null
					&& board[7][7].isMoved() && board[7][5] == null && board[7][6] == null && board[7][4] == null
					&& !putsKingInCheck(7, 3, 7, 5) && !putsKingInCheck(7, 3, 7, 4)) {
				return (new LocationImpl(357, 357));
			}
		}
		return (new LocationImpl(-100, -100));
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
		forceMoveWithoutScore(x, y, r, c);

		boolean returner;
		if (pieceColor == Piece.Color.WHITE) {

			returner = locationToBoolean(
					isCheck((pieces[3]).getLocation().getXAxis(), (pieces[3]).getLocation().getYAxis()));

		} else {
			returner = locationToBoolean(
					isCheck((pieces[19]).getLocation().getXAxis(), (pieces[19]).getLocation().getYAxis()));

		}
		board[x][y] = temp1;
		board[r][c] = temp2;
		board[x][y].setLocation(new LocationImpl(x, y));

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
		forceMoveWithoutScore(x, y, r, c);

		boolean returner;
		if (pieceColor == Piece.Color.WHITE) {

			returner = locationToBoolean(
					isCheck((pieces[19]).getLocation().getXAxis(), (pieces[19]).getLocation().getYAxis()));

		} else {
			returner = locationToBoolean(
					isCheck((pieces[3]).getLocation().getXAxis(), (pieces[3]).getLocation().getYAxis()));

		}
		board[x][y] = temp1;
		board[r][c] = temp2;
		board[x][y].setLocation(new LocationImpl(x, y));

		return returner;
	}

	/**
	 * Checks to see if the move puts own piece in protection
	 *
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move puts the king in check
	 */
	public boolean putsPieceInProtection(int x, int y, int r, int c) {
		Piece temp1 = board[x][y];
		Piece temp2 = board[r][c];
		forceMoveWithoutScore(x, y, r, c);

		boolean returner;
		returner = locationToBoolean(isProtect(r, c));

		board[x][y] = temp1;
		board[r][c] = temp2;
		board[x][y].setLocation(new LocationImpl(x, y));

		return returner;
	}

	/**
	 * Checks to see if the move puts enemy king in checkmate
	 *
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move puts the king in check
	 */
	public boolean putsEnemyKingInCheckmate(int x, int y, int r, int c) {
		Piece.Color pieceColor = ((board[x][y])).getColor();
		Piece temp1 = board[x][y];
		Piece temp2 = board[r][c];
		forceMoveWithoutScore(x, y, r, c);
		boolean returner;
		if (pieceColor == Piece.Color.WHITE) {
			returner = isCheckmate(pieces[19].getLocation().getXAxis(), pieces[19].getLocation().getYAxis());
		} else {
			returner = isCheckmate(pieces[3].getLocation().getXAxis(), pieces[3].getLocation().getYAxis());
		}

		board[x][y] = temp1;
		board[r][c] = temp2;
		board[x][y].setLocation(new LocationImpl(x, y));

		return returner;
	}

	/**
	 * Checks to see if the move puts enemy in stalemate
	 *
	 * @param x The row value of the piece
	 * @param y The column value of the piece
	 * @param r The proposed row value of the move
	 * @param c The proposed column value of the move
	 * @return Whether or not the move puts the enemy in stalemate
	 */
	public boolean putsEnemyInStalemate(int x, int y, int r, int c) {
		if (board[x][y] != null) {

			Piece.Color pieceColor = ((board[x][y])).getColor();
			Piece temp1 = board[x][y];
			Piece temp2 = board[r][c];
			forceMoveWithoutScore(x, y, r, c);
			boolean returner;
			if (pieceColor == Piece.Color.WHITE) {
				returner = isStalemate(Piece.Color.BLACK);
			} else {
				returner = isStalemate(Piece.Color.WHITE);
			}

			board[x][y] = temp1;
			board[r][c] = temp2;
			board[x][y].setLocation(new LocationImpl(x, y));

			return returner;
		} else {
			return false;
		}
	}

	/**
	 * Checks whether or not the king is in check.
	 *
	 * @return whether or not the king at the specified location is in check
	 */
	public LocationImpl isCheck(int x, int y) {
		if (board[x][y].getColor() == Piece.Color.BLACK) {
			for (int i = 0; i < 16; i++) {
				if (pieces[i] != null) {
					if (locationToBoolean(isValidMove(pieces[i].getLocation().getXAxis(),
							pieces[i].getLocation().getYAxis(), x, y))) {
						return new LocationImpl(pieces[i].getLocation().getXAxis(), pieces[i].getLocation().getYAxis());
					}
				}
			}
		} else {
			for (int i = 16; i < 32; i++) {
				if (pieces[i] != null) {
					if (locationToBoolean(isValidMove(pieces[i].getLocation().getXAxis(),
							pieces[i].getLocation().getYAxis(), x, y))) {
						return new LocationImpl(pieces[i].getLocation().getXAxis(), pieces[i].getLocation().getYAxis());
					}
				}
			}
		}
		return new LocationImpl(-100, -100);
	}

	/**
	 * Checks whether or not the piece is protected.
	 *
	 * @return whether or not the piece at the specified location is protected
	 */
	public LocationImpl isProtect(int x, int y) {
		if (board[x][y].getColor() == Piece.Color.BLACK) {
			for (int i = 16; i < 32; i++) {
				if (pieces[i] != null) {
					if (locationToBoolean(isValidProtect(pieces[i].getLocation().getXAxis(),
							pieces[i].getLocation().getYAxis(), x, y))) {
						return new LocationImpl(pieces[i].getLocation().getXAxis(), pieces[i].getLocation().getYAxis());
					}
				}
			}
		} else {
			for (int i = 0; i < 16; i++) {
				if (pieces[i] != null) {
					if (locationToBoolean(isValidProtect(pieces[i].getLocation().getXAxis(),
							pieces[i].getLocation().getYAxis(), x, y))) {
						return new LocationImpl(pieces[i].getLocation().getXAxis(), pieces[i].getLocation().getYAxis());
					}
				}
			}
		}
		return new LocationImpl(-100, -100);
	}

	public boolean isCheckmateKingMove(Piece.Color color) {
		int x;
		int y;

		LocationImpl isCheckLocation;
		if (color.equals(Piece.Color.WHITE)) {
			x = pieces[3].getLocation().getXAxis();
			y = pieces[3].getLocation().getYAxis();
			isCheckLocation = isCheck(x, y);
			if (isCheckLocation.getXAxis() != -100 && isCheckLocation.getYAxis() != -100) {
				for (int i = x - 1; i <= x + 1; i++) {
					for (int k = y - 1; k <= y + 1; k++) {
						if (locationToBoolean(isValidMove(x, y, i, k)) && !putsKingInCheck(x, y, i, k)) {
							return false;
						}
					}
				}

			} else {
				return false;
			}
		}
		if (color.equals(Piece.Color.BLACK)) {
			x = pieces[19].getLocation().getXAxis();
			y = pieces[19].getLocation().getYAxis();
			isCheckLocation = isCheck(x, y);
			if (isCheckLocation.getXAxis() != -100 && isCheckLocation.getYAxis() != -100) {
				for (int i = x - 1; i <= x + 1; i++) {
					for (int k = y - 1; k <= y + 1; k++) {
						if (locationToBoolean(isValidMove(x, y, i, k)) && !putsKingInCheck(x, y, i, k)) {
							return false;
						}
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean isCheckmatePieceAttack(int x, int y) {
		LocationImpl canBeTaken = isCheck(x, y);
		return !locationToBoolean(canBeTaken) || putsKingInCheck(canBeTaken.getXAxis(), canBeTaken.getYAxis(), x, y);

	}

	public boolean isCheckmatePieceMove(int x, int y) {
		int xCord;
		int yCord;
		LocationImpl holder;
		ArrayList<LocationImpl> list = new ArrayList<>();
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				holder = isValidMove(x, y, r, c);
				if (locationToBoolean(holder)) {
					list.add(new LocationImpl(r, c));
				}
			}
		}

		if (board[x][y].getColor() != Piece.Color.WHITE) {
			for (int i = 0; i < 16; i++) {
				if (i != 3) {
					xCord = pieces[i].getLocation().getXAxis();
					yCord = pieces[i].getLocation().getYAxis();
					for (int k = 0; k < list.size(); k++) {

						if (locationToBoolean(isValidMove(xCord, yCord, list.get(k).getXAxis(), list.get(k).getYAxis()))
								&& !(putsKingInCheck(xCord, yCord, list.get(k).getXAxis(), list.get(k).getYAxis()))) {
							return false;// should be false
						}
					}
				}
			}
		} else {
			for (int i = 16; i < 32; i++) {
				if (i != 19) {
					xCord = pieces[i].getLocation().getXAxis();
					yCord = pieces[i].getLocation().getYAxis();
					for (int k = 0; k < list.size(); k++) {
						if (locationToBoolean(isValidMove(xCord, yCord, list.get(k).getXAxis(), list.get(k).getYAxis()))
								&& !putsKingInCheck(xCord, yCord, list.get(k).getXAxis(), list.get(k).getYAxis())) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public boolean isCheckmate(int x, int y) {

		Piece.Color color;
		if (board[x][y].getColor() == Piece.Color.WHITE) {
			color = Piece.Color.BLACK;
		} else {
			color = Piece.Color.WHITE;
		}

		return isCheckmateKingMove(color) && isCheckmatePieceAttack(x, y) && isCheckmatePieceMove(x, y);
	}

	public boolean isStalemateHelper(int xCord, int yCord, int r, int c) {
		return locationToBoolean(isValidMove(xCord, yCord, r, c)) && (!putsKingInCheck(xCord, yCord, r, c));
	}

	public boolean isStalemate(Piece.Color color) {
		int xCord;
		int yCord;

		if (color == Piece.Color.WHITE) {
			for (int i = 0; i < 16; i++) {
				if (pieces[i] == null) {
					continue;
				}
				xCord = pieces[i].getLocation().getXAxis();
				yCord = pieces[i].getLocation().getYAxis();

				if (pieces[i].getType() == Piece.Type.ROOK) {
					if (isStalemateHelper(xCord, yCord, xCord - 1, yCord)
							|| isStalemateHelper(xCord, yCord, xCord, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord)
							|| isStalemateHelper(xCord, yCord, xCord, yCord + 1)) {
						return false;
					}
				} else if (pieces[i].getType() == Piece.Type.BISHOP) {
					if (isStalemateHelper(xCord, yCord, xCord + 1, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord + 1)) {
						return false;
					}
				} else if (pieces[i].getType() == Piece.Type.QUEEN || pieces[i].getType() == Piece.Type.KING) {
					if (isStalemateHelper(xCord, yCord, xCord + 1, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord)
							|| isStalemateHelper(xCord, yCord, xCord, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord)
							|| isStalemateHelper(xCord, yCord, xCord, yCord + 1)) {
						return false;
					}
				} else if (pieces[i].getType() == Piece.Type.PAWN) {
					if (isStalemateHelper(xCord, yCord, xCord + 1, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord)) {
						return false;
					}
				} else if (pieces[i].getType() == Piece.Type.KNIGHT) {
					if (isStalemateHelper(xCord, yCord, xCord - 2, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord - 2, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord + 2)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord - 2)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord - 2)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord + 2)
							|| isStalemateHelper(xCord, yCord, xCord + 2, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord + 2, yCord - 1)) {
						return false;
					}
				}

			}
		} else {
			for (int i = 16; i < 32; i++) {
				if (pieces[i] == null) {
					continue;
				}
				xCord = pieces[i].getLocation().getXAxis();
				yCord = pieces[i].getLocation().getYAxis();

				if (pieces[i].getType() == Piece.Type.ROOK) {
					if (isStalemateHelper(xCord, yCord, xCord - 1, yCord)
							|| isStalemateHelper(xCord, yCord, xCord, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord)
							|| isStalemateHelper(xCord, yCord, xCord, yCord + 1)) {
						return false;
					}
				} else if (pieces[i].getType() == Piece.Type.BISHOP) {
					if (isStalemateHelper(xCord, yCord, xCord + 1, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord + 1)) {
						return false;
					}
				} else if (pieces[i].getType() == Piece.Type.QUEEN || pieces[i].getType() == Piece.Type.KING) {
					if (isStalemateHelper(xCord, yCord, xCord + 1, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord)
							|| isStalemateHelper(xCord, yCord, xCord, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord)
							|| isStalemateHelper(xCord, yCord, xCord, yCord + 1)) {
						return false;
					}
				} else if (pieces[i].getType() == Piece.Type.PAWN) {
					if (isStalemateHelper(xCord, yCord, xCord + 1, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord)) {
						return false;
					}
				} else if (pieces[i].getType() == Piece.Type.KNIGHT) {
					if (isStalemateHelper(xCord, yCord, xCord - 2, yCord - 1)
							|| isStalemateHelper(xCord, yCord, xCord - 2, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord + 2)
							|| isStalemateHelper(xCord, yCord, xCord - 1, yCord - 2)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord - 2)
							|| isStalemateHelper(xCord, yCord, xCord + 1, yCord + 2)
							|| isStalemateHelper(xCord, yCord, xCord + 2, yCord + 1)
							|| isStalemateHelper(xCord, yCord, xCord + 2, yCord - 1)) {
						return false;
					}
				}
			}
		}
		return true;
	}

}