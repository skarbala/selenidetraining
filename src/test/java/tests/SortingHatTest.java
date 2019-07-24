package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SortingHatTest extends TestBase {


    @Test
    public void itShouldDisplayNameOfHouse() {
        driver.get(BASE_URL + "/sortinghat.php");
        driver.findElement(By.cssSelector("button")).click();
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.loading")));
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("img.loading")));
        Assert.assertFalse(driver.findElement(By.cssSelector("p.result")).getText().isEmpty());
    }
}
