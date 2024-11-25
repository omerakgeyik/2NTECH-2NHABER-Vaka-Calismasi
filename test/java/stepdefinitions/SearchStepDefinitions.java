package stepdefinitions;


import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.NavbarPage;
import pages.SearchPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReuseableMethods;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utilities.Driver.driver;

public class SearchStepDefinitions {
    NavbarPage navbarPage = new NavbarPage();
    SearchPage searchPage = new SearchPage();
    Actions actions = new Actions(Driver.getDriver());
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));


    @When("the user searches for istanbul and navigates to the {int}th news article")
    public void theUserSearchesForIstanbulAndNavigatesToTheThNewsArticle(int articleNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(navbarPage.searchButton)).click();
        searchPage.searchInput.sendKeys(ConfigReader.getProperty("search"), Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(searchPage.verificationSearch));
        String head = searchPage.verificationSearch.getText();
        assertTrue(head.contains(ConfigReader.getProperty("search")));

        List<WebElement> newsList = driver.findElements(By.cssSelector("h3.entry-title.cmsmasters-widget-title__heading a"));
        ReuseableMethods.sleep(6);
        if (articleNumber <= newsList.size()) {
            WebElement targetNews = newsList.get(articleNumber - 1);
            String newsTitle = targetNews.getText();
            ReuseableMethods.sleep(2);
            actions.moveToElement(targetNews).perform();
            ReuseableMethods.sleep(4);
            targetNews.click();


            WebElement newsDetail = driver.findElement(By.cssSelector("h1.entry-title.cmsmasters-widget-title__heading"));
            String detailTitle = newsDetail.getText();
            assertEquals(detailTitle, newsTitle);
        }
    }
}
