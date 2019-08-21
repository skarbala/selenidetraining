package base;

import com.codeborne.selenide.Configuration;
import cucumber.api.java.Before;


public class TaggedHooks {

    static {
        Configuration.baseUrl = "http://localhost:80";
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/win/chromedriver75_win.exe");
    }
}
