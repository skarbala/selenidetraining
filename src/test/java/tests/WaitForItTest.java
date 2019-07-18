package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WaitForItTest extends TestBase {

    @Before
    public void openPage() {
        open(BASE_URL + "/waitforit.php");
    }

    @Test
    public void waitForValue() {
        String expectedText = "dary !!!";
        $(By.id("startWaitForText")).click();
        WebElement input = $(By.id("waitForTextInput"));

        new WebDriverWait(driver, 5)
            .until(ExpectedConditions.attributeToBe(input, "value", expectedText));

        Assert.assertEquals(expectedText, input.getAttribute("value"));
    }

    @Test
    public void waitForClass() {
        $(By.id("startWaitForProperty")).click();

        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.attributeContains(By.id("waitForProperty"),"class","error"));
    }

    @Test
    public void itShouldDisplayResponseTimeMessage() {
        driver.findElement(By.id("startWaitForText")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(
            driver.findElement(By.cssSelector("div.current-wait-time")),
            "Response time"));
        Assert.assertTrue(driver.findElement(By.cssSelector("div.current-wait-time")).getText().contains("Response time"));
    }
}
