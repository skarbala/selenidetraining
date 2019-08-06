package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SavingsCalculatorPage {
    @FindBy(id = "emailInput")
    private WebElement emailInput;

    @FindBy(id = "yearsInput")
    private WebElement yearsInput;

    @FindBy(id = "oneTimeInvestmentInput")
    private WebElement oneTimeInvestmentInput;

    @FindBy(id = "fundSelect")
    private WebElement fundSelect;

    @FindBy(css = "button.btn")
    private WebElement applyButton;

    @FindBy(css = "div.result")
    private WebElement resultElement;

    @FindBy(css = "ul.saving-list div.saving-detail")
    private WebElement mostRecentSavingsDetail;

    private WebDriver pageDriver;

    public SavingsCalculatorPage(WebDriver driver) {
        this.pageDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
        emailInput.sendKeys(Keys.TAB);
    }

    public void enterYears(int years) {
        yearsInput.clear();
        yearsInput.sendKeys(String.valueOf(years));
    }

    public void enterOneTimeInvestment(String amount) {
        oneTimeInvestmentInput.clear();
        oneTimeInvestmentInput.sendKeys(amount);
    }

    public void selectFund(String fundToSelect) {
        new Select(fundSelect).selectByVisibleText(fundToSelect);
    }

    public void applyForSaving() {
        applyButton.click();
    }


    public WebElement getCalculatedTotalIncomeElement() {
        return resultElement.findElement(By.xpath("./div[1]/p"));
    }

    public WebElement getCalculatedInterestIncomeElement() {
        return pageDriver.findElement(By.cssSelector("div.result > div:nth-child(2) p"));
    }

    public WebElement getCalculatedRiskElement() {
        return resultElement.findElement(By.xpath("./div[3]/p"));
    }

    public WebElement getRecentRequestDetail() {
        return mostRecentSavingsDetail;
    }

    public WebElement getApplyButton() {
        return applyButton;
    }


    public WebElement getEmailInputWrapper(){
        return pageDriver.findElement(By.xpath("//input[@id='emailInput']/.."));
    }
}