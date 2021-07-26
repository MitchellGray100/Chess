package com.robotchad.chess;

import com.robotchad.chess.client.board.Board;
import com.robotchad.chess.client.board.BoardImpl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoringTests {
    @Test
    public void scoringWhitePawnTest() {
        Board chess = new BoardImpl();
        chess.changeScore(1, 0);
        assertEquals(0, chess.getWhitePoints());
        assertEquals(1, chess.getBlackPoints());
    }

    @Test
    public void scoringBlackPawnTest() {
        Board chess = new BoardImpl();
        chess.changeScore(6, 0);
        assertEquals(1, chess.getWhitePoints());
        assertEquals(0, chess.getBlackPoints());
    }

    @Test
    public void scoringWhiteKnightTest() {
        Board chess = new BoardImpl();
        chess.changeScore(0, 1);
        assertEquals(0, chess.getWhitePoints());
        assertEquals(3, chess.getBlackPoints());
    }

    @Test
    public void scoringBlackKnightTest() {
        Board chess = new BoardImpl();
        chess.changeScore(7, 1);
        assertEquals(3, chess.getWhitePoints());
        assertEquals(0, chess.getBlackPoints());
    }

    @Test
    public void scoringWhiteBishopTest() {
        Board chess = new BoardImpl();
        chess.changeScore(0, 2);
        assertEquals(0, chess.getWhitePoints());
        assertEquals(3, chess.getBlackPoints());
    }

    @Test
    public void scoringBlackBishopTest() {
        Board chess = new BoardImpl();
        chess.changeScore(7, 2);
        assertEquals(3, chess.getWhitePoints());
        assertEquals(0, chess.getBlackPoints());
    }

    @Test
    public void scoringWhiteRookTest() {
        Board chess = new BoardImpl();
        chess.changeScore(0, 0);
        assertEquals(0, chess.getWhitePoints());
        assertEquals(5, chess.getBlackPoints());
    }

    @Test
    public void scoringBlackRookTest() {
        Board chess = new BoardImpl();
        chess.changeScore(7, 0);
        assertEquals(5, chess.getWhitePoints());
        assertEquals(0, chess.getBlackPoints());
    }

    @Test
    public void scoringWhiteQueenTest() {
        Board chess = new BoardImpl();
        chess.changeScore(0, 3);
        assertEquals(0, chess.getWhitePoints());
        assertEquals(9, chess.getBlackPoints());
    }

    @Test
    public void scoringBlacQueenTest() {
        Board chess = new BoardImpl();
        chess.changeScore(7, 3);
        assertEquals(9, chess.getWhitePoints());
        assertEquals(0, chess.getBlackPoints());
    }

    @Test
    public void scoringWhiteKingTest() {
        Board chess = new BoardImpl();
        chess.changeScore(0, 4);
        assertEquals(0, chess.getWhitePoints());
        assertEquals(100, chess.getBlackPoints());
    }

    @Test
    public void scoringBlackKingTest() {
        Board chess = new BoardImpl();
        chess.changeScore(7, 4);
        assertEquals(100, chess.getWhitePoints());
        assertEquals(0, chess.getBlackPoints());
    }

    @Test
    public void scoringTwoWhitePieces() {
        Board chess = new BoardImpl();
        chess.changeScore(0, 0);
        chess.changeScore(0, 1);
        assertEquals(0, chess.getWhitePoints());
        assertEquals(8, chess.getBlackPoints());
    }

    @Test
    public void scoringTwoBlackPieces() {
        Board chess = new BoardImpl();
        chess.changeScore(7, 0);
        chess.changeScore(7, 1);
        assertEquals(8, chess.getWhitePoints());
        assertEquals(0, chess.getBlackPoints());
    }

    @Test
    public void scoringOneOfEachPieces() {
        Board chess = new BoardImpl();
        chess.changeScore(0, 0);
        chess.changeScore(7, 0);
        assertEquals(0, chess.getWhitePoints());
        assertEquals(0, chess.getBlackPoints());
    }

    @Test
    public void scoringTwoWhiteOneBlackPieces() {
        Board chess = new BoardImpl();
        chess.changeScore(0, 0);
        chess.changeScore(0, 1);
        chess.changeScore(7, 0);
        assertEquals(0, chess.getWhitePoints());
        assertEquals(3, chess.getBlackPoints());
    }

    @Test
    public void scoringTwoBlackOneWhitePieces() {
        Board chess = new BoardImpl();
        chess.changeScore(0, 0);
        chess.changeScore(7, 0);
        chess.changeScore(7, 1);
        assertEquals(3, chess.getWhitePoints());
        assertEquals(0, chess.getBlackPoints());
    }

    @Test
    public void scoringAllPieces() {
        Board chess = new BoardImpl();
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 8; c++) {
                chess.changeScore(r, c);
            }
        }
        for (int r = 6; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                chess.changeScore(r, c);
            }
        }
        assertEquals(0, chess.getWhitePoints());
        assertEquals(0, chess.getBlackPoints());
    }

    @Test
    public void scoringAllWhitePieces() {
        Board chess = new BoardImpl();
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 8; c++) {
                chess.changeScore(r, c);
            }
        }
        assertEquals(0, chess.getWhitePoints());
        assertEquals(139, chess.getBlackPoints());
    }

    @Test
    public void scoringAllBlackPieces() {
        Board chess = new BoardImpl();
        for (int r = 6; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                chess.changeScore(r, c);
            }
        }
        assertEquals(139, chess.getWhitePoints());
        assertEquals(0, chess.getBlackPoints());
    }
}
