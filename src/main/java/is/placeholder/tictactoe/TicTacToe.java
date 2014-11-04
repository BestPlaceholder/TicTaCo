package is.placeholder.tictactoe;

public class TicTacToe {
	public boolean computer;
	public boolean difficulty;
	public boolean humanTurn;
	public char grid [][];
	private TicTacAI AI;
	private int playerScore;
	private int computerScore;
	private int tieScore;
	private boolean gameHasBeenWon;
	
	/**
	*	Constructor for human vs human
	*
	*	@param player decides player state
	*/
	public TicTacToe(boolean player){
		computer = player;
		humanTurn = true;
		makeGrid();

	}
	/**
	*	Returns which players turn it is.
	*
	*	@return A boolean value for which players turn is now.
	*/
	public boolean getWhoToPlay(){
		return humanTurn;
	}
	/**
	* 	Constructor for human vs AI
	*
	*	@param player decides player state.
	*	@param difficult decides AI difficulty.
	*/
	public TicTacToe(boolean player, boolean difficult){
		computer = player;
		difficulty = difficult;
		humanTurn = true;
		playerScore = 0;
		computerScore = 0;
		tieScore = 0;
		gameHasBeenWon = false;
		makeGrid();
	}

	/**
	* 	A function for getting the play grid
	*
	*	@return grid a 3x3 two dimensional char array that represents the player grid
	*/
	public char[][] getGrid(){
		return grid;
	}

	/**
	* 	A function for setting the playgrid
	*/
	public void setGrid( char [][] inputGrid){
		grid = inputGrid;
	}

	/**
	*	A function for reseting the play board
	*/
	public void makeGrid(){
		grid = new char[3][3];
		for(int i = 0;i<3;i++){
			for(int j = 0;j<3;j++){
				grid[i][j]=' ';
			}
		}
		AI = new TicTacAI('O' ,difficulty);
		humanTurn = true;
		gameHasBeenWon = false;
	}

	/**
	*	A function for taking in the human players moves and applying them ot the grid
	*	@param x The X coordinates.
	*	@param y The Y coordinates.
	*	@return grid The changed grid
	*/
	public char[][] humanPlayerMove (int x, int y){
		if(grid[x][y] != ' ' || !humanTurn){
			return null;
		}
		else{
			grid[x][y] = 'X';
			humanTurn = false;
			return grid;
		}
	}

	/**
	*	A function that queries the AI for a move and turns it into numeric coordinates.
	*
	*	@param inputGrid the grid to pass on to the AI
	* 	@return intArray an array with the x-y coordinates		
	*/
	public int [] computerMove (char [][] inputGrid)
	{
		hasWon();
		if (gameHasBeenWon) return null;
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
					humanTurn = true;
					return returnValue;
				}
			}
		}
		humanTurn = true;
		return returnValue;
	}

	/**
	*	A function for calculating the game score
	*
	*	@param gameEnd The end state of the game
	*/
	public void calculateScores(int gameEnd) {
		if(gameEnd == 2){
			playerScore++;
		}
		else if(gameEnd == 3){
			computerScore++;
		}
		else if (gameEnd == 1){
			tieScore++;
		}
	}

	//A test that searches through the grid and
	//cheacks if the player or computer has won
	//and a draw has occurred. 0 = Game not over, 1 = draw
	// 2 = player has won, 3 = computer has won
	/**
	*	A function for checking if either player has won the game.
	*
	*	@return gameEnd The win state of the game.
	*/
	public int hasWon(){
		char hasWon = ' ';
		hasWon = checkSideWay();
		if(hasWon == ' '){
			hasWon = checkUpDown();
		}
		if(hasWon == ' '){
			hasWon = checkSideWay();
		}
		if(hasWon == ' '){
			hasWon = checkCross();
		}
		int count = gridCounter();
		if(hasWon == 'X'){
			gameHasBeenWon = true;
			return 2;
		}
		if(hasWon == 'O'){
			gameHasBeenWon = true;
			return 3;
		}
		if (count == 9){
			gameHasBeenWon = true;
			return 1;
		}
			return 0;
		
	}


		private char checkCross() {
		if(grid[0][0] != ' ' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]){
			return grid[0][0];
		}
		if(grid[0][2] != ' ' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]){
			return grid[0][2];
		}

		return ' ';
	}

	/**
	*	A function for checking if someone has won the game
	*
	*	@return The win state of the game
	*/
	public boolean hasGameBeenWon() {
		return gameHasBeenWon;
	}

	private char checkUpDown() {
		if(grid[0][0] != ' ' && grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0]){
			return grid[0][0];
		}
		if(grid[0][1] != ' ' && grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1]){
			return grid[0][1];
		}
		if(grid[0][2] != ' ' && grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2]){
			return grid[0][2];
		}
		return ' ';
	}

	private char checkSideWay() {
		if(grid[0][0] != ' ' && grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2]){
			return grid[0][0];
		}
		if(grid[1][0] != ' ' && grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2]){
			return grid[1][0];
		}
		if(grid[2][0] != ' ' && grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2]){
			return grid[2][0];
		}
		return ' ';
	}

	/**
	*	A function for counting how many tiles have been filled
	*
	*	@return tileCount a Int value containing the number of filled tiles
	*/
	public int gridCounter(){
		int counter = 0;
		for(int i = 0;i<3;i++){
			for(int j = 0;j<3;j++){
				if (grid[i][j] != ' '){
					counter++;
				}
			}
		}
		return counter;	
	}

	/**
	*	A function for getting the player score
	*
	*	@return playerScore a int value for the player score
	*/
	public int getPlayerScore() {
		return playerScore;
	}
	/**
	*	A function for getting the computerscore
	*
	*	@return computerScore a int value for the computer score
	*/
	public int getComputerScore() {
		return computerScore;
	}
	/**
	*	A function for getting the tie score
	*
	*	@return tieScore a int value for the tie score
	*/
	public int getTieScore() {
		return tieScore;
	}

}