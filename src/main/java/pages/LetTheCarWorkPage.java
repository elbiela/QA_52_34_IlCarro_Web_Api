package pages;

import dto.Car;
import enums.Fuel;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

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

    @FindBy(xpath = "//div[text()=' Wrong year ']")
    WebElement wrongYear;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;

    public void fillAddNewCarForm(Car car) {
        inputLocation.sendKeys(car.getCity());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        chooseFuel(car.getFuel());
        inputSeats.sendKeys(Integer.toString(car.getSeats()));
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(Double.toString(car.getPricePerDay()));
        inputAbout.sendKeys(car.getAbout());
    }

    public void fillAddNewCarBlankForm() {
        inputLocation.sendKeys("");
        inputManufacture.sendKeys("");
        inputModel.sendKeys("");
        inputYear.sendKeys("");

        selectFuel.click();
        inputSeats.click();

        inputCarClass.sendKeys("");
        inputSerialNumber.sendKeys("");
        inputPrice.click();

        inputAbout.sendKeys("");
    }

    public void downloadImage(String fileName) {
        inputPhotos.sendKeys(new File("src/test/resources/"
                +fileName).getAbsolutePath());
    }

    public void chooseFuel(Fuel fuel) {
        selectFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();
    }

    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')", btnSubmit);
        clickWait(btnSubmit);
    }
}
