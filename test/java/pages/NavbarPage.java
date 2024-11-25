package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class NavbarPage {

    WebDriver driver;

    public NavbarPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a.elementor-widget-cmsmasters-site-logo__link")
    public WebElement companyLogo;

    @FindBy(xpath = "//*[@id=\"menu-1-5dc673f1\"]/li/a")
    public List<WebElement> navbarLinks;

    @FindBy(css = "div.elementor-widget-cmsmasters-mode-switcher__button-inner")
    public WebElement darkLightToggle;

    @FindBy(css = "span.elementor-widget-cmsmasters-search__popup-trigger-inner-icon")
    public WebElement searchButton;

    @FindBy(css = "span.elementor-widget-cmsmasters-offcanvas__trigger-icon")
    public WebElement hamburgerMenuButton;


}
