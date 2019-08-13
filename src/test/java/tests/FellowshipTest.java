package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.TestBase;

public class FellowshipTest extends TestBase {

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/fellowship.php");
    }

    @Test
    public void itShouldContainNameForEachFellow() {
        List<WebElement> fellowElements = getFellowElements();

        for (WebElement fellowElement : fellowElements) {
            Assert.assertFalse(fellowElement.findElement(By.cssSelector("h1")).getText().isEmpty());
        }
    }

    @Test
    public void itShouldContainSpecifiedFellows() {
        List<WebElement> fellowElements = getFellowElements();
        List<String> fellowNames = new ArrayList<String>();

        for (WebElement fellowElement : fellowElements) {
            System.out.println(fellowElement.findElement(By.cssSelector("h1")).getText());
            fellowNames.add(fellowElement.findElement(By.cssSelector("h1")).getText());
        }
        System.out.println(fellowNames);
        Assert.assertTrue(fellowNames.contains("Gandalf"));
        Assert.assertTrue(fellowNames.contains("Aragorn"));
        Assert.assertTrue(fellowNames.contains("Frodo"));
    }

    @Test
    public void itShouldDisplayMessageComplete() {
        List<String> fellowsToSelect = new ArrayList<String>();
        fellowsToSelect.add("Gandalf");
        fellowsToSelect.add("Aragorn");
        fellowsToSelect.add("Legolas");
        fellowsToSelect.add("Frodo");

        for (String fellowToSelect : fellowsToSelect) {
            selectFellow(fellowToSelect);
        }

        Assert.assertEquals("Complete", driver.findElement(By.cssSelector("div.points-left h3")).getText());
    }

    @Test
    public void itShouldDisplayPointsForEachFellow() {
        List<WebElement> displayedFellows = getFellowElements();
        for (WebElement displayedFellow : displayedFellows) {

            String actualPoints = displayedFellow.findElement(By.cssSelector("div.fellow-points h2")).getText();

            Assert.assertFalse(actualPoints.isEmpty());
        }
    }

    @Test
    public void itShouldHighlightFellows() {
        List<String> fellowsToSelect = new ArrayList<String>();
        fellowsToSelect.add("Gandalf");
        fellowsToSelect.add("Aragorn");
        fellowsToSelect.add("Legolas");
        fellowsToSelect.add("Frodo");

        for (String fellowToSelect : fellowsToSelect) {
            selectFellow(fellowToSelect);
        }

        List<String> higlightedFellows =
                driver.findElements(By.xpath("//ul[contains(@class,'list-of-fellows')]/li/div[contains(@class,'active')]//h1"))
                        .stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());

        for (String higlightedFellow : higlightedFellows) {
            Assert.assertTrue(fellowsToSelect.contains(higlightedFellow));
        }
    }

    private void selectFellow(String fellowName) {
        driver.findElement(By.xpath("//h1[contains(text(),'" + fellowName + "')]")).click();
    }

    private List<WebElement> getFellowElements() {
        return driver.findElements(By.cssSelector("ul.list-of-fellows li"));
    }
}
