package is.placeholder.tictactoe;
import org.junit.*;
import static org.junit.Assert.*;

public class TicTacAITest {
    @Test
    public void firstMoveX() {
        TicTacAI AI = new TicTacAI('X', true);
        char[][] playBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
        playBoard = AI.getMove(playBoard);
        assertEquals("First move for X should be 0 0", 'X', playBoard[0][0]);
        playBoard[0][0] = ' ';
        for (int i = 0; i < 3; i++){
            for ( int j = 0; j < 3; j ++){
                assertEquals("All other fields should be empty", ' ', playBoard[i][j]);
	    }
	    
	}
	    
    }

    @Test
    public void firstMoveO() {
	char[][] playBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
	for (int i = 0; i < 3 ; i++){
	    for (int j = 0; j < 3; j++){
	TicTacAI AI = new TicTacAI('O',true);
		playBoard[i][j] = 'X';
		playBoard = AI.getMove(playBoard);
		if (i == 1 && j == 1){
		    assertEquals("If the center is occupied circles first move should be 0 0", 'O', playBoard[0][0]);
		    assertEquals("1 1 should still be X after AI has played", 'X', playBoard[1][1]);
		    playBoard[0][0] = ' ';
		    playBoard[1][1] = ' ';
		    for(int k = 0; k < 3; k++){
			for (int h = 0; h < 3; h++){
			    assertEquals("All other fields should be empty", ' ', playBoard[k][h]);
			}
		    }
		}
		else{
		    assertEquals("1 1 should be O's first move if possible", 'O', playBoard[1][1]);
		    assertEquals("X should still be in the same location after AI has played", 'X', playBoard[i][j]);
		    playBoard[i][j] = ' ';
                    playBoard[1][1] = ' ';
		    for(int k = 0; k < 3; k++){
                        for (int h = 0; h < 3; h++){
                            assertEquals("All other fields should be empty", ' ', playBoard[k][h]);
                        }
                    }

		}
		    
	    }
	}
    }
    @Test
    public void randomMove(){
	char[][] playBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
	for (int i = 0; i < 1000; i++){
	TicTacAI AI = new TicTacAI('X',true);
	    for(int j = 0; j < 9; j++) {
		playBoard = AI.getMove(playBoard);
	    }
	    for(int h = 0; h < 3; h++){
		for (int k = 0; k < 3; k++){
		    assertEquals("These fields should all contain X", 'X', playBoard[h][k]);
		    playBoard[h][k] = ' ';
		}
	    }
	}
    }
   @Test
   public void straightRowHorCheck(){
       char[][] turnTicker = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
       TicTacAI AI = new TicTacAI('O',true);
       turnTicker = AI.getMove(turnTicker);

       char[][] playBoard1 = {{'X','X',' '},{' ',' ',' '},{' ',' ',' '}};
       playBoard1 = AI.getMove(playBoard1);
       assertEquals("To not lose the AI should play in 0 2", 'O', playBoard1[0][2]);

       char[][] playBoard2 = {{' ','X','X'},{' ',' ',' '},{' ',' ',' '}};
       playBoard2 = AI.getMove(playBoard2);
       assertEquals("To not lose the AI should play in 0 0", 'O', playBoard2[0][0]);

       char[][] playBoard3 = {{'O','O','X'},{' ',' ',' '},{' ',' ',' '}};
       playBoard3 = AI.getMove(playBoard3);
       assertEquals("AI should not overwrite X at 0 2", 'X', playBoard3[0][2]);

       char[][] playBoard4 = {{'X',' ','X'},{' ',' ',' '},{' ',' ',' '}};
       playBoard4 = AI.getMove(playBoard4);
       assertEquals("To not lose the AI should play in 0 1", 'O', playBoard4[0][1]);
   }
    @Test
    public void straightRowVerCheck(){
        char[][] turnTicker = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
        TicTacAI AI = new TicTacAI('O',true);
        turnTicker = AI.getMove(turnTicker);

        char[][] playBoard1 = {{'X',' ',' '},{'X',' ',' '},{' ',' ',' '}};
        playBoard1 = AI.getMove(playBoard1);
        assertEquals("To not lose the AI should play in 2 0", 'O', playBoard1[2][0]);

        char[][] playBoard2 = {{' ',' ',' '},{'X',' ',' '},{'X',' ',' '}};
        playBoard2 = AI.getMove(playBoard2);
        assertEquals("To not lose the AI should play in 0 0", 'O', playBoard2[0][0]);

        char[][] playBoard3 = {{'X',' ',' '},{' ',' ',' '},{'X',' ',' '}};
        playBoard3 = AI.getMove(playBoard3);
        assertEquals("To not lose the AI should play in 1 0", 'O', playBoard3[1][0]);

        char[][] playBoard4 = {{'X',' ',' '},{'O',' ',' '},{'O',' ',' '}};
        playBoard4 = AI.getMove(playBoard3);
        assertEquals("The AI should not overwrite X in 0 0", 'X', playBoard4[0][0]);
    }
}
