package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.textCaseSensitive;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GosslingatorTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/win/chromedriver75_win.exe");
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
        open("http://localhost:80/gosslingator.php");
    }

    @Test
    public void itShouldDisplayTitle() {
        $(".ryan-title").shouldHave(textCaseSensitive("GOSLINGATE ME"));
    }

    @Test
    public void itShouldAddOneRyan() {
        addRyan();

        $("div.ryan-counter h2").shouldHave(text("1"));
        $("div.ryan-counter h3").shouldHave(text("ryan"));
    }

    @Test
    public void itShouldTwoRyans() {
        addRyan(2);

        $("div.ryan-counter h2").shouldHave(text("2"));
        $("div.ryan-counter h3").shouldHave(text("ryans"));
    }

    @Test
    public void itShouldDisplayWarningMessage() {
        addRyan(50);
        $(By.cssSelector("h1.tooManyRyans")).shouldHave(exactText(
                "NUMBER OF\n" +
                        "RYANS\n" +
                        "IS TOO DAMN\n" +
                        "HIGH"
        ));
    }

    @Test
    public void itShouldDisplayNoRyanOnPageOpen() {
        Assert.assertEquals(0, driver.findElements(By.cssSelector("img")).size());
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    private void addRyan() {
        $(By.id("addRyan")).click();
    }

    private void addRyan(int numberOfRyans) {
        for (int i = 0; i < numberOfRyans; i++) {
            $(By.id("addRyan")).click();
        }
    }

}
