package is.placeholder.tictactoe;
import org.junit.Test;
import static org.junit.Assert.*;


public class MainTest {
    @Test
    public void TestInitializer() {
    	tictactoe function = new tictactoe(true);
    	assertEquals(true, function.Computer);
    }
	@Test
    public void TestInitializer2() {
    	tictactoe function = new tictactoe(false);
    	assertEquals(false, function.Computer);
    }
    @Test
    public void TestInitializerWith2Arguments() {
    	tictactoe function = new tictactoe(true, true);
    	assertEquals(true, function.Computer);
    	assertEquals(true, function.Difficulty);
    }
    @Test
    public void TestInitializerWith2Arguments2() {
    	tictactoe function = new tictactoe(true, false);
    	assertEquals(true, function.Computer);
    	assertEquals(false, function.Difficulty);
    }
    @Test
    public void TestmakeGrid() {
    	char [][] test = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    	assertArrayEquals( test, tictactoe.makeGrid());
    }

    @Test
    public void TestLeftRightCorner() {
    	char [][] test = {{'X',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    	char [][] empty = tictactoe.makeGrid();
    	assertArrayEquals( test, tictactoe.humanPlayerMove(0, 0, empty));
    }
}