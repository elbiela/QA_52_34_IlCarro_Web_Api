package ui_tests;

import dto.Car;
import dto.User;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import static utils.UserFactory.*;
import static utils.CarFactory.*;

public class LetTheCarWorkTests extends AppManager {
    LetTheCarWorkPage letTheCarWorkPage;
    SoftAssert softAssert = new SoftAssert();
    User user;
    LoginPage loginPage;
    HomePage homePage;
    Car car;
    BasePage basePage;

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
    public void LetTheCarWorkPositiveTest(){
        car = positiveCar();
        letTheCarWorkPage.fillCarFormWOPhoto(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();
    }
}
