package core;
import ui.CheckersTextConsole;

/**
 * Class that controls the computer player.
 * @author Jessica Moody
 * @version 1.0 (11/2/21)
 *
 */
public class CheckersComputerPlayer extends CheckersLogic{
	/**
	 * Method to create the computer's move.
	 * @param mBoard The board from the current game.
	 * @return computerInput The computer's move.
	 */
	public String computerInput(char[][] mBoard) {
		String source = selectPiece(mBoard);
		String dest = selectMove(source, mBoard);
		String computerInput = source + "-" + dest;
		return computerInput;
	}
	
	/**
	 * Method to generate the computer's choice of piece to move.
	 * @param mBoard The board from the current game.
	 * @return selectedPiece The piece the computer selected to move.
	 */
	public String selectPiece(char[][] mBoard) {
		String selectedPiece = "";
		int pieceNum = 0;
		int row = -1, col = -1;
		int k = 0;
		CheckersSpaces[] spaces = new CheckersSpaces[TOTALPIECES];
		for(int i = 0; i < spaces.length; i++)
		{
			spaces[i] = new CheckersSpaces();
		}
		for(int i = BOARDSIZE - 1; i >= 0; i--)
		{
			for(int j = 0; j < BOARDSIZE; j++)
			{
				if(mBoard[i][j] == OPIECE)
				{
					if(i > 0 &&  ((j > 0 && mBoard[i - 1][j - 1] == BLANK) || (j < BOARDSIZE - 1 && mBoard[i - 1][j + 1] == BLANK)))
					{
						spaces[k].setRow(i);
						spaces[k].setCol(j);
						k++;
					}
					else if (i > 1 && ((j > 1 && mBoard[i - 1][j - 1] == XPIECE && mBoard[i - 2][j - 2] == BLANK) || (j < BOARDSIZE - 2 && mBoard[i - 1][j + 1] == XPIECE && mBoard[i - 2][j + 2] == BLANK)))	
					{
						spaces[k].setRow(i);
						spaces[k].setCol(j);
						k++;
					}
				}
			}
		}
		int random = (int)(Math.random() * k);
		row = spaces[random].getRow();
		col = spaces[random].getCol();
		selectedPiece = convertToSpace(row, col);
		return selectedPiece;
	}
	
	/**
	 * Method to generate the computer's choice of piece to move.
	 * @param location The location of the piece the computer is going to move.
	 * @param mBoard The board from the current game.
	 * @return selectedLocation The location the computer will move the piece chosen.
	 */
	public String selectMove(String location, char[][] mBoard) {
		String selectedLocation = "";
		boolean leftFree = false, rightFree = false;
		boolean jumpLeft = false, jumpRight = false;
		boolean goLeft = false, goRight = false;
		int newRow = -1, newCol = -1;
		int a = 0, b = 0, c = 0, d = 0, f = 0, g = 0;
		int row = location.charAt(0) - NUMSHIFT;
		int col = location.charAt(1) - CHARSHIFT;

		if(row > 0 && col > 0 && mBoard[row - 1][col - 1] == BLANK)
		{
			leftFree = true;
			a = row - 1;
			b = col - 1;
		}
		else if (row > 1 && col > 1 && mBoard[row - 1][col - 1] == XPIECE && mBoard[row - 2][col - 2] == BLANK)
		{
			leftFree = true;
			jumpLeft = true;
			a = row - 2;
			b = col - 2;
		}
		if(row > 0 && col < BOARDSIZE - 1 && mBoard[row - 1][col + 1] == BLANK)
		{
			rightFree = true;
			c = row - 1;
			d = col + 1;
		}
		else if (row > 1 && col < BOARDSIZE - 2 && mBoard[row - 1][col + 1] == XPIECE && mBoard[row - 2][col + 2] == BLANK)
		{
			rightFree = true;
			jumpRight = true;
			c = row - 2;
			d = col + 2;
		}
		if (leftFree && rightFree)
		{
			int randomLocation = (int)(Math.random() * 2 + 1);
			if(randomLocation == 1) 
			{
				newRow = a;
				newCol = b;
			}
			else 
			{
				newRow = c;
				newCol = d;
			}
		}
		else if (leftFree)
		{
			newRow = a;
			newCol = b;
		}
		else if (rightFree)
		{
			newRow = c;
			newCol = d;
		}
		selectedLocation = convertToSpace(newRow, newCol);
		return selectedLocation;
	}
	
	/**
	 * Method to convert row/column indexes to a space on the board.
	 * @param row Row index.
	 * @param col Column index.
	 * @return space The space on the board.
	 */
	public String convertToSpace(int row, int col) {
		char rowChar = (char)(row + NUMSHIFT);
		char colChar = (char)(col + CHARSHIFT);
		String rowString = Character.toString(rowChar);
		String colString = Character.toString(colChar);
		String space = rowString + colString;
		return space;
	}
}
