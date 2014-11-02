package is.placeholder.tictactoe.MainWebTest;


import static org.junit.Assert.assertEquals;

import org.junit.*;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class TicTaCoWebTest {

    static WebDriver driver;
    static String baseUrl;
    static String port;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void before() {
        // Will be run before all tests in class are run
        driver = new FirefoxDriver();
        port = System.getenv("PORT");
        if(port == null) {
            port = "4567";
        }
        if(System.getenv("HEROKU_STAGING_APP") != null) {
            baseUrl = "http://" + System.getenv("HEROKU_STAGING_APP") + ".herokuapp.com";
        } else {
            //running tests locally
            baseUrl = "http://127.0.0.1:"+port;
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void assertTitle() {
        driver.get(baseUrl + "/");
        assertEquals("TicTaCo", driver.getTitle());
    }

    @Test
    public void assertCheckEmptyGrid() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.xpath("//input[@value='Easy']")).click();
        for(int x = 1; x <= 3; x++) {
            for(int y = 1; y <=3; y++) {
                assertEquals(baseUrl+"/img/empty.jpg", driver.findElement(By.id("tile"+x+y)).getAttribute("src"));
            }
        }
    }

    @Test
    public void assertClickGridIsX() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.xpath("//input[@value='Hard']")).click();
        driver.findElement(By.id("tile22")).click();
        assertEquals(baseUrl+"/img/x.jpg", driver.findElement(By.id("tile22")).getAttribute("src"));
    }

    @AfterClass
    public static void after() {
        // Will be run after all tests in class have run
        driver.close();
    }

}
