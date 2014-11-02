package is.placeholder.tictactoe;

public class tictactoe {
	public boolean Computer;
	public boolean Difficulty;

	//An initiatior for tictactoe against human player
	public tictactoe(boolean Player){
		Computer = Player;
	}
	//An initiatior for tictactoe against computer
	public tictactoe(boolean Player, boolean Difficult){
		Computer = Player;
		Difficulty = Difficult;
	}
    //Makes a empty tictactoe table
	public static char[][] makeGrid(){
		char[][]grid = new char[3][3];
		for(int i = 0;i<3;i++){
			for(int j = 0;j<3;j++){
				grid[i][j]=' ';
			}
		}
		return grid;
	}

	public static char[][] humanPlayerMove (int x, int y, char [][] grid){
		return grid;
	}

}