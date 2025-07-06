package api_tests;

import ch.qos.logback.core.subst.Token;
import dto.ErrorMessageDto;
import dto.TokenDto;
import dto.User;
import io.restassured.response.Response;
import manager.AuthenticationController;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseAPI;

import java.time.LocalDate;

import static utils.PropertiesReader.getProperty;
import static utils.RandomUtils.*;

public class LoginTestRest extends AuthenticationController implements BaseAPI {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void loginPositiveTest_200() {
        User user = new User(getProperty("login.properties", "email"), getProperty("login.properties", "password"));
        Response response = requestRegLogin(user, LOGIN_URL);
        System.out.println(response.getStatusLine());
        //Assert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(response.getStatusCode(), 200);
        TokenDto tokenDto = response.body().as(TokenDto.class);
        System.out.println(tokenDto);
        softAssert.assertTrue(tokenDto.toString().contains("token"));
        softAssert.assertAll();


    }

    @Test
    public void loginNegativeTest_401_WrongPassword() {
        User user = new User(getProperty("login.properties", "email"), "Qwerty123");
        Response response = requestRegLogin(user, LOGIN_URL);
        System.out.println(response.getStatusLine());
        softAssert.assertEquals(response.getStatusCode(), 401);
        ErrorMessageDto errorMessageDto= response.body().as(ErrorMessageDto.class);
        System.out.println(errorMessageDto);
        softAssert.assertTrue(errorMessageDto.getMessage().equals("Login or Password incorrect"));
        softAssert.assertTrue(errorMessageDto.getError().equals("Unauthorized"));
        softAssert.assertTrue(errorMessageDto.getPath().contains("login/usernamepassword"));
        System.out.println(LocalDate.now().toString().equals(errorMessageDto.getTimestamp().substring(0,10)));
        softAssert.assertEquals(LocalDate.now().toString(), errorMessageDto.getTimestamp().substring(0,10));
        softAssert.assertAll();

    }


    @Test
    public void loginNegativeTest_401_WrongEmailFormat() {
        User user = new User("QAemail", "Qwerty123!");
        Response response = requestRegLogin(user, LOGIN_URL);
        System.out.println(response.getStatusLine());
        softAssert.assertEquals(response.getStatusCode(), 401);
        ErrorMessageDto errorMessageDto= response.body().as(ErrorMessageDto.class);
        System.out.println(errorMessageDto);
        softAssert.assertTrue(errorMessageDto.getMessage().equals("Login or Password incorrect"));
        softAssert.assertTrue(errorMessageDto.getError().equals("Unauthorized"));
        softAssert.assertTrue(errorMessageDto.getPath().contains("login/usernamepassword"));
        System.out.println(LocalDate.now().toString().equals(errorMessageDto.getTimestamp().substring(0,10)));
        softAssert.assertEquals(LocalDate.now().toString(), errorMessageDto.getTimestamp().substring(0,10));
        softAssert.assertAll();

    }





    @Test
    public void loginNegativeTest_401(){
        User user = new User(getProperty("login.properties", "email"), "Pass12345!");
        Response response= requestRegLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);





    }
}
