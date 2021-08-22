package com.robotchad.chess.gui;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new ControllerImpl(); // Create new board instance
        buttons = new ImageButton[8][8];
        setContentView((R.layout.chessboard));
        GridLayout ll = findViewById(R.id.boardLayout);
        //System.out.println(ll);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ImageButton myButton = new ImageButton(this);
                int imageID;
                if ((i + j) % 2 == 0) {
                    imageID = R.drawable.white_square;
                } else {
                    imageID = R.drawable.black_square;
                }
                myButton.setImageResource(imageID);
                myButton.setBackgroundColor(R.color.transparent);
                //myButton.setColorFilter(R.color.transparent);
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