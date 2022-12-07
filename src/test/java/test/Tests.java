package test;


import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import helpers.Screenshooter;
import helpers.WebDriverManager;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageReservation;

public class Tests {
	private WebDriver driver;
	ArrayList<String> tabs;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/newtours/");
		JavascriptExecutor javaScriptExecution = (JavascriptExecutor)driver;
		String googleWindow = "window.open('https://www.google.com')";
		javaScriptExecution.executeScript(googleWindow);
		tabs = new ArrayList<String>(driver.getWindowHandles());
	} 
	
	@Test(priority = 1)
	public void pruebaUno() {
		WebDriverManager.setWindowSize(driver, "fullscreen");
		PageLogin loginPage = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		loginPage.login("user", "user");
		pageReservation.assertPage();
	}
/*
	@Test(priority = 2)
	public void pruebaDos() {
		WebDriverManager.setWindowSize(driver, "maximized");
		driver.switchTo().window(tabs.get(1)).get("https://youtube.com/user/Draculinio");;
		driver.switchTo().window(tabs.get(0));
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.login("mercury", "mercury");
		pageLogon.assertLogonPage();

	}

	@Test(priority = 3)
	public void PruebaTres() {
		WebDriverManager.setWindowSize(driver, "maximized");
		PageLogin loginPage = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		Helpers helper = new Helpers();
		loginPage.login("mercury", "mercury");
		pageReservation.assertPage();
		pageReservation.goToFlights();
		helper.sleepSeconds(3);
		pageReservation.selectPassengers(3);
		pageReservation.selectFrom(3);
		pageReservation.selectToPort("London");
	}

	@Test(priority = 4)
	public void pruebaCantidadCampos() {
		WebDriverManager.setWindowSize(driver, 400, 400);
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.verifyFields();
	}
*/
	@AfterMethod
	public void tearDown(ITestResult resul) {
		if (!resul.isSuccess()) {
			Screenshooter.takeScreenshot("Error", driver);
		}
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0)).close();
	}

}
