package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactForm extends Base{

	public ContactForm(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	private By blogLink = By.cssSelector("a[href='https://www.matimim.co.il/blog/']");
	
    private By nameField = By.name("your-name");
    private By phoneField = By.name("tel-305");
    private By emailField = By.name("edit-email");
    private By messageField = By.name("edit-message");
    private By sendButton = By.cssSelector("edit-actions-submit");
    
    public void submitContactForm() throws InterruptedException {
    	click(blogLink);
        
    	type(nameField, "John Doe");
    	type(phoneField, "0547762556");
        type(emailField, "john_doe@gmail.com");
        type(messageField, "Hello, this is a test. Thank you!");
        waitUntilFieldsAreFilled(List.of(nameField, phoneField, emailField, messageField), Duration.ofMillis(12000));
        click(sendButton);
    }

}
