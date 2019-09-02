package tests;

import base.TestBase;
import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WaitForItTest extends TestBase {

    @Before
    public void openPage() {
        open("/waitforit.php");
    }

    @Test
    public void waitForValue() {
        String expectedText = "dary !!!";
        $(By.id("startWaitForText")).click();
        $(By.id("waitForTextInput")).shouldHave(value(expectedText));
    }

    @Test
    public void waitForClass() {
        $(By.id("startWaitForProperty")).click();
        $(byId("waitForProperty")).shouldHave(cssClass("error"));
    }

    @Test
    public void itShouldDisplayResponseTimeMessageSelenide() {
        $(By.id("startWaitForText")).click();

        $("div.current-wait-time").shouldHave(text("Response time was"));
    }

    @Test
    public void itShouldDisplayResponseTimeMessage() {
        $(By.id("startWaitForText")).click();

        $("div.current-wait-time").shouldHave(text("Response time"));
    }

//    @Test
//    public void itShouldDisplayResponseTimeMessageThreadSleep() throws InterruptedException {
//        $(By.id("startWaitForText")).click();
//
//        Thread.sleep(3000);
//        Assert.assertTrue(driver.findElement(By.cssSelector("div.current-wait-time"))
//                .getText()
//                .contains("Response time"));
//    }


}
