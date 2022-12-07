package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PageReservation {

private WebDriver driver;
private By titleText;
private By passengersDrop;
private By fromDrop;
private By toDrop;
	
	public PageReservation(WebDriver driver) {
		this.driver = driver;
		titleText = By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[1]/font/b");
		passengersDrop = By.xpath("//select[@name='passCount']");
		fromDrop = By.xpath("//select[@name='fromPort']");
		toDrop = By.xpath("//select[@name='toPort']");
		
	}
	public void goToFlights() {
		driver.get("https://demo.guru99.com/test/newtours/reservation.php");
	}
	public void assertPage() {
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Thank you for Loggin"));
	}
	
	public void selectPassengers(int cant) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cantPassengers = wait.until(ExpectedConditions.presenceOfElementLocated(passengersDrop));
		Select selectPassengers = new Select(cantPassengers);
		selectPassengers.selectByVisibleText(Integer.toString(cant));
	}
	
	public void selectFrom(int index) {
		Select selectFrom = new Select(driver.findElement(fromDrop));
		selectFrom.selectByIndex(index);
	}
	
	public void selectToPort(String city) {
		Select selectTo = new Select(driver.findElement(toDrop));
		selectTo.selectByValue(city);
	}
}

