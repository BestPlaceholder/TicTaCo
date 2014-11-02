package is.placeholder.tictactoe;
import org.junit.Test;
import static org.junit.Assert.*;


public class MainTest {
    @Test
    public void TestInitializer() {
    	TicTacToe function = new TicTacToe(true);
    	assertEquals(true, function.computer);
    }
	@Test
    public void TestInitializer2() {
    	TicTacToe function = new TicTacToe(false);
    	assertEquals(false, function.computer);
    }
    @Test
    public void TestInitializerWith2Arguments() {
    	TicTacToe function = new TicTacToe(true, true);
    	assertEquals(true, function.computer);
    	assertEquals(true, function.difficulty);
    }
    @Test
    public void TestInitializerWith2Arguments2() {
    	TicTacToe function = new TicTacToe(true, false);
    	assertEquals(true, function.computer);
    	assertEquals(false, function.difficulty);
    }
    @Test
    public void TestmakeGrid() {
    	char [][] test = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    	TicTacToe function = new TicTacToe(true);
    	function.makeGrid();
    	assertArrayEquals( test, function.getGrid());
    }

    @Test
    public void TestLeftRightCorner() {
    	TicTacToe function = new TicTacToe(true);
    	char [][] test = {{'X',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    	function.makeGrid();

    	assertArrayEquals( test, function.humanPlayerMove(0, 0));
    }

    @Test
    public void TestManyHumanMovesInRow() {
    	TicTacToe function = new TicTacToe(true);
    	char [][] test = {{'X',' ','X'},{'X',' ',' '},{'X',' ','X'}};
    	function.makeGrid();
    	function.grid = function.humanPlayerMove(0, 0);
    	function.grid = function.humanPlayerMove(0, 2);
    	function.grid = function.humanPlayerMove(1, 0);
    	function.grid = function.humanPlayerMove(2, 0);
    	assertArrayEquals( test, function.humanPlayerMove(2, 2));
    }
    
    @Test
    public void TestSameHumanMoveInRow() {
    	TicTacToe function = new TicTacToe(true);
    	function.makeGrid();
    	char [][] testData = {{'X',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    	function.setGrid(testData);
    	function.grid = function.humanPlayerMove(0, 0);
    	assertEquals( true, function.getWhoToPlay());

    }
    
}