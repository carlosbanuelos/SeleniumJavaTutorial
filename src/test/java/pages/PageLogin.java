package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageLogin {
	private WebDriver driver;
	private By userField;
	private By passwordField;
	private By loginButton;
	private By fields;
	
	
	public PageLogin(WebDriver driver) {
		this.driver = driver;
		userField = By.name("userName");
		passwordField = By.name("password");
		loginButton = By.name("submit");
		fields = By.tagName("input");
	}
	
	public void login(String usr, String pwd) {
		driver.findElement(userField).sendKeys(usr);
		driver.findElement(passwordField).sendKeys(pwd);
		driver.findElement(loginButton).click();
		/*File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("LOGIN " +System.currentTimeMillis()+ ".png"));
		}catch(IOException e){
			e.printStackTrace();
		}*/
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void fields_login(String user, String pass) {
		List<WebElement> loginFields = driver.findElements(fields);
		loginFields.get(1).sendKeys(user);
		loginFields.get(2).sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void verifyFields() {
		List<WebElement> loginFields = driver.findElements(fields);
		System.out.println(loginFields.size());
		Assert.assertTrue(loginFields.size()==4);
	}
	
}
