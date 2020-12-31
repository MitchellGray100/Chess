package Board;

public interface World {
	
	/**
	 * 
	 * @param x The x coordinate to move to
	 * @param y The y coordinate to move to
	 */
	public void move(int x, int y);
	
	
	/**
	 * Determines whether the given move is a valid move for the piece
	 * @param x The x coordinate of the piece
	 * @param y The y coordinate of the piece
	 * @param r The proposed x coordinate of the move
	 * @param c The proposed y coordinate of the move
	 * @return whether or not the move is valid
	 */
	public boolean isValidMove(int x, int y, int r, int c);
	
}

