package is.placeholder.tictactoe;
import org.junit.Test;
import static org.junit.Assert.*;


public class MainTest {
    @Test
    public void testInitializer() {
    	TicTacToe function = new TicTacToe(true);
    	assertEquals(true, function.computer);
    }
	@Test
    public void testInitializer2() {
    	TicTacToe function = new TicTacToe(false);
    	assertEquals(false, function.computer);
    }
    @Test
    public void testInitializerWith2Arguments() {
    	TicTacToe function = new TicTacToe(true, true);
    	assertEquals(true, function.computer);
    	assertEquals(true, function.difficulty);
    }
    @Test
    public void testInitializerWith2Arguments2() {
    	TicTacToe function = new TicTacToe(true, false);
    	assertEquals(true, function.computer);
    	assertEquals(false, function.difficulty);
    }
    @Test
    public void testmakeGrid() {
    	char [][] test = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    	TicTacToe function = new TicTacToe(true);
    	function.makeGrid();
    	assertArrayEquals( test, function.getGrid());
    }

    @Test
    public void testLeftRightCorner() {
    	TicTacToe function = new TicTacToe(true);
    	char [][] test = {{'X',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    	function.makeGrid();

    	assertArrayEquals( test, function.humanPlayerMove(0, 0));
    }

    @Test
    public void testManyHumanMovesInRow() {
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
    public void testSameHumanMoveInRow() {
    	TicTacToe function = new TicTacToe(true);
    	function.makeGrid();
    	char [][] testData = {{'X',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    	function.setGrid(testData);
    	function.grid = function.humanPlayerMove(0, 0);
    	assertEquals( true, function.getWhoToPlay());

    }
    @Test
    public void testComputerMoveBadAI() {
        TicTacToe function = new TicTacToe (true, true);
        char[][] input = {{'X',' ',' '},{' ',' ',' '},{' ',' ',' '}};
        function.setGrid(input);
        int [] testData = {1,1};
        assertArrayEquals(testData, function.computerMove(input));

    }

    @Test
    public void testIfPlayerHasWon(){
    	TicTacToe function = new TicTacToe(true);
    	function.makeGrid();
    	char [][] testData = {{'X','X','X'},{' ',' ',' '},{' ',' ',' '}};
    	function.setGrid(testData);
    	assertEquals( 2, function.hasWon());
    }
}