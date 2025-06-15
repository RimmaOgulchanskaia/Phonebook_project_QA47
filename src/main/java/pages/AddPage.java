package pages;

import dto.Contact;
import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AddPage extends BasePage{
    public AddPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
    @FindBy(css = "input[placeholder='Name']")
    WebElement inputName;
    @FindBy(css = "input[placeholder='Last Name']")
    WebElement inputLastName;
    @FindBy(css = "input[placeholder='Phone']")
    WebElement inputPhone;
    @FindBy(css = "input[placeholder='email']")
    WebElement inputEmail;
    @FindBy(css = "input[placeholder='Address']")
    WebElement inputAddress;
    @FindBy(css = "input[placeholder='description']")
    WebElement inputDescription;
    @FindBy(xpath = "//button[.//b[text()='Save']]")
    WebElement btnSave;

    public void clickBtnSave(){
        btnSave.click();

    }


    public void typeAddContactForm(Contact contact) {
        inputName.sendKeys(contact.getName());
        inputLastName.sendKeys(contact.getLastName());
        inputPhone.sendKeys(contact.getPhone());
        inputEmail.sendKeys(contact.getEmail());
        inputAddress.sendKeys(contact.getAddress());
        inputDescription.sendKeys(contact.getDescription());

    }
}

