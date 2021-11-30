package core;
/**
 * Class for the checkers board spaces.
 * @author jessm
 * @version 1.0 (11/2/21)
 */
public class CheckersSpaces {
	/**
	 * Row number
	 */
	private int row;
	/**
	 * Column number
	 */
	private int col;
	
	/**
	 * Constructor - sets row and column to 0.
	 */
	public CheckersSpaces()
	{
		setRow(0);
		setCol(0);
	}
	/**
	 * Mutator method for the row
	 * @param pRow The row where the designated space is located.
	 */
	public void setRow(int pRow)
	{
		row = pRow;
	}
	/**
	 * Mutator method for the column
	 * @param pCol The column where the designated space is located.
	 */
	public void setCol(int pCol)
	{
		col = pCol;
	}
	/**
	 * Accessor method for the row
	 * @return The row number
	 */
	public int getRow()
	{
		return row;
	}
	/**
	 * Accessor method for the column
	 * @return The column number
	 */
	public int getCol()
	{
		return col;
	}
}
