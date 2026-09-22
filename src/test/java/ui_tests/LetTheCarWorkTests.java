package ui_tests;

import dto.Car;
import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.time.Year;

import static utils.UserFactory.*;
import static utils.CarFactory.*;

public class LetTheCarWorkTests extends AppManager {
    LetTheCarWorkPage letTheCarWorkPage;
    User user;
    LoginPage loginPage;
    HomePage homePage;
    Car car;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLetTheCarWorkPageWithAuthorization() {
        logger.info("Start add car test");

        homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();

        loginPage = new LoginPage(getDriver());
        user = positiveLoginUser();

        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        loginPage.clickWaitBtnLoginSuccess();

        homePage = new HomePage(getDriver());

        homePage.clickBtnLetTheCarWork();

        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
    }

    @Test
    public void addNewCarPositiveTest(){
        car = positiveCar();
        System.out.println(car);
        letTheCarWorkPage.fillAddNewCarForm(car);
        letTheCarWorkPage.downloadImage("cat2.jpg");
        letTheCarWorkPage.clickBtnSubmitWithJS();

        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"city\":\"must not be blank\"}"));
    }

    @Test
    public void addNewCarNegativeEmptyAllFieldsTest(){
        letTheCarWorkPage.clickBtnSubmitWithJS();

        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"manufacture\":\"must not be blank\",\"serialNumber\":\""));
    }

    @Test
    public void addNewCarNegativeBlankAllFieldsTest(){
        letTheCarWorkPage.fillAddNewCarBlankForm();
        softAssert.assertTrue(letTheCarWorkPage
                .isTextInErrorPresent("Wrong address"));
        softAssert.assertTrue(letTheCarWorkPage
                .isTextInErrorPresent("Model is required"));
        letTheCarWorkPage.clickBtnSubmitWithJS();
        softAssert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"manufacture\":\"must not be blank\",\"serialNumber\":\""));
    }

    @Test
    public void addNewCarNegativeBlankModelTest() {
        car = positiveCar();
        car.setModel("");

        letTheCarWorkPage.fillAddNewCarForm(car);
        softAssert.assertTrue(letTheCarWorkPage
                .isTextInErrorPresent("Model is required"));
        letTheCarWorkPage.clickBtnSubmitWithJS();
        softAssert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"city\":\"must not be blank\",\"model\":\"must not be blank\"}"));
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeWrongYearTest() {
        car = positiveCar();
        car.setYear(String.valueOf(Year.now().getValue()+1));
        System.out.println(car);
        letTheCarWorkPage.fillAddNewCarForm(car);
        letTheCarWorkPage.downloadImage("cat2.jpg");
        softAssert.assertTrue(letTheCarWorkPage.
                isTextInErrorPresent("Wrong year"));
        letTheCarWorkPage.clickBtnSubmitWithJS();
        softAssert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"city\":\"must not be blank\"}"));
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeWrongYearNotDigitTest() {
        car = positiveCar();
        car.setYear("a");
        System.out.println(car);
        letTheCarWorkPage.fillAddNewCarForm(car);
        letTheCarWorkPage.downloadImage("cat2.jpg");
        Assert.assertTrue(letTheCarWorkPage.
                isTextInErrorPresent("Year required"));

    }
}
