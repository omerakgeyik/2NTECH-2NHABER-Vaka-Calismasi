package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class FormPage {
    WebDriver driver;

    public FormPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "name")
    public WebElement fullNameInput;
    @FindBy(id = "birth")
    public WebElement birthInput;
    @FindBy(id = "tcKimlik")
    public WebElement idInput;
    @FindBy(id = "phone")
    public WebElement phoneInput;
    @FindBy(id = "email")
    public WebElement emailInput;
    @FindBy(id = "cv_field")
    public WebElement cVFileInput;
    @FindBy(xpath = "//*[@type=\"button\"]")
    public List<WebElement> educationInput;
    @FindBy(id = "pdp1")
    public WebElement kVKKRadioButton;
    @FindBy(xpath = "//*[@type=\"submit\"]")
    public WebElement submitButton;
    @FindBy(css = "div.flex.justify-between.items-start span")
    public List<WebElement> position;
    @FindBy(css = "div.text-white.flex.justify-center.items-center")
    public WebElement backAndSendButton;
    @FindBy(xpath = "//*[contains(@class, 'text-[14px]') and contains(@class, 'font-semibold')]")
    public WebElement checkPage;
}
