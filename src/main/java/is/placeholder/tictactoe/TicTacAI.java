package is.placeholder.tictactoe;
import java.util.Random;

public class TicTacAI {
    //identity is used to keep the possible identities while me and you keep track on which ones it should reference.
    char[] identity = {'X','O'};
    int me, you;
    //This counter would be more important if the AI was more advanced, as it its a glorified bool value.
    int turn = 0;
    //Diff true/false switches difficulty on and off.
    boolean diff;
    //The Constructor takes in which player the AI is and what its difficulty should be.
    TicTacAI(char me, boolean diff){
        this.me = (identity[0] == me ? 0 : 1);
        you = (identity[0] == me ? 1 : 0);
        this.diff = diff;
    }

    public char[][] getMove(char playBoard[][]){
	turn++;
	//For more consistent difficulty it plays set first turns
	if(turn == 1 && identity[me] == 'X'){
	    playBoard[0][0] = 'X';
	    return playBoard;
	}else if (turn == 1 && identity[me] == 'O'){
	    if (playBoard[1][1] == 'X'){
		playBoard[0][0] = 'O';
		return playBoard;
	    }else {
		playBoard[1][1] = 'O';
		return playBoard;
	    }
	}
	//Checking if there is a way for it to win this turn.
	int[] move = rowCheck(playBoard, identity[me]);
	if(move != null){
	    playBoard[move[0]][move[1]] = identity[me];
	    return playBoard;
	}
	//Checking if there is a way for the opponent to win next turn.
	move = rowCheck(playBoard, identity[you]);
	if(move != null){
	    playBoard[move[0]][move[1]] = identity[me];
	    return playBoard;
	}
    
	playBoard = randomMove(playBoard);
	return playBoard;
    }
    /********************************************************************************
     * Helper functions that search for victory or loss conditions and return answers
     *********************************************************************************/
    //Calls the straight and diagonal row checks and returns the result, null if there is none.
    private int[] rowCheck(char playboard[][], char player){
	int[] move = straightCheck(playboard, player);
	if(move != null) return move;
	return null;
    }

    //Checks if there are two straight in a row in any column or row and returns the intercept point
    private int[]  straightCheck(char playboard[][], char player){
        int first, second;
	int[] move = new int[2];
        for (int i = 0; i < 2; i++) {
            //Checking for front and back column
            if (playboard[i][0] == player && playboard[i][2] == player){
                first = i;
                second = 1;
                if(playboard[first][second] == ' '){
		    move[0] = first;
		    move[1] = second;
		    return move;
		}
            }
            for (int j = 0; j < 2; j++) {
                //Checking for row lines
                if (playboard[i][j] == player && playboard[i+1][j] == player){
                    first = (i == 0) ? 2 : 0;
                    second = j;
		    if(playboard[first][second] == ' '){
			move[0] = first;
			move[1] = second;
			return move;
		    }
                }
                //Checking for column lines
                if (playboard[i][j] == player && playboard[i][j+1] == player){
                    first = i;
                    second = (j == 0) ? 2 : 0;
		    if(playboard[first][second] == ' '){
			move[0] = first;
			move[1] = second;
			return move;
		    }
                }
                //checking for top and bottom
                if (i == 0)
                    if (playboard[0][j] == player && playboard[2][j] == player){
                        first = 1;
                        second = j;
			if(playboard[first][second] == ' '){
			    move[0] = first;
			    move[1] = second;
			    return move;
			}
                    }
            }
        }
        return null;
    }


    //Random Move generator
    private char[][] randomMove(char playBoard[][]) {
	Random ran = new Random();
        while (true) {
            int first = ran.nextInt(3);
            int second = ran.nextInt(3);
            if (playBoard[first][second] == ' ') {
                playBoard[first][second] = identity[me];
                return playBoard;
            }
        }
    }
}
