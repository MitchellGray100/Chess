package controller;

import board.BoardImpl;

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
