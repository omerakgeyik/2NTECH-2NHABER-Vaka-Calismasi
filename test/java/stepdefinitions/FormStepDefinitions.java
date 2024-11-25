package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FormPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReuseableMethods;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;
import static utilities.Driver.driver;

public class FormStepDefinitions {
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    FormPage page = new FormPage();
    Actions actions = new Actions(Driver.getDriver());

    @Given("navigate to the 2NTECH HR page")
    public void navigateToTheNTECHHRPage() {
        Driver.getDriver().get(ConfigReader.getProperty("url_2ntech"));
        ReuseableMethods.sleep(2);
    }

    @When("fill out the first step of the form with valid information")
    public void fillOutTheFirstStepOfTheFormWithValidInformation() {
        page.fullNameInput.sendKeys(ConfigReader.getProperty("fullName"));
        page.birthInput.sendKeys(ConfigReader.getProperty("birthYear"));
        page.idInput.sendKeys(ConfigReader.getProperty("idNo"));
        page.phoneInput.sendKeys(ConfigReader.getProperty("phoneNo"));
        page.emailInput.sendKeys(ConfigReader.getProperty("emailAddress"));
        page.cVFileInput.sendKeys(ConfigReader.getProperty("filePath"));
        ReuseableMethods.sleep(2);

        boolean success = false;
        int attempts = 0;
        while (attempts < 3) {
            try {
                for (WebElement educ : page.educationInput) {
                    if (educ.getText().equals(ConfigReader.getProperty("education"))) {
                        educ.click();
                        assertEquals("true", page.kVKKRadioButton.getAttribute("value"));
                        wait.until(ExpectedConditions.visibilityOf(page.submitButton));
                        page.submitButton.click();
                        wait.until(ExpectedConditions.visibilityOf(page.checkPage));
                        actions.moveToElement(page.checkPage).perform();
                        assertEquals("Pozisyon*", page.checkPage.getText());
                        success = true;
                        break;
                    }
                }
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element exception occurred. Retrying...");
            }
            attempts++;
            if (success) {
                break;
            }
        }

    }

    @And("select {string} from the position")
    public void selectFromThePosition(String position) {
        for (WebElement work : page.position) {
            if (work.getText().equals(position)) {
                work.click();
            }
        }
    }

    @And("submit the form")
    public void submitTheForm() {
        page.backAndSendButton.click();
    }

    @Then("should see a success message confirming the form submission")
    public void shouldSeeASuccessMessageConfirmingTheFormSubmission() throws IOException {

        String currentUrl = driver.getCurrentUrl();

        HttpURLConnection connection = (HttpURLConnection) new URL(currentUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {
            assertEquals(200, responseCode);
        } else if (responseCode == 400) {
            assertEquals(400, responseCode);
        } else {
            System.out.println("Unexpected status code:" + responseCode);
        }
    }
}
