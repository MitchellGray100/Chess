package Board;

public interface World {
	
	/**
	 * 
	 * @param x The x coordinate to move to
	 * @param y The y coordinate to move to
	 */
	public void move(int x,int y,int r,int c);
	
	
	/**
	 * Determines whether the given move is a valid move for the piece
	 * @param x The x coordinate of the piece
	 * @param y The y coordinate of the piece
	 * @param r The proposed x coordinate of the move
	 * @param c The proposed y coordinate of the move
	 * @return whether or not the move is valid
	 */
	public boolean isValidMove(int x, int y, int r, int c);
	
	
	/**
	 * Changes the score of the game whenever a piece is taken
	 * @param r The x position of the piece being taken
	 * @param c The y position of the piece being taken
	 */
	public void changeScore(int r, int c);
}

