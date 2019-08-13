package tests;

import base.TestBase;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static utils.DataUtils.getExpectedSpells;

public class SpelleologyTest extends TestBase {

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/spelleology.php");
    }

    @Test
    public void itShouldContainSpells() throws FileNotFoundException {
        $("ul.spells")
                .findAll("li")
                .shouldHave(texts(getExpectedSpells()));
    }

    @Test
    public void itShouldDisplayTortureSpell() {
        ElementsCollection spellElements = $("ul.spells")
                .findAll("li")
                .shouldHave(sizeGreaterThan(1));

        for (WebElement spellElement : spellElements) {
            if (spellElement.getText().equals("tortures a person")) {
                spellElement.click();
            }
        }
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal-container")));
        WebElement modal = $("div.modal-container");
        Assert.assertTrue(modal.getText().contains("Crucio"));
    }

    @Test
    public void itShouldFilterSpells() {
        $("input").sendKeys("tortures a person");
        $$("ul.spells li").shouldHave(size(1));
    }

}
