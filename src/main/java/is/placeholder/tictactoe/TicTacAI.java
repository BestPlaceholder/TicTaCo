package is.placeholder.tictactoe;

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

    public char[][] getMove(char playboard[][]){
	turn++;
	//For more consistent difficulty it plays set first turns
	if(turn == 1 && identity[me] == 'X'){
	    playboard[0][0] = 'X';
	    return playboard;
	}else if (turn == 1 && identity[me] == 'O'){
	    if (playboard[1][1] == 'X'){
		playboard[0][0] = 'O';
		return playboard;
	    }else {
		playboard[1][1] = 'O';
		return playboard;
	    }
	}
	return playboard;
    }
}
