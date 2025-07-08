package api_tests;

import dto.ContactsDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import manager.ContactController;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static utils.PropertiesReader.getProperty;

public class GetUserContactsTests extends ContactController {

    @Test
    public void getAllUserContactsPositiveTest(){
        Response response= getAllUserContacts();
        System.out.println(response.getStatusLine());
        ContactsDto contactsDto= new ContactsDto();
        if(response.getStatusCode()==200){
            contactsDto= response.body().as(ContactsDto.class);
        }
        System.out.println("--->" + contactsDto.getContacts().length);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    public void getAllUserContactsNegativeTest_Unauthorized_InvalidToken() {
        Response response = given()
                .baseUri(getProperty("login.properties", "baseUri"))
                .accept(ContentType.JSON)
                .header("Authorization", "Invalid_token")
                .get(ADD_NEW_CONTACT_URL)
                .thenReturn();

        System.out.println(response.getStatusLine());

        Assert.assertEquals(response.getStatusCode(), 401);
    }

}
