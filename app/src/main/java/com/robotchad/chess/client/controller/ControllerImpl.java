package com.robotchad.chess.client.controller;

import com.robotchad.chess.client.board.BoardImpl;

/** Implementation of a controller for the chess game */
public class ControllerImpl implements Controller {
	/** The representation of the chess board */
	BoardImpl board;
	
	/** 
	 * Creates a new Controller instance
	 */
	public ControllerImpl() {
		board = new BoardImpl();
	}
}
