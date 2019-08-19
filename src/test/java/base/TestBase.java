package base;

import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class TestBase {
    protected WebDriver driver;

    static {
        Configuration.baseUrl = "http://localhost:80";
        Configuration.timeout = 1000;
        Configuration.pollingInterval = 100;
//        Configuration.headless = true;
        Configuration.startMaximized = true;
        Configuration.clickViaJs = true;
//        Configuration.holdBrowserOpen = true;

//        Configuration.remote = "http://localhost:4444/wd/hub";
    }


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/win/chromedriver75_win.exe");
//        driver = new ChromeDriver();
//        WebDriverRunner.setWebDriver(driver);
    }

    @After
    public void tearDown() {
//        driver.close();
//        driver.quit();
    }
}
