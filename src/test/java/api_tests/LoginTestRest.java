package api_tests;

import dto.User;
import io.restassured.response.Response;
import manager.AuthenticationController;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseAPI;

import static utils.PropertiesReader.getProperty;
import static utils.RandomUtils.*;

public class LoginTestRest extends AuthenticationController implements BaseAPI {

    @Test
    public void loginPositiveTest_200() {
        User user = new User(getProperty("login.properties", "email"), getProperty("login.properties", "password"));
        Response response = requestRegLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    public void loginNegativeTest_401(){
        User user = new User(getProperty("login.properties", "email"), "Pass12345!");
        Response response= requestRegLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);





    }
}
