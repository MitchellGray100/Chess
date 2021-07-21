package com.robotchad.chess;

import com.robotchad.chess.client.board.Board;
import com.robotchad.chess.client.board.BoardImpl;
import com.robotchad.chess.client.pieces.Piece;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckTests {


    //IS CHECK TESTS
    @Test
    public void isCheckNullPieceTest()
    {
        Board chess = new BoardImpl();
        chess.move(6,2,4,2);
        chess.move(1,1,3,1);
        chess.move(3,1,4,2);
        chess.move(1,3,3,3);
        assertFalse(chess.locationToBoolean(chess.isCheck(3,3)));
    }

    @Test
    public void isCheckImplTest()
    {
        Board chess = new BoardImpl();
        assertFalse(chess.locationToBoolean(chess.isCheck(0,3)));
        assertFalse(chess.locationToBoolean(chess.isCheck(7,3)));
    }

    @Test
    public void isCheckKnightTest()
    {
        Board chess = new BoardImpl();
        chess.forceMove(0,1,5,3);
        chess.forceMove(7,1,2,3);
        assertTrue(chess.locationToBoolean(chess.isCheck(0,4)));
        assertTrue(chess.locationToBoolean(chess.isCheck(7,4)));
    }

    @Test
    public void isCheckNeighborKingsTest()
    {
        Board chess = new BoardImpl();
        chess.forceMove(0,4,3,3);
        chess.forceMove(7,4,3,4);
        assertTrue(chess.locationToBoolean(chess.isCheck(3,4)));
        assertTrue(chess.locationToBoolean(chess.isCheck(3,3)));
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
        assertFalse(chess.locationToBoolean(chess.isCheck(7,4)));
    }


    //KING IN CHECK TESTS
    @Test
    public void putsKingInCheckFalseTest()
    {
        Board chess = new BoardImpl();
        assertFalse(chess.putsKingInCheck(1,4,2,4));
    }

    @Test
    public void putsKingInCheckWithRookFalseTest()
    {
        Board chess = new BoardImpl();
        chess.forceMove(7,0,1,4);
        assertFalse(chess.putsKingInCheck(0,4,1,4));
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

    @Test
    public void putsKingInCheckKingMoveTest()
    {
        Board chess = new BoardImpl();
        chess.forceMove(1,4,5,5);
        assertFalse(chess.putsKingInCheck(0,4,1,4));
    }

    @Test
    public void putsKingInCheckSelfMove()
    {
        Board chess = new BoardImpl();
        for(int r = 0; r < 2;r++)
        {
            for(int c = 0; c < 8; c++)
            {
                if(r!=0 && c != 4)
                {
                    chess.deleteSquare(r,c);
                }
            }
        }
        chess.forceMove(7,0,5,3);
        chess.forceMove(7,7,5,5);
        chess.forceMove(7,2,5,0);
        assertTrue(chess.putsKingInCheck(0,4,0,3));
        assertTrue(chess.putsKingInCheck(0,4,1,3));
        assertTrue(chess.putsKingInCheck(0,4,1,4));
        assertTrue(chess.putsKingInCheck(0,4,1,5));
        assertTrue(chess.putsKingInCheck(0,4,0,5));
        assertFalse(chess.locationToBoolean(chess.isValidMove(0,4,0,1)));
        assertFalse(chess.putsKingInCheck(0,4,0,1));
    }


    //King move checkmate tests
    @Test
    public void whiteRookInCheckMate()
    {
        Board chess = new BoardImpl();
        chess.forceMove(7,0,1,4);
        assertFalse(chess.isCheckmateKingMove(Piece.Color.WHITE));
    }

    @Test
    public void blackRookInCheckMate()
    {
        Board chess = new BoardImpl();

        chess.forceMove(0,0,6,4);
        assertFalse(chess.isCheckmateKingMove(Piece.Color.BLACK));
    }


    //Piece attack checkmate tests
    @Test
    public void pieceAttackBishopInCheckMate()
    {
        Board chess = new BoardImpl();
        chess.forceMove(7,5,2,6);
        chess.forceMove(1,5,5,5);
        assertFalse(chess.isCheckmatePieceAttack(chess.isCheck(0,4).getXAxis(),chess.isCheck(0,4).getYAxis()));
    }

    @Test
    public void pieceAttackDoubleInCheckMate()
    {
        Board chess = new BoardImpl();
        chess.forceMove(7,3,3,4);
        chess.forceMove(1,4,5,5);
        chess.forceMove(7,6,2,5);
        assertTrue(chess.isCheckmatePieceAttack(chess.isCheck(0,4).getXAxis(),chess.isCheck(0,4).getYAxis()));
    }


    //Piece move checkmate tests
    @Test
    public void pieceMovementBlockBishop()
    {
        Board chess = new BoardImpl();
        chess.forceMove(1,5,5,5);
        chess.forceMove(7,2,3,7);
        assertFalse(chess.isCheckmatePieceMove(chess.isCheck(0,4).getXAxis(),chess.isCheck(0,4).getYAxis()));
    }
    @Test
    public void pieceMovementKnightBlockBishop()
    {
        Board chess = new BoardImpl();
        chess.forceMove(1,5,5,5);
        chess.forceMove(7,2,3,7);
        chess.forceMove(1,6,5,6);
        chess.forceMove(0,6,3,4);
        assertFalse(chess.isCheckmatePieceMove(chess.isCheck(0,4).getXAxis(),chess.isCheck(0,4).getYAxis()));
    }


    //Checkmate tests
    @Test
    public void wRookInCheckMate()
    {
        Board chess = new BoardImpl();
        chess.forceMove(7,0,1,4);
        assertFalse(chess.isCheckmate(chess.isCheck(0,4).getXAxis(),chess.isCheck(0,4).getYAxis()));
    }

    @Test
    public void bRookInCheckMate()
    {
        Board chess = new BoardImpl();

        chess.forceMove(0,0,6,4);
        assertFalse(chess.isCheckmate(chess.isCheck(7,4).getXAxis(),chess.isCheck(7,4).getYAxis()));
    }

    @Test
    public void BishopInCheckMate()
    {
        Board chess = new BoardImpl();
        chess.forceMove(7,5,2,6);
        chess.forceMove(1,5,5,5);
        assertFalse(chess.isCheckmate(chess.isCheck(0,4).getXAxis(),chess.isCheck(0,4).getYAxis()));
    }

    @Test
    public void DoubleInCheckMate()
    {
        Board chess = new BoardImpl();
        chess.forceMove(7,3,3,4);
        chess.forceMove(1,4,5,5);
        chess.forceMove(7,6,2,5);
        assertTrue(chess.isCheckmate(chess.isCheck(0,4).getXAxis(),chess.isCheck(0,4).getYAxis()));
    }

    @Test
    public void CheckmateBlockBishop()
    {
        Board chess = new BoardImpl();
        chess.forceMove(1,5,5,5);
        chess.forceMove(7,2,3,7);
        assertFalse(chess.isCheckmate(chess.isCheck(0,4).getXAxis(),chess.isCheck(0,4).getYAxis()));
    }

    //STALEMATE tests
    @Test
    public void StalemateFalseTest()
    {
        Board chess = new BoardImpl();
        assertFalse(chess.isStalemate(Piece.Color.WHITE));
        assertFalse(chess.isStalemate(Piece.Color.BLACK));
    }
    @Test
    public void StalemateTrueTest()
    {
        Board chess = new BoardImpl();
        for(int r = 0; r < 2;r++)
        {
            for(int c = 0; c < 8; c++)
            {
                if(r==0 && c == 4)
                {

                }
                else
                {
                    chess.deleteSquare(r,c);
                }
            }
        }
//        chess.deleteSquare(0,0);
//        chess.deleteSquare(0,1);
//        chess.deleteSquare(0,2);
//        chess.deleteSquare(0,3);
//        chess.deleteSquare(0,5);
//        chess.deleteSquare(0,6);
//        chess.deleteSquare(0,7);
//        chess.deleteSquare(1,0);
//        chess.deleteSquare(1,1);
//        chess.deleteSquare(1,2);
//        chess.deleteSquare(1,3);
//        chess.deleteSquare(1,4);
//        chess.deleteSquare(1,5);
//        chess.deleteSquare(1,6);
//        chess.deleteSquare(1,7);
        chess.forceMove(7,0,5,3);
        chess.forceMove(7,7,5,5);
        chess.forceMove(7,2,5,0);
        assertTrue(chess.isStalemate(Piece.Color.WHITE));
        assertFalse(chess.isStalemate(Piece.Color.BLACK));
    }
}
