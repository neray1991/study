package battleShip;

	import java.util.Scanner;

	// TODO Auto-generated method stub
			/**
			*  A little (6x6?) Battleship game
			*
			*  @author Jim Nastos
			*/
			public class BattleShip {
			public static void main(String[] args) {
			final int SIZE = 6;
			Scanner in =new Scanner(System.in);
			
			int[][] board = {   
			{2,0,0,0,0,0},
			{2,0,0,0,3,0},
			{0,0,0,0,3,0},
			{0,0,0,0,3,0},
			{0,0,0,0,0,0},
			{0,0,4,4,4,4} };
			char[][] guesses = new char[SIZE][SIZE];
			boolean thereAreShips = true;
			//borad for players
			int count=0;
			while(thereAreShips) {
					displayGuesses(guesses);
				   //check if there are ships
				   for(int a = 0; a<SIZE; a++) {
					   for(int b=0; b<SIZE; b++) {
						   if(guesses[a][b]=='X') {
							   count++;
						   } 
					   }
				   }
				   
				   if(count>=9) {
					   thereAreShips = false;
				   }
				   
			// display the board
				   
			// ask user for their guess (e.g. "B5")
				   System.out.println("Please enter a guess in the form 'B5':");
				   String str = in.nextLine();
			// turn their guess string into a row index and column index
				   	int row = str.charAt(0)-65;
				   	int col = str.charAt(1)-49;
			// check the game board if it is a hit or a miss
				   	//System.out.println(row+","+col);
				   	
				   	int temp = board[row][col];
				   	
				   	boolean isSunk = false;
				   	if(board[row][col]>0) {
				   		System.out.println("hit");
				   		System.out.println(temp);
				   		guesses[row][col]='X';
				   		
				   		//check if sunk
				   		for(int i=0; i<SIZE; i++) {
				   			int j = 0;
				   			for(; j<SIZE; j++) {
				   				if(board[row][col] == board[i][j]) {
				   					if (guesses[i][j] != 'X') break;
				   				}
				   			}
				   			if (j != SIZE) break;
				   			if (i == SIZE-1) isSunk = true;
				   		}
				   		
				   		if(isSunk)
				   		{
				   			System.out.println("You sunk the Battleship " + temp);
				   			for (int i=0; i<SIZE; i++)
				   				for (int j=0; j<SIZE; j++)
				   					if (board[i][j] == temp) board[i][j] = 0;
				   		}
				   		
				   		
				   	}else {
				   		System.out.println("miss");
				   		guesses[row][col]='O';
				   	}
			// update the guesses board accordingly
				   	
			// decide if the ship is sunk
				   	
			// output message to user
			
			   }
			
			}
			


			public static void displayGuesses(char[][] g) {
			System.out.print(" ");
			for (int c=1; c<=g[0].length; c++ ) System.out.print(c);
			System.out.println();
			for (int row=0; row<g.length; row++) {
			System.out.print(""+(char)('A'+row));
			for (int col=0; col<g[row].length; col++) {
			if (g[row][col]=='O') System.out.print("O");
			else if (g[row][col]=='X') System.out.print("X");
			else System.out.print(".");
			}
			System.out.println();
			}
			}
			

			
		}
