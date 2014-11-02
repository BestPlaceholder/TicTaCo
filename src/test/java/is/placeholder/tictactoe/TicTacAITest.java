package is.placeholder.TicTacAITest;
import org.junit.*;
import static org.junit.Assert.*;
public class TicTacAITest {
    private TicTacAI AI;

    @Test
    public void firstMoveX() {
        AI = new TicTacAI('X', true);
        playBoard[][] = {{' ',' ',' '}{' ',' ',' '}{' ',' ',' '}};
        playBoard[][] = AI.getMove(playBoard);
        assertEquals("First move for X should be 0 0", 'X', playBoard[0][0]);
        playBoard[0][0] = ' ';
        for (int i = 0; i < 3; i++){
            for ( int j = 0; j < 3; j ++){
                assertEquals("All other fields should be empty", ' ', playBoard[i][j];
                             }
		    
            }
	    
        }
      
    }
