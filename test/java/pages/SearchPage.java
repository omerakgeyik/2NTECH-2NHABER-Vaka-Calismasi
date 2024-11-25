package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SearchPage {
    WebDriver driver;

    public SearchPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@type=\"search\"]")
    public WebElement searchInput;

    @FindBy(css = "h1.entry-title.cmsmasters-widget-title__heading")
    public WebElement verificationSearch;

}
