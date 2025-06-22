package pages;

import dto.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddPage extends BasePage {
    public AddPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement inputName;
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement inputLastName;
    @FindBy(xpath = "//input[@placeholder='Phone']")
    WebElement inputPhone;
    @FindBy(xpath = "//input[@placeholder='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@placeholder='Address']")
    WebElement inputAddress;
    @FindBy(xpath = "//input[@placeholder='description']")
    WebElement inputDescription;
    @FindBy(xpath = "//b/..")
    WebElement btnSave;

    public void typeAddNewContactForm(Contact contact){
        inputName.sendKeys(contact.getName());
        inputLastName.sendKeys(contact.getLastName());
        inputPhone.sendKeys(contact.getPhone());
        inputEmail.sendKeys(contact.getEmail());
        inputAddress.sendKeys(contact.getAddress());
        inputDescription.sendKeys(contact.getDescription());
        btnSave.click();
    }
//    public boolean isSaveButtonEnabled() {
//        return driver.findElement(By.xpath("//b/..")).isEnabled();
//    }
    public boolean isSaveButtonEnabled() {
    WebElement btn = driver.findElement(By.xpath("//b/.."));
    try {
        btn.click();
        return true;
    } catch (Exception e) {
        return false;
    }
}
    public WebDriver getDriver() {
        return this.driver;
    }

    public String closeAlertReturnText() {
        try {
            pause(2);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String text = alert.getText();
            alert.accept();
            return text;
        } catch (Exception e) {
            System.out.println("Allert not found: " + e.getMessage());
            return null;
        }
    }




}