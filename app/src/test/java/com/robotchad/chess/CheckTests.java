package com.robotchad.chess;

import com.robotchad.chess.client.board.Board;
import com.robotchad.chess.client.board.BoardImpl;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckTests {


    //IS CHECK TESTS
    @Test
    public void isCheckImplTest()
    {
        Board chess = new BoardImpl();
        assertFalse(chess.isCheck(0,3));
        assertFalse(chess.isCheck(7,3));
    }

    @Test
    public void isCheckKnightTest()
    {
        Board chess = new BoardImpl();
        chess.forceMove(0,1,5,3);
        chess.forceMove(7,1,2,3);
        assertTrue(chess.isCheck(0,4));
        assertTrue(chess.isCheck(7,4));
    }

    @Test
    public void isCheckNeighborKingsTest()
    {
        Board chess = new BoardImpl();
        chess.forceMove(0,4,3,3);
        chess.forceMove(7,4,3,4);
        assertTrue(chess.isCheck(3,4));
        assertTrue(chess.isCheck(3,3));
    }

    @Test
    public void isCheckNoneCheckedTest()
    {
        Board chess = new BoardImpl();
        for(int r = 0; r < 7; r++)
        {
            for(int c = 0; c < 7; c++)
            {
                if(chess.squareInfo(r,c) != null)
                {
                    chess.isCheck(r,c);
                }
            }
        }
    }

    @Test
    public void isCheckObstacleTest()
    {
        Board chess = new BoardImpl();
        chess.forceMove(0,3,0,4);
        assertFalse(chess.isCheck(7,4));
    }


    //KING IN CHECK TESTS
    @Test
    public void putsKingInCheckFalseTest()
    {
        Board chess = new BoardImpl();
        assertFalse(chess.putsKingInCheck(1,4,2,4));
    }

    @Test
    public void putsWhiteKingInCheckTrueTest()
    {
        Board chess = new BoardImpl();
        chess.forceMove(0,4,4,6);
        chess.deleteSquare(6,4);
        chess.forceMove(0,2,5,5);
        assertTrue(chess.putsKingInCheck(5,5,5,4));
    }

    @Test
    public void putsBlackKingInCheckTrueTest()
    {
        Board chess = new BoardImpl();
        chess.forceMove(7,4,3,0);
        chess.deleteSquare(1,2);
        chess.forceMove(7,2,2,1);
        assertTrue(chess.putsKingInCheck(2,1,3,2));
    }
}