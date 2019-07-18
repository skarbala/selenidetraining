package base;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
    protected WebDriver driver;
    protected final String BASE_URL = "http://localhost:80";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/win/chromedriver75_win.exe");
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
