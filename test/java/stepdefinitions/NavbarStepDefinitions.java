package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.NavbarPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReuseableMethods;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utilities.Driver.driver;

public class NavbarStepDefinitions {

    NavbarPage page = new NavbarPage();
    Actions actions = new Actions(Driver.getDriver());
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    @Given("the user navigates to the 2NHaber homepage")
    public void theUserNavigatesToThe2NHaberHomepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url_2nhaber"));
        ReuseableMethods.sleep(2);
    }

    @Then("clicks company logo should be redirected to the homepage")
    public void clicksCompanyLogoShouldBeRedirectedToTheHomepage() {
        wait.until(ExpectedConditions.elementToBeClickable(page.companyLogo)).click();
        assertEquals("https://2nhaber.com/", Driver.getDriver().getCurrentUrl(), "User is not redirected to the homepage");
        ReuseableMethods.sleep(2);
    }

    @And("clicks the dropdown menu for each navbar link should be accessible")
    public void clicksTheDropdownMenuForEachNavbarLinkShouldBeAccessible() {
        int count = 1;
        for (WebElement link : page.navbarLinks){
            actions.moveToElement(link).perform();
            String dynamicXPath = "//*[@id='menu-1-5dc673f1']/li[" + count + "]//a";
            List<WebElement> firstDropDown = driver.findElements(By.xpath(dynamicXPath));
            for (WebElement firstSubMenu : firstDropDown){
                firstSubMenu.click();
                wait.until(ExpectedConditions.urlContains("https://2nhaber.com/"));
                assertTrue(Driver.getDriver().getCurrentUrl().contains("https://2nhaber.com/"), "Dropdown link is not accessible");
                ReuseableMethods.sleep(2);
                Driver.getDriver().navigate().back();
                wait.until(ExpectedConditions.visibilityOf(link));
            }
            count++;
        }
    }

    @Then("toggles the dark-light mode button the page theme should switch accordingly")
    public void togglesTheDarkLightModeButtonThePageThemeShouldSwitchAccordingly() {
        page.darkLightToggle.click();
        WebElement modeSwitcherElement = Driver.getDriver().findElement(By.cssSelector("div.elementor-widget-cmsmasters-mode-switcher__container"));
        String dataState = modeSwitcherElement.getAttribute("data-state");
        assertEquals(dataState, "second", "Page did not switch to dark mode as expected.");
    }

    @Then("clicks on the search button the search popup should be displayed")
    public void clicksOnTheSearchButtonTheSearchPopupShouldBeDisplayed() {
        page.searchButton.click();
        WebElement searchPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='search']")));
        assertTrue(searchPopup.isDisplayed(), "Search popup is not displayed.");
        actions.sendKeys(Keys.ESCAPE).perform();
        ReuseableMethods.sleep(4);
    }

    @Then("clicks on the hamburger menu button the hamburger menu should be displayed")
    public void clicksOnTheHamburgerMenuButtonTheHamburgerMenuShouldBeDisplayed() {
        page.hamburgerMenuButton.click();
        ReuseableMethods.sleep(3);
        WebElement hamburgerMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cmsmasters-offcanvas-alignment-left.active")));
        assertTrue(hamburgerMenu.isDisplayed(), "Hamburger menu did not display!");
        actions.sendKeys(Keys.ESCAPE).perform();
    }
}
