package ui_tests;

import dto.User;
import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

import static utils.UserFactory.positiveLoginUser;

public class LoginTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        User user = User.builder()
                .username("test567@test.com")
                .password("Test567!")
                .build();
        loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
    }

    @Test
    public void loginNegativeEmptyAllFieldsTest() {
        loginPage.clickOnFieldLogin();
        loginPage.clickOnFieldPassword();
        loginPage.clickBtnYalla();

        Assert.assertTrue(loginPage
                .validateTextAfterClickOnLoginAndPassword());

        Assert.assertTrue(loginPage.isBtnYallaEnabled());
    }

    @Test
    public void loginNegativeEmptyUsernameTest() {
        User user = positiveLoginUser();
        loginPage.clickOnFieldLogin();
        loginPage.typePassword(user);

        Assert.assertTrue(loginPage
                .validateTextAfterFillingOnlyPasswordField("Email is required"));

        Assert.assertTrue(loginPage.isBtnYallaEnabled());
    }

}
