package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyzer;

import static utils.PropertiesReader.*;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();
    User user;

    @BeforeMethod
    public void goToLoginPage() {
        logger.info("Start login test");
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();

        Assert.assertTrue(loginPage.isPopUpSuccessLoginDisplayed());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWithClickTest() {
        loginPage.clickOnFieldLogin();
        loginPage.clickOnFieldPassword();
        loginPage.clickBtnYalla();

        softAssert.assertTrue(loginPage
                        .isEmailAndPasswordRequiredMessagesDisplayed(),
                "validate isEmailAndPasswordRequiredMessagesDisplayed()");
        softAssert.assertFalse(loginPage
                        .isBtnYallaEnabled(),
                "validate btnYalla is Disabled");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeEmptyAllFieldsWOClickInFieldsTest() {
        loginPage.clickBtnYalla();

        Assert.assertFalse(loginPage.isBtnYallaEnabled());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWithClickTest2() {
        user = User.builder()
                .username("")
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();

        softAssert.assertFalse(loginPage.isBtnYallaEnabled(),
                "validate isBtnYallaEnabled()");
        softAssert.assertTrue(loginPage
                        .isTextInErrorPresent("Email is required"),
                "validate message: Email is required");
        softAssert.assertTrue(loginPage
                        .isTextInErrorPresent("Password is required"),
                "validate message: Password is required");
        softAssert.assertAll();

    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginNegativeEmptyUsernameFieldTest() {
        user = User.builder()
                .username("")
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();

        softAssert.assertFalse(loginPage
                        .isBtnYallaEnabled(),
                "validate btnYalla is Disabled");
        softAssert.assertTrue(loginPage
                        .isTextInErrorPresent("Email is required"),
                "validate message: Email is required");
        softAssert.assertAll();


    }

    @Test
    public void loginNegativeEmptyPasswordFieldTest() {
        user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();

        softAssert.assertFalse(loginPage
                .isBtnYallaEnabled(), "validate btnYalla is Disabled");
        softAssert.assertTrue(loginPage
                .isTextInErrorPresent("Password is required"), "validate message: Password is required");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeWrongEmailTest() {
        user = User.builder()
                .username(getProperty("base.properties", "wrongEmail"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();

        Assert.assertTrue(loginPage.isPopUpLoginFailedDisplayed());

    }

    @Test
    public void loginNegativeWrongPasswordTest() {
        user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "wrongPassword"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();

        Assert.assertTrue(loginPage.isPopUpLoginFailedDisplayed());
    }

}
