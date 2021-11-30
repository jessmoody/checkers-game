package ui;
import core.CheckersComputerPlayer;
import core.CheckersLogic;
import java.util.Scanner;

/**
 * Class that interacts with the user.
 * @author Jessica Moody
 * @version 2.0 (11/2/21)
 */
public class CheckersTextConsole {

	/**
	 * Main method - Controls interaction with user.
	 * @param args
	 */
	public static void main(String[] args) {
		String userOption = "";
		boolean xTurn = true;
		String input = "0";
		boolean verify = false;
		char pieceType;
		int gameState = 0;
		boolean computerPlayer = false;
		boolean verifyGame = false;
		Scanner scan = new Scanner(System.in);
		CheckersLogic game = new CheckersLogic();
		CheckersComputerPlayer comp = new CheckersComputerPlayer();
		System.out.println("Hello, Welcome to Checkers");
		while (!verifyGame)
		{
			System.out.print("Enter ‘p’ if you want to play against another player; enter ‘C’ to play against computer: ");
			userOption = scan.next();
			try {
				game.verifyGameType(userOption);
				verifyGame = true;
			}
			catch (Exception ex){
				System.out.print("Invalid input. ");
			}
		}
		drawBoard(game.getBoard());
		if(userOption.equals("c") || userOption.equals("C"))
		{
			computerPlayer = true;
			System.out.print("Start game against computer. \r\n"
					+ "You are Player X. It is your turn. \r\n");
		}
		while (gameState == 0) {
			
			verify = false;
			if(xTurn)
			{
				System.out.println("Player x's turn.");
				pieceType = 'x';
				xTurn = false;
			}	
			else
			{
				System.out.println("Player o's turn.");
				pieceType = 'o';
				xTurn = true;
			}	
			if(computerPlayer && pieceType == 'o')
			{
				try {
					input = comp.computerInput(game.getBoard());
					game.verifyMove(input, pieceType);
				}
				catch (Exception ex) {
					System.out.print(input + " is an invalid move. ");
					System.exit(100);	
				}
			}
			else
			{
				while (!verify) {
					try {
						System.out.print("Please choose a place to move by indicating the cell# "
								+ "followed by the new position. eg. 3a-4b. To end game, enter 0: ");
						input = scan.next();
						game.verifyMove(input, pieceType);
						verify = true;
					}
					catch (Exception ex){
						System.out.print("Invalid move. ");
					}
					if (input.equals("0"))
						System.exit(100);	
				}
			}
			
			game.movePiece(input, pieceType);
			gameState = game.checkGameState();
			drawBoard(game.getBoard());
			
			if(gameState == 1)
			{
				System.out.println("Player x wins!");
			}
			if(gameState == 2)
			{
				System.out.println("Player o wins!");
			}
			if(gameState == 3)
			{
				System.out.println("It's a tie!");
			}
		}

	}
	
	/**
	 * Function that draws the board on the console.
	 * @param board The array representing the game board
	 */
	public static void drawBoard(char[][] board) {
		for (int i = board.length - 1; i >= 0; i--)
		{
			System.out.print((i + 1) + " |");
			for (int j = 0; j < board.length; j++)
			{
				System.out.print(" "+ board[i][j] + " |" );
			}
			System.out.println();
		}
		System.out.println("    a   b   c   d   e   f   g   h");
	}

}
