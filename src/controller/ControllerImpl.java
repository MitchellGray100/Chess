package controller;

import java.util.ArrayList;
import java.util.Scanner;

import board.BoardImpl;
import location.Location;
import location.LocationImpl;
import piecesPackage.Piece;

/** Implementation of a controller for the chess game */
public class ControllerImpl implements Controller {
	int turns = 0;

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public void incrementTurns() {
		turns++;
	}

	/** The representation of the chess board */
	BoardImpl board;

	/**
	 * Creates a new Controller instance
	 */
	public ControllerImpl() {
		board = new board.BoardImpl();
	}

	public void printBoard() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		for (int r = 0; r < 8; r++) {
			if (r == 0) {
				System.out.println("x01234567");
			}
			for (int c = 0; c < 8; c++) {
				if (c == 0) {
					System.out.println(r);
				}
				if (board.squareInfo(r, c) == null) {
					if (r + c % 2 != 0) {
						System.out.print("⬜");
					} else {
						System.out.print("⬛");
					}
				} else if (board.squareInfo(r, c).getType() == piecesPackage.Piece.Type.PAWN) {
					System.out.print("P");
				} else if (board.squareInfo(r, c).getType() == piecesPackage.Piece.Type.BISHOP) {
					System.out.print("B");
				} else if (board.squareInfo(r, c).getType() == piecesPackage.Piece.Type.KNIGHT) {
					System.out.print("K");
				} else if (board.squareInfo(r, c).getType() == piecesPackage.Piece.Type.QUEEN) {
					System.out.print("Q");
				} else if (board.squareInfo(r, c).getType() == piecesPackage.Piece.Type.ROOK) {
					System.out.print("R");
				} else {
					System.out.print("X");
				}
			}
		}
	}

	public boolean move(int x, int y, int r, int c) {

		Scanner scan = new Scanner(System.in);
		boolean repeat = true;
		piecesPackage.Piece.Type type = piecesPackage.Piece.Type.QUEEN;

		if (board.squareInfo(x, y).getType() == piecesPackage.Piece.Type.PAWN && (r == 0 || r == 7)) {
			while (repeat) {
				System.out.println("What piece would you like your pawn to promote to? Knight/Bishop/Rook/Queen: ");

				switch (scan.nextLine().toLowerCase()) {
				case "knight":
					type = Piece.Type.KNIGHT;
					repeat = false;
					break;
				case "bishop":
					type = Piece.Type.BISHOP;
					repeat = false;
					break;
				case "rook":
					type = Piece.Type.ROOK;
					repeat = false;
					break;
				case "queen":
					type = Piece.Type.QUEEN;
					repeat = false;
					break;
				default:
					System.out.println("Enter a valid piece.");
					break;

				}

			}
		}
		return board.move(x, y, r, c, type);
	}

	public void userMove() {
		boolean repeat = true;

		while (repeat) {
			Scanner scan = new Scanner(System.in);

			System.out.println("What is the x value of your piece?: ");
			int x = scan.nextInt();
			System.out.println("What is the y value of your piece?: ");
			int y = scan.nextInt();
			System.out.println("What is the r value of your piece?: ");
			int r = scan.nextInt();
			System.out.println("What is the c value of your piece?: ");
			int c = scan.nextInt();

			if (!move(x, y, r, c)) {
				System.out.println("Please make a valid move.");
			} else {
				repeat = false;
			}
		}
	}

	public boolean isStalemate(Piece.Color color) {
		return board.isStalemate(color);
	}

	public boolean isCheckmate(Piece.Color color) {
		int x;
		int y;
		if (color == piecesPackage.Piece.Color.WHITE) {
			x = board.piecesGetter(4).getLocation().getXAxis();
			y = board.piecesGetter(4).getLocation().getYAxis();
		} else {
			x = board.piecesGetter(24).getLocation().getXAxis();
			y = board.piecesGetter(24).getLocation().getYAxis();
		}
		return board.isCheckmate(x, y);
	}

	public void aiMove(Piece.Color color) {
		Location[] locations = board.aiMove(color);
		board.move(locations[0].getXAxis(), locations[0].getYAxis(), locations[1].getXAxis(), locations[1].getYAxis(),
				Piece.Type.QUEEN);
	}

	public int getScore(Piece.Color color) {
		if (color == Piece.Color.WHITE) {
			return board.getWhitePoints();
		} else {
			return board.getBlackPoints();
		}
	}

	public Piece squareInfo(int x, int y) {
		return board.squareInfo(x, y);
	}

	public void setSquareInfo(int x, int y, Piece piece) {
		board.changeSquare(x, y, piece);
	}

	public ArrayList<LocationImpl> validMoveList(int x, int y) {
		ArrayList<LocationImpl> list = new ArrayList<>();

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (board.locationToBoolean(isValidMove(x, y, r, c))) {
					list.add(new location.LocationImpl(r, c));
				}
			}
		}
		return list;
	}

	public LocationImpl isValidMove(int x, int y, int r, int c) {
		return board.isValidMove(x, y, r, c);
	}

	public boolean putsKingInCheck(int x, int y, int r, int c) {
		return board.putsKingInCheck(x, y, r, c);
	}

}
