package tests;

import base.TestBase;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GosslingatorTest extends TestBase {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests().succeededTests();

    @Before
    public void setUp() {
        open("/gosslingator.php");
    }

    @Test
    public void itShouldDisplayTitle() {
        $(".ryan-title").shouldHave(textCaseSensitive("GOSLINGATE ME"));
    }

    @Test
    public void itShouldAddOneRyan() {
        addRyan();
        screenshot("anton");
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

    @Test
    public void itShouldRemoveRyanHeadByClickingOnImage() {
        addRyan(30);

        $$("img").forEach(SelenideElement::click);

        $$("img").shouldHave(CollectionCondition.size(0));
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
