package api_tests;

import dto.Contact;
import dto.ResponseMessageDto;
import dto.TokenDto;
import io.restassured.response.Response;
import manager.ContactController;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static utils.RandomUtils.generateEmail;
import static utils.RandomUtils.generateString;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class DeleteContactByIDTests extends ContactController {

    Contact contact;

    @BeforeClass(alwaysRun = true)
    public void createContact(){
        contact = Contact.builder()
                .name("123"+generateString(5))
                .lastName(generateString(10))
                .phone("0123456789")
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        Response response = addNewContactRequest(contact, tokenDto);
        if(response.getStatusCode()!= 200)
            System.out.println("Contact not created");
        else{
            ResponseMessageDto responseMessageDto= response.body().as(ResponseMessageDto.class);
            contact.setId(responseMessageDto.getMessage().split("ID: ")[1]);


        }
    }
    @Test(groups = "smoke")
    public void deleteContactByIDPositiveTest(){
        Response response= deleteContact(contact, tokenDto);
        response
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("ResponseMessageDtoShema.json"));
    }


    @Test
    public void deleteContactByIDNegativeTest_InvalidToken(){
        TokenDto tokenDto1= TokenDto.builder().token("invalid").build();
        Response response= deleteContact(contact, tokenDto1);
        response
                .then()
                .log().all()
                .statusCode(401)
                .body(matchesJsonSchemaInClasspath("ErrorMessageDtoSchema.json"));

    }

}
