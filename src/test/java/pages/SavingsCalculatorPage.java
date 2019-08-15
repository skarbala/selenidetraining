package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SavingsCalculatorPage {
    private SelenideElement emailInput = $(byId("emailInput"));

    private SelenideElement yearsInput = $(byId("yearsInput"));

    private SelenideElement oneTimeInvestmentInput = $(byId("oneTimeInvestmentInput"));

    private SelenideElement fundSelect = $(byId("fundSelect"));

    private SelenideElement applyButton = $("button.btn");

    private SelenideElement resultElement = $("div.result");

    private SelenideElement mostRecentSavingsDetail = $("ul.saving-list").find("div.saving-detail");

    public SavingsCalculatorPage() {
        page(this);
    }

    public void enterEmail(String email) {
        emailInput.val(email).pressTab();
    }

    public void enterYears(int years) {
        yearsInput.val(String.valueOf(years));
    }

    public void enterOneTimeInvestment(String amount) {
        oneTimeInvestmentInput.val(amount);
    }

    public void selectFund(String fundToSelect) {
        fundSelect.selectOption(fundToSelect);
    }

    public void applyForSaving() {
        applyButton.click();
    }


    public SelenideElement getCalculatedTotalIncomeElement() {
        return resultElement.find("div").find("p");
    }

    public SelenideElement getCalculatedInterestIncomeElement() {
        return resultElement.find("div", 1).find("p");
    }

    public SelenideElement getCalculatedRiskElement() {
        return resultElement.find("div", 2).find("p");
    }

    public SelenideElement getRecentRequestDetail() {
        return mostRecentSavingsDetail;
    }

    public SelenideElement getApplyButton() {
        return applyButton;
    }

    public SelenideElement getEmailInputWrapper() {
        return emailInput.parent();
    }
}