package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WDListener;

import java.time.Duration;
import java.time.LocalDate;

public class ApplicationManager {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public Logger logger = LoggerFactory.getLogger(ApplicationManager.class);


    @BeforeMethod(alwaysRun = true)
    public void setup(){
        //logger.info("Start test " + LocalDate.now());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        //следующие две строки имплементация WDListener!!!
        WebDriverListener webDriverListener= new WDListener();
        driver= new EventFiringDecorator<>(webDriverListener).decorate(driver);
    }
    @AfterMethod (enabled=true, alwaysRun = true)
    public void tearDown(){
        //logger.info("Stop test");
        if(driver != null)
            driver.quit();
    }
}
