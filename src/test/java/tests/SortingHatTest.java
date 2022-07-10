package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SortingHatTest extends TestBase {


    @Test
    public void itShouldDisplayNameOfHouse() {
        open(BASE_URL + "/sortinghat.php");
        $(By.cssSelector("button")).click();
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.loading")));
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("img.loading")));
        Assert.assertFalse($(By.cssSelector("p.result")).getText().isEmpty());
    }
}
