package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GosslingatorTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/win/chromedriver.exe");
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);  // otherwise, selenide would open its own driver
        open("http://localhost:80/gosslingator.php");
    }

    @Test
    public void itShouldDisplayTitle() {
        Assert.assertEquals("GOSLINGATE ME", $(By.cssSelector(".ryan-title")).getText());
    }

    @Test
    public void itShouldFindImage(){
        // With Selenium
        $(By.cssSelector("img"));  // will crash with elementNotFoundException

        // With Selenide
        // Selenide knows it is CSS selector, you need not specify with By.cssSelector()
        $("img");  // Will NOT crash
    }

    @Test
    public void itShouldAddOneRyan() {
        $(By.id("addRyan")).click();

        String actualNumberOfRyans = $(By.id("ryanCounter")).getText();
        Assert.assertEquals("1", actualNumberOfRyans);

        System.out.println("Number of ryans: " + $(By.cssSelector("div.ryan-counter h2")).getText());
        Assert.assertEquals("ryan", $(By.cssSelector("div.ryan-counter h3")).getText());
    }

    @Test
    public void itShouldTwoRyans() {
        $(By.id("addRyan")).click();
        $(By.id("addRyan")).click();

        String actualNumberOfRyans = $(By.id("ryanCounter")).getText();
        String actualRyanDescription = $(By.cssSelector("div.ryan-counter h3")).getText();

        Assert.assertEquals("2", actualNumberOfRyans);
        Assert.assertEquals("ryans", actualRyanDescription);
    }

    @Test
    public void itShouldDisplayWarningMessage() {
        WebElement addRyanButton = $(By.id("addRyan"));
        for (int i = 0; i < 50; i++) {
            addRyanButton.click();
        }
        Assert.assertEquals(
                "NUMBER OF\n" +
                        "RYANS\n" +
                        "IS TOO DAMN\n" +
                        "HIGH",
                $(By.cssSelector("h1.tooManyRyans")).getText()
        );
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
}
