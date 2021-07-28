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

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /** The instance of the board used for all game activities */
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        board = new BoardImpl(); // Create new board instance
        setContentView((R.layout.chessboard));
        RelativeLayout ll = findViewById(R.id.mainLayout);
        //System.out.println(ll);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        Button myButton = new Button(this);
        myButton.setText("Push Me");
        ll.addView(myButton, lp);
        //myButton.bringToFront();
        //findViewById(R.id.boardImageView).
    }
}