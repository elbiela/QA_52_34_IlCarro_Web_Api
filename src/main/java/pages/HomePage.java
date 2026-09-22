package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

import static utils.PropertiesReader.*;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignUp;
    @FindBy(xpath = "//a[text()=' Let the car work ']")
    WebElement btnLetTheCarWork;
    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnYearOnCalendar;

    public void typeSearchForm(String city, LocalDate startDate,
                               LocalDate endDate){
        inputCity.sendKeys(city);
        String dates = startDate.getMonthValue() + "/"
                + startDate.getDayOfMonth()+"/"
                + startDate.getYear()+" - "
                + endDate.getMonthValue()+"/"
                + endDate.getDayOfMonth()+"/"
                + endDate.getYear();
        System.out.println(dates);
        inputDates.sendKeys(dates);
    }

    public void typeSearchFormWithCalendar(String city,
                                           LocalDate startDate, LocalDate endDate){
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);
        typeCalendar(endDate);
    }

    private void typeCalendar(LocalDate date){
        btnYearOnCalendar.click();
        String year = Integer.toString(date.getYear());
        WebElement btnYear = driver.findElement
                (By.xpath("//td[@aria-label='"+year+"']"));
        btnYear.click();
        String month = createMonth(date.getMonth().toString());
        WebElement btnMonth = driver.findElement(By.xpath("//td[@aria-label='"+month+" "+year+"']"));
        btnMonth.click();
        String day = String.valueOf(date.getDayOfMonth());
        WebElement btnDay = driver.findElement(By.xpath("//td[@aria-label='"+month+" "+day+", "+year+"']"));
        btnDay.click();

    }

    private String createMonth(String month){
        return new StringBuilder().append(month.substring(0,1)
                .toUpperCase()).append(month.substring(1)
                .toLowerCase()).toString();
    }

    public void typeEmptySearchForm(){
        inputCity.click();
        inputDates.click();
    }

    public void clickBtnLetTheCarWork(){
        btnLetTheCarWork.click();
    }

    public void clickBtnLogin() {
        btnLogin.click();
    }

    public void clickBtnSignUp() {
        btnSignUp.click();
    }

    public void clickBtnYallaWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')", btnYalla);
        clickWait(btnYalla);
    }

    public boolean isBtnYallaEnabled() {
        return btnYalla.isEnabled();
    }

    public void clickInputDates(){
        inputDates.click();
    }

    public void typeCity(String city){
        inputCity.sendKeys(city);
    }
}
