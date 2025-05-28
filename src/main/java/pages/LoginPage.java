package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);


    }
    @FindBy(xpath ="//input[@placeholder='Email']")
    WebElement inputEmail;
    @FindBy(xpath ="//*[@name='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//*[@name='registration']")
    WebElement btnRegistration;

    public void typeLoginForm(String email, String password){
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        btnRegistration.click();

    }






}
