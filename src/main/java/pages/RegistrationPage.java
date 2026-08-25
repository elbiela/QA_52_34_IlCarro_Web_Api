package pages;

import dto.User;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBoxLabel;



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

    public void clickCheckboxTermsOfUse() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkBox);
    }

    public void clickCheckboxWithActions(){
        int x = checkBoxLabel.getSize().getWidth();
        int y = checkBoxLabel.getSize().getHeight();
        System.out.println("x = " + x + " y = " + y);
        Actions actions = new Actions(driver);
        actions.moveToElement(checkBoxLabel,-x/10*3,-y/4).click().perform();
    }
}
