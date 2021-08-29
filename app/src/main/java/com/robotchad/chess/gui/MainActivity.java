package com.robotchad.chess.gui;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.robotchad.chess.client.board.Board;
import com.robotchad.chess.client.board.BoardImpl;
import com.robotchad.chess.client.controller.Controller;
import com.robotchad.chess.client.controller.ControllerImpl;
import com.robotchad.chess.client.location.LastLocation;
import com.robotchad.chess.client.location.LastLocationImpl;
import com.robotchad.chess.client.location.Location;
import com.robotchad.chess.client.location.LocationImpl;
import com.robotchad.chess.client.pieces.Piece;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /** The instance of the chess controller used for all game activities */
    private Controller controller;
    /** The 2D array of buttons making up the chess board in the GUI */
    private ImageButton[][] buttons;
    /** The location of the last clicked square */
    private LastLocation lastloc;
    /** The piece color of the player */
    public static Piece.Color PLAYERCOLOR = Piece.Color.WHITE;
    /** The piece color of the AI */
    public static Piece.Color AICOLOR = Piece.Color.BLACK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new ControllerImpl(); // Create new board instance
        buttons = new ImageButton[8][8];
        lastloc = new LastLocationImpl();
        setContentView((R.layout.chessboard));
        GridLayout ll = findViewById(R.id.boardLayout);
        //System.out.println(ll);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ImageButton myButton = new ImageButton(this);
                //myButton.addLocation(i,j);
                int imageID;
                if ((i + j) % 2 == 0) {
                    imageID = R.drawable.white_square;
                } else {
                    imageID = R.drawable.black_square;
                }
                myButton.setImageResource(imageID);
                myButton.setBackgroundColor(R.color.transparent);
                //myButton.setColorFilter(R.color.transparent);
                int finalI = i;
                int finalJ = j;
                myButton.setOnClickListener(new View.OnClickListener() {

                    /**
                     * The location of the space the button represents in the chess board
                     */
                    public final Location location = new LocationImpl(finalI, finalJ);
                    /**
                     * The controller used for the chess game
                     * NOTE: This is the same controller as the controller used in the top level
                     * class
                     */
                    public final Controller con = controller;

                    /**
                     * The location of the last clicked square
                     */
                    public final LastLocation lastLocation = lastloc;

                    // Main driver of buttons
                    @Override
                    public void onClick(View v) {
                        ImageButton b = (ImageButton) v;
                        // If last location exists and either square is empty or contains piece of
                        // Different color
                            // Attempt to make a move
                        // Else
                            // Attempt to select piece
                        if (lastLocation.isValid() && (con.squareInfo(location.getXAxis(),location.getYAxis()) == null || con.squareInfo(location.getXAxis(),location.getYAxis()).getColor() != PLAYERCOLOR)) {
                            // Check if move is valid and not same square
                            // If move is valid and not same square
                                // Make move
                                // Update graphics
                            // Else if piece same color as player
                                // Return
                            // Else
                                // Invalidate last location (deselect piece)
                            if (!lastLocation.equals(location) &&
                                    con.isValidMove(lastLocation.getLocation().getXAxis(),
                                    lastLocation.getLocation().getYAxis(), location.getXAxis(),
                                    location.getYAxis()).toBoolean()) {
                                    makeMove();

                            } else if (con.squareInfo(location.getXAxis(),
                                    location.getYAxis()).getColor() == PLAYERCOLOR) {
                                return;
                            } else {
                                lastLocation.invalidateLocation();
                            }
                        } else {
                            // Check if piece on space
                            // If piece on space
                                // Validate location
                                // Display moves
                            // Else
                                // Return
                            if (con.squareInfo(location.getXAxis(),location.getYAxis()) != null) {
                                lastLocation.validateLocation(location);
                                displayMoves();
                            }
                        }
                    }

                    /**
                     * Makes the player's move, redraws board, checks for end of game, moves the AI,
                     * and checks for end of game
                     */
                    private void makeMove() {
                        // Player move
                        con.move(lastLocation.getLocation().getXAxis(),
                                lastLocation.getLocation().getYAxis(),
                                location.getXAxis(), location.getYAxis());
                        redrawBoard();
                        // Check for end conditions
                        if (con.isStalemate(PLAYERCOLOR) || con.isStalemate(AICOLOR)) {
                            onStalemate();
                        } else if (con.isCheckmate(AICOLOR)) {
                            onWin(AICOLOR);
                            return;
                        } else if (con.isCheckmate(PLAYERCOLOR)) {
                            onWin(PLAYERCOLOR);
                            return;
                        }
                        // AI move
                        con.aiMove(AICOLOR);
                        redrawBoard();
                        // Check for end conditions
                        if (con.isStalemate(PLAYERCOLOR) || con.isStalemate(AICOLOR)) {
                            onStalemate();
                        } else if (con.isCheckmate(AICOLOR)) {
                            onWin(AICOLOR);
                            return;
                        } else if (con.isCheckmate(PLAYERCOLOR)) {
                            onWin(PLAYERCOLOR);
                            return;
                        }
                    }

                    /**
                     * The series of events taken if a winner is found after a move is taken
                     * TODO
                     * @param winner - the color of the winner
                     */
                    private void onWin(Piece.Color winner) {
                    }

                    /**
                     * The series of events taken if a stalemate is found after a move is taken
                     * TODO
                     */
                    private void onStalemate() {
                    }

                    /**
                     * Displays the list of valid moves for the piece located on this space
                     * TODO
                     */
                    private void displayMoves() {
                        ArrayList<LocationImpl> moves = con.validMoveList(location.getXAxis(),location.getYAxis());
                    }

                });
                buttons[i][j] = myButton;
                ll.addView(myButton);
                myButton.getLayoutParams().height = ll.getHeight() / 8;
                myButton.getLayoutParams().width = ll.getWidth() / 8;
            }
        }
        // redrawBoard();
    }

    /**
     * Redraws the bopard given the current state of the game
     */
    public void redrawBoard() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                Piece piece = controller.squareInfo(i,j);
                int imageID;
                if (piece == null) {
                    if ((i + j) % 2 == 0) {
                        imageID = R.drawable.white_square;
                    } else {
                        imageID = R.drawable.black_square;
                    }
                } else {
                    // TODO
                    imageID = R.drawable.placeholder;
                }
                buttons[i][j].setImageResource(imageID);
                buttons[i][j].setBackgroundColor(R.color.transparent);
            }
        }
    }
}