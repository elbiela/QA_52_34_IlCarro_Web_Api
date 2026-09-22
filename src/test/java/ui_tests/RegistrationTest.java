package ui_tests;

import data_providers.UserDataProvider;
import dto.User;
import manager.AppManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;
import utils.TestNGListener;

import static utils.UserFactory.*;
@Listeners(TestNGListener.class)

public class RegistrationTest extends AppManager {
    RegistrationPage registrationPage;
    User user;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToRegistrationPage() {
        logger.info("Start registration test");
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        user = positiveRegisterUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        softAssert.assertTrue(registrationPage.isBtnYallaEnabled(),
                "validate isBtnYallaEnabled()");
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(new PopUpPage(getDriver())
                        .isTextInPopUpMessagePresent("You are logged in"),
                "validate isPopUpRegisteredDisplayed()");
        softAssert.assertAll();
    }

    @Test
    public void registrationPositiveWithJSTest() {
        user = positiveRegisterUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckboxTermsOfUse();
        softAssert.assertTrue(registrationPage.isBtnYallaEnabled(),
                "validate isBtnYallaEnabled()");
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(new PopUpPage(getDriver())
                        .isTextInPopUpMessagePresent("You are logged in"),
                "validate isPopUpRegisteredDisplayed()");
        softAssert.assertAll();
    }

    @Test
    public void registrationPositiveWithActionsTest() {
        user = positiveRegisterUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckboxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in"));
    }

    @Test(dataProvider = "dataProviderWrongPasswordOrEmail",
            dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordTest(User user) {
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        registrationPage.clickBtnYalla();

        softAssert.assertFalse(registrationPage.isBtnYallaEnabled(),
                "validate isBtnYallaEnabled()");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain"),
                "validate isTextInErrorPresent(): Password must contain");
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeEmptyAllFieldsTest() {
        Assert.assertFalse(registrationPage.isBtnYallaEnabled());
    }
}
