package ui_tests;

import data_provider.ContactDP;
import dto.Contact;
import dto.User;
import manager.ApplicationManager;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static pages.BasePage.*;
import static utils.RandomUtils.*;

@Listeners(TestNGListener.class)

public class AddNewContactsTests extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddPage addPage;
    int sizeBeforeAdd;
    String existPhone;


    @BeforeMethod
    public void login() {
        User user = new User("qa_mail@mail.com", "Qwerty123!");
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm(user);
        contactsPage = new ContactsPage(getDriver());
        sizeBeforeAdd = contactsPage.getContactsListSize();
        existPhone=contactsPage.getPhoneFromList();
        addPage = clickButtonHeader(HeaderMenuItem.ADD);
    }


    @Test(invocationCount = 1)
    public void addNewContactPositiveTest() {
        Contact contact = Contact.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .phone("0123456789")
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactForm(contact);
        int sizeAfterAdd = contactsPage.getContactsListSize();
        System.out.println(sizeBeforeAdd + "X" + sizeAfterAdd);
        Assert.assertEquals(sizeBeforeAdd + 1, sizeAfterAdd);
    }

    @Test(dataProvider = "addNewContactDP", dataProviderClass = ContactDP.class)
    public void addNewContactPositiveTestDP(Contact contact) {
        addPage.typeAddNewContactForm(contact);

    }

    @Test(dataProvider = "addNewContactFile", dataProviderClass = ContactDP.class)
    public void addNewContactPositiveTestFile(Contact contact) {
        logger.info("test data->" + contact);
        addPage.typeAddNewContactForm(contact);

    }

    @Test
    public void addNewContactPositiveTestValidateContactNamePhone() {
        Contact contact = Contact.builder()
                .name("Name-" + generateString(8))
                .lastName(generateString(10))
                .phone(generatePhone(10))
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactForm(contact);
        Assert.assertTrue(contactsPage.validateContactNamePhone(contact.getName(), contact.getPhone()));
    }

    @Test
    public void addNewContactNegativeTestEmptyName() {
        Contact contact = Contact.builder()
                .name("")
                .lastName(generateString(10))
                .phone(generatePhone(10))
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactForm(contact);
        pause(2);
        Assert.assertTrue(addPage.validateURL("add"));
    }

    @Test
    public void addNewContactNegativeTestEmptyName2() {
        Contact contact = Contact.builder()
                .name("")
                .lastName(generateString(10))
                .phone(generatePhone(10))
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactForm(contact);
        pause(2);
        Assert.assertTrue(addPage.isUrlNotContains("contacts"));

    }



    @Test
    public void addNewContactNegativeTestEmptyLastName() {
        Contact contact = Contact.builder()
                .name("Name-" + generateString(8))
                .lastName("")
                .phone(generatePhone(10))
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactForm(contact);
        pause(2);
        String currentUrl = addPage.getDriver().getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("/add") || currentUrl.endsWith("/add"),
                ""
        );
    }

    @Test
    public void addNewContactNegativeTestEmptyPhone() {
        Contact contact = Contact.builder()
                .name("Name-" + generateString(8))
                .lastName(generateString(10))
                .phone(generatePhone(0))
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactForm(contact);
        pause(2);
        Alert alert = getDriver().switchTo().alert();
        String alertText = alert.getText();
        Assert.assertTrue(alertText.contains(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"), "");
        alert.accept();


    }

    @Test
    public void addNewContactNegativeTestEmptyPhone2() {
        Contact contact = Contact.builder()
                .name("Name-" + generateString(8))
                .lastName(generateString(10))
                .phone(generatePhone(0))
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactForm(contact);
        pause(2);
        //Assert.assertEquals(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!", addPage.closeAlertReturnText());
        Assert.assertTrue(addPage.closeAlertReturnText().contains(" Phone not valid: Phone number must"));
    }

    @Test
    public void addNewContactNegativeTestEmptyEmail() {
        Contact contact = Contact.builder()
                .name("Name-" + generateString(8))
                .lastName("")
                .phone(generatePhone(10))
                .email("")
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactForm(contact);
        pause(2);
        String currentUrl = addPage.getDriver().getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("/add") || currentUrl.endsWith("/add"),
                ""
        );
    }


    @Test(invocationCount = 1)
    public void addNewContactNegativeTest_existPhone() {
        Contact contact = Contact.builder()
                .name(generateString(10))
                .lastName(generateString(10))
                .phone(existPhone)
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactForm(contact);
        //Assert.assertTrue(addPage.isUrlNotContains("contacts"));
    }
//    @Test
//    public void addNewContactNegativeTestInvalidPhone(){
//        Contact contact = Contact.builder()
//                .name(generateString(8))
//                .lastName(generateString(10))
//                .phone("ttt")
//                .email(generateEmail(10))
//                .address(generateString(10))
//                .description( generateString(15))
//                .build();
//        addPage.typeAddNewContactForm(contact);
//        Assert.assertEquals(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"
//                , addPage.closeAlertReturnText());
//        String alertText = addPage.closeAlertReturnText();
//        Assert.assertNotNull(alertText, "!!!");
//        Assert.assertTrue(alertText.contains("Phone"), "Alert doesnt contain 'Phone'");
//
//
//    }

    @Test
    public void addNewContactNegativeTest_InvalidEmail(){
        Contact contact = Contact.builder()
                .name(generateString(8))
                .lastName(generateString(10))
                .phone(generatePhone(9))
                .email("QAmail.ru")
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactForm(contact);
        Assert.assertTrue(addPage.closeAlertReturnText().contains("Email not valid:"));

    }




}
