package com.robotchad.chess.client.Main;

import com.robotchad.chess.client.controller.ControllerImpl;
import com.robotchad.chess.client.pieces.Piece;

public class Main {
    public static void main(String[] args)
    {
        ControllerImpl controller = new ControllerImpl();
        Piece.Color player = Piece.Color.WHITE;
        String end = "";

        while(end.equals(""))
        {
            System.out.println("The Black Score Is: " + controller.getScore(Piece.Color.BLACK));
            System.out.println("The White Score Is: " + controller.getScore(Piece.Color.WHITE));

            if(controller.isCheckmate(player))
            {
                end = player.toString();
            }
            else if(controller.isStalemate(player))
            {
                end = "Stalemate.";
            }
            else {
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
        if(end.equals("Stalemate."))
        {
            System.out.println("GAME OVER. STALEMATE");
        }
        else
        {
            System.out.println("GAME OVER. " + end + " WINS");
        }
        System.out.println("GAME OVER");
    }
}
