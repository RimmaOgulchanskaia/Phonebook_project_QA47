package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.RegistrationPage;

public class RegistrationTest extends ApplicationManager {

    @Test
    public void RegistrationPositiveTest(){
        User user = new User("TomGme@gmail.com", "Tom@12345!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.isContactsPresent());

    }

    @Test
    public void RegistrationNegativeTest(){
        User user = new User("TomGme@gmail.com", "Tom@12345!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        registrationPage.closeAlert();
        Assert.assertTrue(registrationPage.isErrorMessagePresent("Registration failed with code 409"));




    }

}
