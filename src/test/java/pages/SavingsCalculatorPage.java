package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;

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
        $(emailInput).val(email).pressTab();
    }

    public void enterYears(int years) {
        $(yearsInput).val(String.valueOf(years));
    }

    public void enterOneTimeInvestment(String amount) {
        $(oneTimeInvestmentInput).val(amount);
    }

    public void selectFund(String fundToSelect) {
        $(fundSelect).selectOption(fundToSelect);
    }

    public void applyForSaving() {
        applyButton.click();
    }


    public SelenideElement getCalculatedTotalIncomeElement() {
        return $(resultElement).find("div").find("p");
    }

    public SelenideElement getCalculatedInterestIncomeElement() {
        return $(resultElement).find("div", 1).find("p");
    }

    public SelenideElement getCalculatedRiskElement() {
        return $(resultElement).find("div", 2).find("p");
    }

    public WebElement getRecentRequestDetail() {
        return mostRecentSavingsDetail;
    }

    public SelenideElement getApplyButton() {
        return $(applyButton);
    }


    public SelenideElement getEmailInputWrapper() {
        return $(emailInput).parent();
    }
}