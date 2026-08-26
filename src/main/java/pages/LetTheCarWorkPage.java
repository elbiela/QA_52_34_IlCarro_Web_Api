package pages;

import dto.Car;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class LetTheCarWorkPage extends BasePage {
    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//input[@formcontrolname='pickUpPlace']")
    WebElement inputLocation;
    @FindBy(id = "make")
    WebElement inputManufacture;
    @FindBy(id = "model")
    WebElement inputModel;
    @FindBy(id = "year")
    WebElement inputYear;
    @FindBy(id = "fuel")
    WebElement selectFuel;
    @FindBy(id = "seats")
    WebElement inputSeats;
    @FindBy(xpath = "//input[@formcontrolname='carClass']")
    WebElement inputCarClass;
    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;
    @FindBy(id = "price")
    WebElement inputPrice;
    @FindBy(id = "about")
    WebElement inputAbout;
    @FindBy(id = "photos")
    WebElement inputPhotos;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;

    public void fillCarFormWOPhoto(Car car) {
        inputLocation.sendKeys(car.getLocation());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());

        new Select(selectFuel)
                .selectByVisibleText(car.getFuel().getValue());

        inputSeats.sendKeys(car.getSeats());
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getRegistrationNumber());
        inputPrice.sendKeys(car.getPrice());
        inputAbout.sendKeys(car.getAbout());
    }

    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')", btnSubmit);
        clickWait(btnSubmit);
    }
}
