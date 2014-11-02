package is.placeholder.tictactoe;

public class TicTacToe {
	public boolean computer;
	public boolean difficulty;
	public boolean humanTurn;
	public char grid [][];
	//An initiatior for tictactoe against human player
	public TicTacToe(boolean player){
		computer = player;
		humanTurn = true;
	}
	//An initiatior for tictactoe against computer
	public TicTacToe(boolean player, boolean difficult){
		computer = player;
		difficulty = difficult;
	}
	public char[][] getGrid(){
		return grid;
	}
    //Makes a empty tictactoe table
	public void makeGrid(){
		grid = new char[3][3];
		for(int i = 0;i<3;i++){
			for(int j = 0;j<3;j++){
				grid[i][j]=' ';
			}
		}
	}

	public char[][] humanPlayerMove (int x, int y){
		
		grid[x][y] = 'X';
		humanTurn = false;
		return grid;
	}

}