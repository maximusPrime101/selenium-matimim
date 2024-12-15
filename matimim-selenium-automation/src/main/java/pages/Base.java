package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

public class Base {
	private WebDriver driver;
	private WebDriverWait wait;

	public Base(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriver chromeDriverConnection() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofMillis(3000));
		return driver;
	}

	public void waitUntilElementLocated(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String getText(By locator) {
		return findElement(locator).getText();
	}

	public WebElement type(By locator, String inputText) {
		findElement(locator).sendKeys(inputText);
		return findElement(locator);
	}

	public void click(By locator) {
		findElement(locator).click();
	}
	
	public void clickElementInList(By locator, int indx) {
		findElements(locator).get(0).click();
	}

	public boolean isDisplayed(By locator) {
		try {
			return findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void selectFromDropDownListByValue(By locator, String value) {
		Select select = new Select(findElement(locator));
		select.selectByValue(value);
	}

	public void visit(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
	}
	
	public void waitUntilFieldIsFilled(By locator, Duration duration){
		WebDriverWait wait = new WebDriverWait(driver, duration); 
		wait.until(new ExpectedCondition<Boolean>() { 
			@Override 
			public Boolean apply(WebDriver driver) { 
				String value = findElement(locator).getDomProperty("value"); 
				try { 
					// Wait for a brief moment to ensure typing has stopped 
					Thread.sleep(500); 
				} catch (InterruptedException e) { 
					e.printStackTrace(); 
				} 
				// Check if the value has not changed 
				return value.equals(findElement(locator).getDomProperty("value"));
			}
		});
	}
	
	public void waitUntilFieldsAreFilled(List<By> fieldLocators, Duration duration) {
		Duration durationForEachField = duration.dividedBy(fieldLocators.size());
		for (By fieldLocator : fieldLocators) {
			waitUntilFieldIsFilled(fieldLocator, durationForEachField);
		}
	}
		
}
