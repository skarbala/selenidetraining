package tests;

import base.TestBase;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
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
        $("ul.spells")
                .findAll("li")
                .shouldHave(sizeGreaterThan(1))
                .find(exactText("tortures a person"))
                .click();

        $("div.modal-container")
                .should(appear)
                .shouldHave(text("Crucio"));
     }

    @Test
    public void itShouldFilterSpells() {
        $("input").sendKeys("tortures a person");
        $$("ul.spells li").shouldHave(size(1));
    }

}
