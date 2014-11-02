package is.placeholder.tictactoe;
import org.junit.Test;
import static org.junit.Assert.*;


public class MainTest {
    @Test
    public void TestInitializer() {
    	TicTacToe function = new TicTacToe(true);
    	assertEquals(true, function.Computer);
    }
	@Test
    public void TestInitializer2() {
    	TicTacToe function = new TicTacToe(false);
    	assertEquals(false, function.Computer);
    }
    @Test
    public void TestInitializerWith2Arguments() {
    	TicTacToe function = new TicTacToe(true, true);
    	assertEquals(true, function.Computer);
    	assertEquals(true, function.Difficulty);
    }
    @Test
    public void TestInitializerWith2Arguments2() {
    	TicTacToe function = new TicTacToe(true, false);
    	assertEquals(true, function.Computer);
    	assertEquals(false, function.Difficulty);
    }
    @Test
    public void TestmakeGrid() {
    	char [][] test = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    	assertArrayEquals( test, TicTacToe.makeGrid());
    }

    @Test
    public void TestLeftRightCorner() {
    	char [][] test = {{'X',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    	char [][] empty = TicTacToe.makeGrid();
    	assertArrayEquals( test, TicTacToe.humanPlayerMove(0, 0, empty));
    }

    @Test
    public void TestManyHumanMovesInRow() {
    	char [][] test = {{'X',' ','X'},{'X',' ',' '},{'X',' ','X'}};
    	char [][] empty = TicTacToe.makeGrid();
    	empty = TicTacToe.humanPlayerMove(0, 0, empty);
    	empty = TicTacToe.humanPlayerMove(0, 2, empty);
    	empty = TicTacToe.humanPlayerMove(1, 0, empty);
    	empty = TicTacToe.humanPlayerMove(2, 0, empty);
    	assertArrayEquals( test, TicTacToe.humanPlayerMove(2, 2, empty));
    }
}