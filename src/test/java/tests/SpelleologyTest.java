package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SpelleologyTest extends TestBase {

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/spelleology.php");
    }

    @Test
    public void itShouldDisplayTortureSpell() {
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.spells li")));
        List<WebElement> spellElements = getSpellList();

        for (WebElement spellElement : spellElements) {
            if (spellElement.getText().equals("tortures a person")) {
                spellElement.click();
            }
        }
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal-container")));
        WebElement modal = driver.findElement(By.cssSelector("div.modal-container"));
        Assert.assertTrue(modal.getText().contains("Crucio"));
    }

    @Test
    public void itShouldFilterSpells() {
        driver.findElement(By.cssSelector("input")).sendKeys("tortures a person");
        new WebDriverWait(driver, 10).until(ExpectedConditions
            .numberOfElementsToBe(By.cssSelector("ul.spells li"), 1));
        Assert.assertEquals(getSpellList().size(), 1);
    }

    private List<WebElement> getSpellList() {
        return driver.findElements(By.cssSelector("ul.spells li"));
    }

}
