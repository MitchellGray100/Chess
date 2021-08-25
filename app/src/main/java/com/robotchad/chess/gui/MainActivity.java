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

                    /** The location of the space the button represents in the chess board */
                    public final Location location = new LocationImpl(finalI, finalJ);
                    /** The controller used for the chess game
                     * NOTE: This is the same controller as the controller used in the top level
                     * class */
                    public final Controller con = controller;

                    /** The location of the last clicked square */
                    public final LastLocation lastLocation = lastloc;

                    // Main driver of buttons
                    @Override
                    public void onClick (View v) {
                        ImageButton b = (ImageButton) v;
                    }

                    /**
                     * The series of events taken if a winner is found after a move is taken
                     * @param winner - the color of the winner
                     */
                    private void onWin (Piece.Color winner) {}

                    /**
                     * The series of events taken if a stalemate is found after a move is taken
                     */
                    private void onStalemate() {}

                    /**
                     * Displays the list of valid moves for the piece located on this space
                     */
                    private void displayMoves() {}

                    /**
                     * Attempts to move the piece found on this space to the given new space and
                     * returns if successful
                     * @param newLoc - new location of the piece
                     * @return true if the move was successful, false otherwise
                     */
                    private boolean makeMove(Location newLoc) {
                        return false;
                    }

                });
                buttons[i][j] = myButton;
                ll.addView(myButton);
                myButton.getLayoutParams().height = ll.getHeight() / 8 - 2;
                myButton.getLayoutParams().width = ll.getWidth() / 8 - 2;
            }
        }
        //myButton.bringToFront();
        //findViewById(R.id.boardImageView).
    }
}