package ui_tests;

import data_providers.UserDataProvider;
import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.RegistrationPage;

import static utils.UserFactory.*;

import static utils.PropertiesReader.*;

public class RegistrationTest extends AppManager {
    RegistrationPage registrationPage;
    User user;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToRegistrationPage() {
        new HomePage(getDriver()).clickBtnSignup();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        user = positiveRegisterUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        softAssert.assertTrue(registrationPage.isBtnYallaEnabled(), "validate isBtnYallaEnabled()");
        registrationPage.clickBtnYalla();

        softAssert.assertTrue(registrationPage.isPopUpRegisteredDisplayed(), "validate isPopUpRegisteredDisplayed()");
        softAssert.assertAll();
    }

    @Test(dataProvider = "dataProviderWrongPasswordOrEmailRegistration",
            dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordTest(User user) {
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckbox();
        registrationPage.clickBtnYalla();

        softAssert.assertFalse(registrationPage.isBtnYallaEnabled(), "validate isBtnYallaEnabled()");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain"), "validate isTextInErrorPresent(): Password must contain");
        softAssert.assertAll();
    }
}
