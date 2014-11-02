package is.placeholder.tictactoe;
import java.util.Random;

/*************************************************************************
 *  Compilation:  javac TicTacAI.java
 *  Dependencies: java.util.Random
 *  <p>
 *  The <tt>TicTacAI</tt> class takes in a tic tac toe table in the form
 *  of a two dimensional char array and adds a move to it based on its
 *  state and the selected difficulty.
 *  To see its implementation go to
 *  <a href="http://ourprettyherokuserver.com">Heroku - Tic Tac Toe.</a>
 *
 *  @author Andri Mar Sigurdsson
 *************************************************************************/

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

    /*
     *  getMove takes in a playboard and plays a move on them based on difficulty and board state.
     *  If the difficulty is on it has set starting moves as well as going for the win
     *  and blocking opponent wins when it can.
     *  if its not it just plays completely random moves
     */
    public char[][] getMove(char playBoard[][]){
	turn++;
	//Gets skipped if the difficulty is off
	if(diff){
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
	}
	playBoard = randomMove(playBoard);
	return playBoard;
    }
    /********************************************************************************
     * Helper functions that search for victory or loss conditions and return answers
     *********************************************************************************/
    //Calls the straight and diagonal row checks and returns the result, null if there is none.
    private int[] rowCheck(char playBoard[][], char player){
	int[] move = straightCheck(playBoard, player);
	if(move != null) return move;
	return diagCheck(playBoard, player);
    }

    //Checks if there are two straight in a row in any column or row and returns the intercept point
    private int[]  straightCheck(char playBoard[][], char player){
        int first, second;
	int[] move = new int[2];
        for (int i = 0; i < 2; i++) {
            //Checking for front and back column
            if (playBoard[i][0] == player && playBoard[i][2] == player){
                first = i;
                second = 1;
                if(playBoard[first][second] == ' '){
		    move[0] = first;
		    move[1] = second;
		    return move;
		}
            }
            for (int j = 0; j < 2; j++) {
                //Checking for row lines
                if (playBoard[i][j] == player && playBoard[i+1][j] == player){
                    first = (i == 0) ? 2 : 0;
                    second = j;
		    if(playBoard[first][second] == ' '){
			move[0] = first;
			move[1] = second;
			return move;
		    }
                }
                //Checking for column lines
                if (playBoard[i][j] == player && playBoard[i][j+1] == player){
                    first = i;
                    second = (j == 0) ? 2 : 0;
		    if(playBoard[first][second] == ' '){
			move[0] = first;
			move[1] = second;
			return move;
		    }
                }
                //checking for top and bottom
                if (i == 0)
                    if (playBoard[0][j] == player && playBoard[2][j] == player){
                        first = 1;
                        second = j;
			if(playBoard[first][second] == ' '){
			    move[0] = first;
			    move[1] = second;
			    return move;
			}
                    }
            }
        }
        return null;
    }
    //checks to see if there are potential diagonal lines and returns the intersept point, null if there is none.
    private int[] diagCheck(char playboard[][], char player){
        int[] move = new int[2];
        if (playboard[1][1] == player){
            if(playboard[0][0] == player && playboard[2][2] == ' '){
                move[0] = 2;
                move[1] = 2; 
                return move;
            }
            if(playboard[0][2] == player && playboard[2][0] == ' '){
                move[0] = 2;
                move[1] = 0;
                return move;
            }
            if(playboard[2][0] == player && playboard[0][2] == ' '){
                move[0] = 0;
                move[1] = 2;
                return move;
            }
            if(playboard[2][2] == player && playboard[0][0] == ' '){
                move[0] = 0;
                move[1] = 0;
                return move;
            }
        }
        if((playboard[0][0] == player && playboard[2][2] == player) || (playboard[2][0] == player && playboard[0][2] == player)){
            move[0] = 1;
            move[1] = 1;
            return ((playboard[1][1] == ' ') ? move : null);
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
