package board;

public interface Board {
	
	/**
	 * 
	 * @param x The x coordinate to move to
	 * @param y The y coordinate to move to
	 */
	public void move(int x,int y,int r,int c);
	
	
	/**
	 * Determines whether the given move is a valid move for the pieces
	 * @param x The x coordinate of the pieces
	 * @param y The y coordinate of the pieces
	 * @param r The proposed x coordinate of the move
	 * @param c The proposed y coordinate of the move
	 * @return whether or not the move is valid
	 */
	public boolean isValidMove(int x, int y, int r, int c);
	
	
	/**
	 * Changes the score of the game whenever a pieces is taken
	 * @param r The x position of the pieces being taken
	 * @param c The y position of the pieces being taken
	 */
	public void changeScore(int r, int c);
}

