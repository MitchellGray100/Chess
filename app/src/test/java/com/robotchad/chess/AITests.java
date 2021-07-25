package com.robotchad.chess;

import com.robotchad.chess.client.board.Board;
import com.robotchad.chess.client.board.BoardImpl;
import com.robotchad.chess.client.location.LocationImpl;
import com.robotchad.chess.client.pieces.Piece;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AITests {
    @Test
    public void isProtectTests()
    {
        Board chess = new BoardImpl();

        assertTrue(chess.locationToBoolean(chess.isProtect(1,0)));
        assertTrue(chess.locationToBoolean(chess.isProtect(1,1)));
        assertTrue(chess.locationToBoolean(chess.isProtect(1,2)));
        assertTrue(chess.locationToBoolean(chess.isProtect(1,3)));
        assertTrue(chess.locationToBoolean(chess.isProtect(1,4)));
        assertTrue(chess.locationToBoolean(chess.isProtect(1,5)));
        assertTrue(chess.locationToBoolean(chess.isProtect(1,6)));
        assertTrue(chess.locationToBoolean(chess.isProtect(1,7)));
        assertTrue(chess.locationToBoolean(chess.isProtect(0,1)));
        assertTrue(chess.locationToBoolean(chess.isProtect(0,2)));
        assertTrue(chess.locationToBoolean(chess.isProtect(0,3)));
        assertTrue(chess.locationToBoolean(chess.isProtect(0,4)));
        assertTrue(chess.locationToBoolean(chess.isProtect(0,5)));
        assertTrue(chess.locationToBoolean(chess.isProtect(0,6)));
        assertFalse(chess.locationToBoolean(chess.isProtect(0,0)));
        assertFalse(chess.locationToBoolean(chess.isProtect(0,7)));
        assertFalse(chess.locationToBoolean(chess.isProtect(7,0)));
        assertFalse(chess.locationToBoolean(chess.isProtect(7,7)));
        assertTrue(chess.locationToBoolean(chess.isProtect(7,1)));
        assertTrue(chess.locationToBoolean(chess.isProtect(7,2)));
        assertTrue(chess.locationToBoolean(chess.isProtect(7,3)));
        assertTrue(chess.locationToBoolean(chess.isProtect(7,4)));
        assertTrue(chess.locationToBoolean(chess.isProtect(7,5)));
        assertTrue(chess.locationToBoolean(chess.isProtect(7,6)));
        assertTrue(chess.locationToBoolean(chess.isProtect(6,0)));
        assertTrue(chess.locationToBoolean(chess.isProtect(6,1)));
        assertTrue(chess.locationToBoolean(chess.isProtect(6,2)));
        assertTrue(chess.locationToBoolean(chess.isProtect(6,3)));
        assertTrue(chess.locationToBoolean(chess.isProtect(6,4)));
        assertTrue(chess.locationToBoolean(chess.isProtect(6,5)));
        assertTrue(chess.locationToBoolean(chess.isProtect(6,6)));
        assertTrue(chess.locationToBoolean(chess.isProtect(6,7)));
    }

    @Test
    public void putsPieceInProtectionTests()
    {
        Board chess = new BoardImpl();
        assertTrue(chess.putsPieceInProtection(1,0,3,0));
        assertFalse(chess.putsPieceInProtection(1,1,3,1));
        assertFalse(chess.putsPieceInProtection(1,2,3,2));
        assertTrue(chess.putsPieceInProtection(1,3,3,3));
        assertFalse(chess.putsPieceInProtection(1,4,3,4));
        assertTrue(chess.putsPieceInProtection(1,5,2,5));
        assertFalse(chess.putsPieceInProtection(1,6,3,6));
        assertTrue(chess.putsPieceInProtection(1,7,3,7));
        assertTrue(chess.putsPieceInProtection(7,1,5,2));
        assertTrue(chess.putsPieceInProtection(7,6,5,7));
        assertTrue(chess.putsPieceInProtection(6,0,4,0));
        assertFalse(chess.putsPieceInProtection(6,1,4,1));
        assertFalse(chess.putsPieceInProtection(6,2,4,2));
        assertTrue(chess.putsPieceInProtection(6,3,4,3));
        assertFalse(chess.putsPieceInProtection(6,4,4,4));
        assertFalse(chess.putsPieceInProtection(6,5,4,5));
        assertFalse(chess.putsPieceInProtection(6,6,4,6));
        assertTrue(chess.putsPieceInProtection(6,7,4,7));

    }

    @Test
    public void putsOppositeKingInCheckTests()
    {
        Board chess = new BoardImpl();
        assertFalse(chess.putsOppositeKingInCheck(1,0,3,0));
        assertFalse(chess.putsOppositeKingInCheck(6,4,4,4));
        assertFalse(chess.putsOppositeKingInCheck(7,5,3,1));
        chess.forceMove(7,5,3,1);
        assertTrue(chess.putsOppositeKingInCheck(3,1,1,3));
    }

    @Test
    public void isProtectingTests()
    {
        Board chess = new BoardImpl();
        assertFalse(chess.isProtecting(6,0));
        assertFalse(chess.isProtecting(6,1));
        assertFalse(chess.isProtecting(6,2));
        assertFalse(chess.isProtecting(6,3));
        assertFalse(chess.isProtecting(6,4));
        assertFalse(chess.isProtecting(6,5));
        assertFalse(chess.isProtecting(6,6));
        assertFalse(chess.isProtecting(6,7));
        assertFalse(chess.isProtecting(1,0));
        assertFalse(chess.isProtecting(1,1));
        assertFalse(chess.isProtecting(1,2));
        assertFalse(chess.isProtecting(1,3));
        assertFalse(chess.isProtecting(1,4));
        assertFalse(chess.isProtecting(1,5));
        assertFalse(chess.isProtecting(1,6));
        assertFalse(chess.isProtecting(1,7));
        assertTrue(chess.isProtecting(0,0));
        assertTrue(chess.isProtecting(0,1));
        assertTrue(chess.isProtecting(0,2));
        assertTrue(chess.isProtecting(0,3));
        assertTrue(chess.isProtecting(0,4));
        assertTrue(chess.isProtecting(0,5));
        assertTrue(chess.isProtecting(0,6));
        assertTrue(chess.isProtecting(0,7));
        assertTrue(chess.isProtecting(7,0));
        assertTrue(chess.isProtecting(7,1));
        assertTrue(chess.isProtecting(7,2));
        assertTrue(chess.isProtecting(7,3));
        assertTrue(chess.isProtecting(7,4));
        assertTrue(chess.isProtecting(7,5));
        assertTrue(chess.isProtecting(7,6));
        assertTrue(chess.isProtecting(7,7));
    }

    @Test
    public void isValidProtectTests()
    {
        Board chess = new BoardImpl();
        assertFalse(chess.locationToBoolean(chess.isValidProtect(6,0,3,0)));
        assertTrue(chess.locationToBoolean(chess.isValidProtect(7,1,6,3)));
        assertTrue(chess.locationToBoolean(chess.isValidProtect(7,3,6,2)));
        assertTrue(chess.locationToBoolean(chess.isValidProtect(7,3,6,3)));
        assertTrue(chess.locationToBoolean(chess.isValidProtect(7,3,6,4)));
        assertTrue(chess.locationToBoolean(chess.isValidProtect(7,3,7,2)));
        assertTrue(chess.locationToBoolean(chess.isValidProtect(7,3,7,4)));
        assertFalse(chess.locationToBoolean(chess.isValidProtect(7,3,0,0)));
        assertFalse(chess.locationToBoolean(chess.isValidProtect(7,3,5,5)));
    }

    @Test
    public void aiMoveTests()
    {
        Board chess = new BoardImpl();
        LocationImpl[] returned = chess.aiMove(Piece.Color.WHITE);
        assertTrue(returned[0].getXAxis() >0);
        assertTrue(returned[0].getYAxis() >0);
        assertTrue(returned[1].getXAxis() >0);
        assertTrue(returned[1].getYAxis() >0);
        assertNull(chess.squareInfo(returned[1].getXAxis(),returned[1].getYAxis()));
        chess.move(returned[0].getXAxis(),returned[0].getYAxis(),returned[1].getXAxis(),returned[1].getYAxis());
        assertNull(chess.squareInfo(returned[0].getXAxis(),returned[0].getYAxis()));
        assertNotNull(chess.squareInfo(returned[1].getXAxis(),returned[1].getYAxis()));
        assertTrue(returned[1].getXAxis() == 3);
        assertTrue(returned[1].getYAxis() == 3);
        returned = chess.aiMove(Piece.Color.BLACK);
        assertTrue(returned[0].getXAxis() >0);
        assertTrue(returned[0].getYAxis() >0);
        assertTrue(returned[1].getXAxis() >0);
        assertTrue(returned[1].getYAxis() >0);
        assertNull(chess.squareInfo(returned[1].getXAxis(),returned[1].getYAxis()));
        chess.move(returned[0].getXAxis(),returned[0].getYAxis(),returned[1].getXAxis(),returned[1].getYAxis());
        assertNull(chess.squareInfo(returned[0].getXAxis(),returned[0].getYAxis()));
        assertNotNull(chess.squareInfo(returned[1].getXAxis(),returned[1].getYAxis()));
        assertTrue(returned[1].getXAxis() == 4);
        assertTrue(returned[1].getYAxis() == 3);
        returned = chess.aiMove(Piece.Color.WHITE);
        assertTrue(returned[0].getXAxis() >0);
        assertTrue(returned[0].getYAxis() >0);
        assertTrue(returned[1].getXAxis() >0);
        assertTrue(returned[1].getYAxis() >0);
        assertNull(chess.squareInfo(returned[1].getXAxis(),returned[1].getYAxis()));
        chess.move(returned[0].getXAxis(),returned[0].getYAxis(),returned[1].getXAxis(),returned[1].getYAxis());
        assertNull(chess.squareInfo(returned[0].getXAxis(),returned[0].getYAxis()));
        assertNotNull(chess.squareInfo(returned[1].getXAxis(),returned[1].getYAxis()));
        assertTrue(returned[1].getXAxis() == 2);
        assertTrue(returned[1].getYAxis() == 4);
    }

    @Test
    public void aiCheckmateMoveTests()
    {
        Board chess = new BoardImpl();
        chess.move(6,3,4,3);
        LocationImpl[] returned = chess.aiMove(Piece.Color.BLACK);
        assertTrue(returned[0].getXAxis() >0);
        assertTrue(returned[0].getYAxis() >0);
        assertTrue(returned[1].getXAxis() >0);
        assertTrue(returned[1].getYAxis() >0);
        assertNull(chess.squareInfo(returned[1].getXAxis(),returned[1].getYAxis()));
        chess.move(returned[0].getXAxis(),returned[0].getYAxis(),returned[1].getXAxis(),returned[1].getYAxis());
        assertNull(chess.squareInfo(returned[0].getXAxis(),returned[0].getYAxis()));
        assertNotNull(chess.squareInfo(returned[1].getXAxis(),returned[1].getYAxis()));
        assertTrue(returned[1].getXAxis() == 5);
        assertTrue(returned[1].getYAxis() == 4);
        chess.move(7,3,3,7);
        chess.move(7,5,4,2);
        returned = chess.aiMove(Piece.Color.BLACK);
        assertTrue(returned[0].getXAxis() >0);
        assertTrue(returned[0].getYAxis() >0);
        assertTrue(returned[1].getXAxis() >0);
        assertTrue(returned[1].getYAxis() >0);
        chess.move(returned[0].getXAxis(),returned[0].getYAxis(),returned[1].getXAxis(),returned[1].getYAxis());
        assertNull(chess.squareInfo(returned[0].getXAxis(),returned[0].getYAxis()));
        assertNotNull(chess.squareInfo(returned[1].getXAxis(),returned[1].getYAxis()));
        assertEquals(returned[1].getXAxis(),1);
        assertTrue(returned[1].getYAxis() == 5);


    }
}
