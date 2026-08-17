package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//input[@id='name']")
    WebElement inputName;

    @FindBy(xpath = "//input[@id='lastName']")
    WebElement inputLastName;

    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEmail;

    @FindBy(xpath = "//input[@id='password']")
    WebElement inputPassword;

    @FindBy(xpath = "//*[@class='checkbox-label terms-label']")
    WebElement checkBox;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;

    @FindBy(xpath = "//*[text()='Registered']")
    WebElement popUpRegistered;

    @FindBy(xpath = "//*[contains(text(),'Password must contain')]")
    WebElement passwordMustContain;

    public boolean isPopUpRegisteredDisplayed() {
        return isElementDisplayed(popUpRegistered);
    }

    public boolean isBtnYallaEnabled() {
        return btnYalla.isEnabled();
    }

    public void typeRegistrationForm(User user) {
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickCheckbox() {
        checkBox.click();
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }
}
