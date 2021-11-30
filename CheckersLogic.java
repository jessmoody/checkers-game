package core;

/**
 * Class that controls the logic of the Checkers game.
 * @author Jessica Moody
 * @version 2.0 (11/2/21)
 *
 */
public class CheckersLogic {
	/**
	 * Size of one edge of the board
	 */
	public static int BOARDSIZE = 8;
	/**
	 * Characters representing game pieces
	 */
	public static char OPIECE = 'o', XPIECE = 'x', BLANK = '_';
	/**
	 * Location of pieces on the starting board.
	 */
	public static int OSTART = 7, OEND = 5, XSTART = 2, XEND = 0;
	/**
	 * Amounts to shift from ASCII character numbers to the corresponding integer numbers.
	 */
	public static int CHARSHIFT = 97, NUMSHIFT = 49;
	/**
	 * Total number of pieces of each type.
	 */
	public static int TOTALPIECES = 12;
	/**
	 * Array representing the game board.
	 */
	private char[][] board;
	/**
	 * Number of "o" pieces captured.
	 */
	private int numOCaptured;
	/**
	 * Number of "x" pieces captured.
	 */
	private int numXCaptured;
	
	/**
	 * Constructor
	 */
	public CheckersLogic() {
		createBoard();
		numOCaptured = 0;
		numXCaptured = 0;
	}
	/**
	 * Method that sets up the Checkers board to start the game.
	 */
	private void createBoard() {
		board = new char[BOARDSIZE][BOARDSIZE];
		for (int i = board.length - 1; i >= 0; i--)
		{
			for (int j = 0; j < board.length; j++)
			{
				if(i >= OEND && ((i % 2 != 0 && j % 2 != 0) || (i % 2 == 0 && j % 2 == 0)))
					board[i][j] = OPIECE;
				else if(i <= XSTART && ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)))
					board[i][j] = XPIECE;
				else
					board[i][j] = BLANK;
			}
		}
	}
	/**
	 * Method that checks whether the user entered the game type correctly. "p" or "P" for player or "c" or "C"
	 * for computer.
	 * @param userOption
	 * @throws Exception
	 */
	public void verifyGameType(String userOption) throws Exception {
		if(!userOption.equals("p") && !userOption.equals("P") && !userOption.equals("c") && !userOption.equals("C"))
			throw new Exception();
	}
	
	/**
	 * Method that moves Checkers pieces across the board.
	 * @param userInput Piece location and destination the user entered.
	 * @param pieceType Either an "x" or "o" piece.
	 * @return board The checkers board.
	 */
	public char[][] movePiece(String userInput, char pieceType) {
		
		String moves = userInput;
		while (moves.length() > 2)
		{
			int row1 = moves.charAt(0) - NUMSHIFT;
			int col1 = moves.charAt(1) - CHARSHIFT;
			int row2 = moves.charAt(3) - NUMSHIFT;
			int col2 = moves.charAt(4) - CHARSHIFT;
			board[row1][col1] = BLANK;
			if(pieceType == XPIECE && row2 == row1 + 2 && board[row2][col2] == BLANK)
			{
				if(col2 == col1 + 2 && board[row1 + 1][col1 + 1] == OPIECE) {
					board[row1 + 1][col1 + 1] = BLANK;
					numOCaptured++;
				}
				if(col2 == col1 - 2 && board[row1 + 1][col1 - 1] == OPIECE) {			
					board[row1 + 1][col1 - 1] = BLANK;
					numOCaptured++;
				}
			}
			else if(pieceType == OPIECE && row2 == row1 - 2 && board[row2][col2] == BLANK)
			{
				if(col2 == col1 + 2 && board[row1 - 1][col1 + 1] == XPIECE) {
					board[row1 - 1][col1 + 1] = BLANK;
					numXCaptured++;
				}
				if(col2 == col1 - 2 && board[row1 - 1][col1 - 1] == XPIECE) {
					board[row1 - 1][col1 - 1] = BLANK;
					numXCaptured++;
				}
			}
			board[row2][col2] = pieceType;
			moves = moves.substring(3);
		}

		return board;
	}
	/**
	 * Method that verifies if the move user entered is valid.
	 * @param userInput Piece location and destination the user entered.
	 * @param pieceType Either an "x" or "o" piece.
	 */
	public void verifyMove(String userInput, char pieceType) throws Exception{
		boolean verify = true;
		if(userInput.equals("0"))
			verify = true;
		else
		{
			String moves = userInput;
			int row1 = moves.charAt(0) - NUMSHIFT;
			int col1 = moves.charAt(1) - CHARSHIFT;
			if(board[row1][col1] != pieceType)
				verify = false;
			while (moves.length() > 2)
			{
				row1 = moves.charAt(0) - NUMSHIFT;
				col1 = moves.charAt(1) - CHARSHIFT;
				int row2 = moves.charAt(3) - NUMSHIFT;
				int col2 = moves.charAt(4) - CHARSHIFT;
				if(row1 < 0 || row1 > BOARDSIZE - 1 || col1 < 0 || col1 > BOARDSIZE - 1)
					verify = false;
				if(row2 < 0 || row2 > BOARDSIZE - 1 || col2 < 0 || col2 > BOARDSIZE - 1)
					verify = false;
				if(pieceType == XPIECE && (row2 != row1 + 1 && row2 != row1 + 2))
					verify = false;
				if(pieceType == OPIECE && (row2 != row1 - 1 && row2 != row1 - 2))
					verify = false;
				if((col2 != col1 + 1 && col2 != col1 - 1) && (col2 != col1 + 2 && col2 != col1 - 2))
					verify = false;
				if(pieceType == XPIECE && row2 == row1 + 2)
				{
					if (board[row1 + 1][col1 + 1] != OPIECE && board[row1 + 1][col1 - 1] != OPIECE)
						verify = false;	
				}	
				if(pieceType == OPIECE && row2 == row1 - 2)
				{
					if (board[row1 - 1][col1 + 1] != XPIECE && board[row1 - 1][col1 - 1] != XPIECE)
						verify = false;
				}
				if(board[row2][col2] != BLANK)
					verify = false;
				moves = moves.substring(3);
			}
		}
		if(!verify)
			throw new Exception();
	}
	/**
	 * Method that checks whether or not the game has been won. First checks if there is space in front of an "x" 
	 * piece or if the "x" piece can jump, then does the same for the "o" pieces. If one of them cannot move then
	 * the other player wins the game. If all of one player's pieces have been captured, the other one wins the game.
	 * @return 0 to continue game, 1 if "x" wins, 2 if "o" wins and 3 if there is a tie.
	 */
	public int checkGameState() {
		boolean isWon = true;
		boolean OWon = true;
		boolean XWon = true;
		for (int i = board.length - 1; i >= 0 ; i--) {
			for(int j = 0; j < board.length; j++) {
				if (board[i][j] == XPIECE)
				{
					if(i != 7 && ((j != 7 && board[i + 1][j + 1] == BLANK) || (j != 0 && board[i + 1][j - 1] == BLANK)))
					{
						OWon = false;
					}
					else if(i < 6 && (j < 6 && board[i + 1][j + 1] == OPIECE) && board[i + 2][j + 2] == BLANK)
					{
						OWon = false;
					}
					else if(i < 6 && (j > 1 && board[i + 1][j - 1] == OPIECE) && board[i + 2][j - 2] == BLANK)
					{
						OWon = false;
					}
				}
				if (board[i][j] == OPIECE)
				{
					if(i != 0 && ((j != 7 && board[i - 1][j + 1] == BLANK) || (j != 0 && board[i - 1][j - 1] == BLANK)))
					{
						XWon = false;
					}
					else if(i > 1 && (j < 6 && board[i - 1][j + 1] == XPIECE) && board[i - 2][j + 2] == BLANK)
					{
						XWon = false;
					}
					else if(i > 1 && (j > 1 && board[i - 1][j - 1] == XPIECE) && board[i - 2][j - 2] == BLANK)
					{
						XWon = false;
					}
				}
				
				
			}
		}
		if (OWon && XWon)
			return 3;
		if (OWon)
			return 2;
		if (XWon)
			return 1;
		if(numOCaptured == TOTALPIECES && numXCaptured == TOTALPIECES)
			return 3;
		if(numOCaptured == TOTALPIECES)
			return 1;
		if(numXCaptured == TOTALPIECES)
			return 2;
		return 0;
	}
	/**
	 * Accessor method for board array.
	 * @return board The array representing the game board.
	 */
	public char[][] getBoard() {
		return board;
	}
	
	/**
	 * Mutator method for numOCaptured
	 * @param pNumOCaptured
	 */
	public void setNumOCaptured(int pNumOCaptured) {
		numOCaptured = pNumOCaptured;
	}
	/**
	 * Mutator method for numXCaptured
	 * @param pNumXCaptured
	 */
	public void setNumxCaptured(int pNumXCaptured) {
		numXCaptured = pNumXCaptured;
	}
	/**
	 * Accessor method for getNumOCaptured
	 * @return numOCaptured
	 */
	public int getNumOCaptured() {
		return numOCaptured;
	}
	/**
	 * Accessor method for getNumXCaptured
	 * @return numXCaptured
	 */
	public int getNumXCaptured() {
		return numXCaptured;
	}
}
