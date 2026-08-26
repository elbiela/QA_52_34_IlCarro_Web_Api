package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//input[contains(@formcontrolname,'email')]")
    WebElement inputEmail;

    @FindBy(css = "*[id='password']")
    WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    @FindBy(xpath = "//*[text()='Logged in']")
    WebElement popUpSuccessLogin;

    @FindBy(xpath = "//*[text()='Login failed']")
    WebElement popUpLoginFailed;

    @FindBy(xpath = "//*[text()=' Email is required ']")
    WebElement emailIsRequired;

    @FindBy(xpath = "//*[text()=' Password is required ']")
    WebElement passwordIsRequired;

    @FindBy(xpath = "//button[@type='button']")
    WebElement btnLoginSuccessOk;

    public void typeLoginForm(User user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public void clickWaitBtnLoginSuccess() {
        clickWait(btnLoginSuccessOk);
    }

    public void clickOnFieldLogin() {
        inputEmail.click();
    }

    public void clickOnFieldPassword() {
        inputPassword.click();
    }

    public boolean isPopUpSuccessLoginDisplayed() {

        return isElementDisplayed(popUpSuccessLogin);
    }

    public boolean isPopUpLoginFailedDisplayed() {
        return isElementDisplayed(popUpLoginFailed);
    }

    public boolean isEmailAndPasswordRequiredMessagesDisplayed() {
        return isTextInElementPresent(emailIsRequired, "Email is required")
                && isTextInElementPresent(passwordIsRequired, "Password is required");
    }

    public boolean isBtnYallaEnabled() {
        return btnYalla.isEnabled();
    }


}
