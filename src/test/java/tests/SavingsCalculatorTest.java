package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SavingsCalculatorPage;

import static org.junit.Assert.*;
import static org.openqa.selenium.By.cssSelector;

public class SavingsCalculatorTest extends TestBase {
    private SavingsCalculatorPage savingsCalculatorPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL.concat("/savingscalculator.php"));
        savingsCalculatorPage = new SavingsCalculatorPage(driver);
    }

    @Test
    public void itShouldEnableApplyButton() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

        assertTrue(savingsCalculatorPage.getApplyButton().isEnabled());
    }

    @Test
    public void itShouldDisplayCalculatedAmounts() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

        assertFalse(savingsCalculatorPage.getCalculatedTotalIncomeElement().getText().isEmpty());
        assertFalse(savingsCalculatorPage.getCalculatedInterestIncomeElement().getText().isEmpty());
    }

    @Test
    public void itShouldDisplayCalculatedRisk() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

        assertFalse(savingsCalculatorPage.getCalculatedRiskElement().getText().isEmpty());
    }


    @Test
    public void itShouldContainFundNameInNewRequest() {
        String fundToSelect = "Hoggwart's Fund";

        savingsCalculatorPage.selectFund(fundToSelect);
        savingsCalculatorPage.enterOneTimeInvestment("25000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

        savingsCalculatorPage.applyForSaving();

        assertEquals(
                fundToSelect,
                savingsCalculatorPage.getRecentRequestDetail().findElement(cssSelector("p.fund-description")).getText()
        );
    }

    @Test
    public void itShouldDisplayErrorMessageWhenEmailIsInvalid() {
        savingsCalculatorPage.enterEmail("invalid");
        assertTrue(savingsCalculatorPage.getEmailInputWrapper().getAttribute("class").contains("error"));
    }

    @Test
    public void itShouldHighlightNewRequestOnHover() throws InterruptedException {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");
        savingsCalculatorPage.applyForSaving();

        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.cssSelector("div.saving-detail"));
        action.moveToElement(we).build().perform();
        Thread.sleep(300);
        assertEquals("rgba(4, 102, 156, 1)", we.getCssValue("background-color"));
    }
}



