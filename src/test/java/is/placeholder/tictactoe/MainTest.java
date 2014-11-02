package is.placeholder.tictactoe;
import org.junit.Test;
import static org.junit.Assert.*;


public class MainTest {
    @Test

    public void TestInitializer() {
    	tictactoe function = new tictactoe(true);
    	assertEquals(true, function.Computer);
    }
    
}

