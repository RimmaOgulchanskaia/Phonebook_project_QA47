package pages;

import dto.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement inputEmail;

    @FindBy(xpath = "//*[@name='password']")
    WebElement inputPassword;

    @FindBy(xpath = "//*[@name='registration']")
    WebElement btnRegistration;

    @FindBy(xpath = "//div[@class='login_login__3EHKB']/div")
    WebElement errorMessageRegistration;

    public void typeRegistrationForm(User user){
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        btnRegistration.click();
    }

    public void closeAlert () {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.accept();
    }

    public boolean isErrorMessagePresent(String message){
       return isTextElementPresent(errorMessageRegistration, message);
    }
}
