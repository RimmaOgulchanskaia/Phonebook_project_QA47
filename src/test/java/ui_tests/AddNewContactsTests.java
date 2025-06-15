package ui_tests;

import dto.Contact;
import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.HeaderMenuItem;

import static pages.BasePage.*;
import static utils.RandomUtils.*;

public class AddNewContactsTests extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;

    @BeforeMethod
    public void login(){
        User user = new User("qa_mail@mail.com", "Qwerty123!");
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm(user);
        //contactsPage = clickButtonHeader(HeaderMenuItem.ADD);
        AddPage addPage = clickButtonHeader(HeaderMenuItem.ADD);

    }

    @Test
    public void addNewContactPositiveTest(){
        Contact contact = Contact.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .phone("0123456789")
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc "+ generateString(15))
                .build();
        AddPage addPage = new AddPage(getDriver());
        addPage.typeAddContactForm(contact);
        addPage.clickBtnSave();





    }
}