package is.placeholder.tictactoe;

public class TicTacToe {
	public boolean computer;
	public boolean difficulty;
	public boolean humanTurn;
	public char grid [][];
	private TicTacAI AI;
	//An initiatior for tictactoe against human player
	public TicTacToe(boolean player){
		computer = player;
		humanTurn = true;
		makeGrid();

	}
	public boolean getWhoToPlay(){
		return humanTurn;
	}
	//An initiatior for tictactoe against computer
	public TicTacToe(boolean player, boolean difficult){
		computer = player;
		difficulty = difficult;
		makeGrid();
	}
	public char[][] getGrid(){
		return grid;
	}
	public void setGrid( char [][] inputGrid){
		grid = inputGrid;
		;
	}
    //Makes a empty tictactoe table
	public void makeGrid(){
		grid = new char[3][3];
		for(int i = 0;i<3;i++){
			for(int j = 0;j<3;j++){
				grid[i][j]=' ';
			}
		}
		AI = new TicTacAI('O' ,difficulty);
	}

	public char[][] humanPlayerMove (int x, int y){
		
		if(grid[x][y] != ' '){
			humanTurn = true;
		}
		else{
			grid[x][y] = 'X';
			humanTurn = false;
		}
		return grid;
	}

	public int [] computerMove (char [][] inputGrid)
	{
		char[][] previousGrid = new char[3][3]; 
		for (int i = 0; i < 3; i++){
			for (int j = 0 ; j < 3 ; j++){
				previousGrid[i][j] = inputGrid[i][j];
			}
		}
		inputGrid = AI.getMove(inputGrid);
		if (inputGrid == null) return null;
		int [] returnValue = new int [2];
		for(int i = 0;i<3;i++){
			for(int j = 0;j<3;j++){
				if (previousGrid[i][j] != grid[i][j]){
					returnValue[0] = i;
					returnValue[1] = j;
					return returnValue;
				}
			}
		}
		return returnValue;
	}

	//A test that searches through the grid and
	//cheacks if the player or computer has won
	//and a draw has occurred. 0 = Game not over, 1 = draw
	// 2 = player has won, 3 = computer has won
	public int hasWon(){
		
		return 2;
	}


	public int getPlayerScore() {
		return 1;
	}

	public int getComputerScore() {
		return 2;
	}

	public int getTieScore() {
		return 3;
	}

}