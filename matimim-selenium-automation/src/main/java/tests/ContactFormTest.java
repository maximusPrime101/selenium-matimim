package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import pages.ContactForm;

public class ContactFormTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private ContactForm contactForm;

    @BeforeTest
    public void setup() throws InterruptedException {
        contactForm = new ContactForm(driver, wait);
        driver = contactForm.chromeDriverConnection();
        contactForm.visit("https://www.matimim.co.il//");
    }

    @Test
    public void registerTest() throws InterruptedException {
        contactForm.submitContactForm();
    }

    @AfterTest
    public void endTest() throws InterruptedException {
        //Thread.sleep(2000);
        //driver.quit();
    }
}
