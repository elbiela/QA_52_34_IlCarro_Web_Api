package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;

import java.time.LocalDate;

public class SearchCarTests extends AppManager {
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void openHomePage(){
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarPositiveTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.isUrlContainsText("results"));

    }

    @Test
    public void searchCarWithCalendarPositiveTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.isUrlContainsText("results"));

    }


    @Test
    public void searchCarNegativeEmptyAllFieldsTest(){
        homePage.typeEmptySearchForm();
        softAssert.assertTrue(homePage.isTextInErrorPresent("City is required"),"validate message error city is required");
        softAssert.assertTrue(homePage.isTextInErrorPresent("Dates are required"),"validate message error dates is required");
        softAssert.assertFalse(homePage.isBtnYallaEnabled(),"validate is btn Yalla disabled");
        softAssert.assertAll();
    }

    @Test
    public void searchCarNegativeEmptyDatesFieldTest(){
        String city = "Haifa";
        homePage.typeCity(city);
        homePage.clickInputDates();
        softAssert.assertTrue(homePage.isTextInErrorPresent("Dates are required"),"validate message error dates is required");
        softAssert.assertFalse(homePage.isBtnYallaEnabled(),"validate is btn Yalla disabled");
        softAssert.assertAll();
    }

    @Test
    public void searchCarNegativeEndDateBeforeStartDateTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().minusDays(1);
        homePage.typeSearchForm(city,startDate,endDate);
        homePage.clickBtnYallaWithJS();
        softAssert.assertTrue(homePage.isTextInErrorPresent("City is required"), "validate message error city is required");
        softAssert.assertTrue(homePage.isTextInErrorPresent("You can't pick date before today"), "validate message error date before today");
        softAssert.assertAll();
    }

    @Test
    public void searchCarNegativeSameDateTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't book car for less than a day"));
    }

    @Test
    public void searchCarNegativeMoreOneYearTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusYears(1).plusDays(1);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't pick date after one year"));
    }


}
