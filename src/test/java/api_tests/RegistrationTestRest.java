package api_tests;

import dto.User;
import io.restassured.response.Response;
import manager.AuthenticationController;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseAPI;

import static utils.RandomUtils.*;

public class RegistrationTestRest extends AuthenticationController implements BaseAPI {

    @Test(groups = "smoke")
    public void registrationPositiveTest_200(){
        User user = new User(generateEmail(10), "Password12345!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test
    public void registrationPositiveTest_getBody(){
        User user = new User(generateEmail(10), "Password12345!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.body().print());

    }



    @Test
    public void registrationNegativeTest_wrongMail_400(){
        User user = new User(generateString(10), "Password12345!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    //@gmail.com; rtyuio@e; ppp@.com; qwerty@@rui.csd; rtyuio@e.; qwerty @gmail.com; qwerty@мама.com

    @Test
    public void registrationNegativeTest_wrongPassword_WithoutSymbol400(){
        User user = new User(generateString(10), "Password12345");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void registrationNegativeTest_wrongPassword_WithoutDigit400(){
        User user = new User(generateString(10), "Password!!!!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void registrationNegativeTest_wrongPassword_WithoutLetters400(){
        User user = new User(generateString(10), "1234567890!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }
    @Test
    public void registrationNegativeTest_wrongPassword_WithoutCapitalLetter400(){
        User user = new User(generateString(10), "password12345!!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void registrationNegativeTest_wrongPassword_WithoutLowercaseLetter400(){
        User user = new User(generateString(10), "PASSWORD12345!!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void registrationNegativeTest_wrongLittlePassword_400(){
        User user = new User(generateString(10), "Pas1!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void registrationNegativeTest_wrongEmptyPassword_400(){
        User user = new User(generateString(10), "");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }
    @Test
    public void registrationNegativeTest_wrongNullPassword_400(){
        User user = new User(generateString(10), null);
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }


    @Test
    public void registrationNegativeTest_wrongSpacePassword_400(){
        User user = new User(generateString(10), "              ");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void registrationNegativeTestWrongEmailWithoutStrydel_400(){
        User user = new User("Flaffyy123gmail.com", "Password12345!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void registrationNegativeTestWrongEmailWithoutSymbolBeforeStrydel_400(){
        User user = new User("@gmail.com", "Password12345!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }
    @Test
    public void registrationNegativeTestWrongEmailWithoutSymbolAfterStrydel_400(){
        User user = new User("flaffy@", "Password12345!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);
    }
    @Test
    public void registrationNegativeTestWrongEmailWithoutDomen_400(){
        User user = new User("@mama.com", "Password12345!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void registrationNegativeTestWrongEmailWithoutCOM_400(){
        User user = new User("@mama", "Password12345!");
        Response response= requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);

    }









}
