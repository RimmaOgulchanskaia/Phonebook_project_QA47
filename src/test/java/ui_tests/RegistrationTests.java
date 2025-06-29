
package ui_tests;

import dto.User;
import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyzer;
import utils.TestNGListener;

import static utils.RandomUtils.*;

@Listeners(TestNGListener.class)

    public class RegistrationTests extends ApplicationManager {
        HomePage homePage;
        LoginPage loginPage;
        @BeforeMethod
        public void goToRegistrationPage(){
            homePage = new HomePage(getDriver());
            homePage.clickBtnLoginHeader();
            loginPage = new LoginPage(getDriver());
        }
        //      BeforeMethod(App)   BeforeMethod(Reg)  Test  AfterMethod(App)

        @Test(retryAnalyzer = RetryAnalyzer.class)
        public void registrationPositiveTest(){
            User user = new User(generateEmail(10), "Password123!");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.isNoContactMessagePresent("Add new by clicking on Add in NavBar!"));
        }

        @Test
        public void registrationNegativeTest_duplicateUser(){
            User user = new User(generateEmail(10), "Password123!");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            if(loginPage.isNoContactMessagePresent("Add new by clicking on Add in NavBar!")){
                loginPage.logOut();
                loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
                Assert.assertTrue(loginPage.closeAlertReturnText()
                        .contains("User already exist"));
            }else {
                Assert.fail("wrong registration with user " + user.toString());
            }
        }

        @Test
        public void registrationNegativeTest_wrongPassword(){
            User user = new User(generateEmail(10), "Password123");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Password must contain at least one special symbol from [‘$’,’~’,’-‘,’_’]!"));
        }


        @Test
        public void registrationNegativeTestEmptyAll(){
            User user = new User("", "");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));

        }

        @Test
        public void registrationNegativeTestEmptyEmail(){
            User user = new User("", "Password_123");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));

        }

        @Test
        public void registrationNegativeTestEmptyPassword(){
            User user = new User(generateEmail(10), "");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));

        }
        @Test
        public void registrationNegativeTestEmailWithoutAtSign(){
            User user = new User("Rossagmail.com", "Password_123");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));

        }
        @Test
        public void registrationNegativeTestWithoutUppercaseLetter(){
            User user = new User("Rossa@gmail.com", "password_123");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));

        }
        @Test
        public void registrationNegativeTestWithoutLowercaseLetter(){
            User user = new User("Rossa@gmail.com", "PASSWORD_123");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));

        }
        @Test
        public void registrationNegativeTestWithoutDigit(){
            User user = new User("Rossa@gmail.com", "Password_-_");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));

        }

        @Test
        public void registrationNegativeTestWithoutSymbol(){
            User user = new User("Rossa@gmail.com", "Password123");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));

        }

        @Test
        public void registrationNegativeTestWithSpace(){
            User user = new User("          ", "           ");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));

        }
        @Test
        public void registrationNegativeTestWithSpaceInEmail(){
            User user = new User("          ", "Password_123");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));

        }

        @Test
        public void registrationNegativeTestWithSpaceInPassword(){
            User user = new User(generateEmail(10), "           ");
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));

        }

        @Test
        public void registrationNegativeTestWithSpaceInPasswordLombok(){
            UserLombok user = UserLombok.builder()
                    .username(generateEmail(10))
                    .password("           ")
                    .build();
            loginPage.typeRegistrationForm(user.getUsername(),user.getPassword());
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("Wrong email or password format"));



        }

    }


