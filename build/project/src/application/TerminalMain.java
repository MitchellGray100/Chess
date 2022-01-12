package application;

import controller.ControllerImpl;
import piecesPackage.Piece;

public class TerminalMain {
	public static void main(String[] args) {
		ControllerImpl controller = new ControllerImpl();
		Piece.Color player = Piece.Color.WHITE;
		String end = "";

		while (end.equals("")) {
			System.out.println("The Black Score Is: " + controller.getScore(Piece.Color.BLACK));
			System.out.println("The White Score Is: " + controller.getScore(Piece.Color.WHITE));

			if (controller.isCheckmate(player)) {
				if (player == Piece.Color.WHITE)
					end = "BLACK";
				else
					end = "WHITE";
			} else if (controller.isStalemate(player)) {
				end = "Stalemate.";
			} else {
				if (player == Piece.Color.BLACK) {
					controller.aiMove(Piece.Color.BLACK);
				} else {
					controller.userMove();
				}

				controller.printBoard();
				if (player == Piece.Color.WHITE)
					player = Piece.Color.BLACK;
				else
					player = Piece.Color.WHITE;
			}
		}
		if (end.equals("Stalemate.")) {
			System.out.println("GAME OVER. STALEMATE");
		} else {
			System.out.println("GAME OVER. " + end + " WINS");
		}
		System.out.println("GAME OVER");
	}
}
