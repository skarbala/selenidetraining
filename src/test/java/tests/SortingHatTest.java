package tests;

import base.TestBase;
import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SortingHatTest extends TestBase {


    @Test
    public void itShouldDisplayNameOfHouse() {
        open("/sortinghat.php");
        $("button").click();
        $("img.loading").waitUntil(appears, 15000).waitUntil(disappears, 10000);
        $("p.result").shouldBe(visible).shouldNotBe(empty);
    }

    @Test
    public void itShouldDisplayGryffindor() {
        open("/sortinghat.php");
        String generatedHouse = "";
        while (!generatedHouse.equals("Slytherin")) {
            $("button").shouldBe(enabled).click();
            $("img.loading").should(appear).should(disappear);
            generatedHouse = $("p.result").shouldBe(visible).shouldNotBe(empty).getText();
        }
    }
}
