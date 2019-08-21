package steps;


import com.codeborne.selenide.Condition;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GosslingatorSteps {
    @Given("^I am on Gosslingator page$")
    public void iAmOnGosslingatorPage() {
        open("/gosslingator.php");
    }

    @When("^I click on add ryan button$")
    public void iClickOnAddRyanButton() {
        $(By.id("addRyan")).click();
}

    @Then("^ryan head is shown$")
    public void ryanHeadIsShown() {
        $("img").shouldBe(Condition.visible);
    }
}
