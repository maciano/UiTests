package com.Tesis.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.Tesis.commons.UITestHelper;


public class LoginTest {
	private WebDriver driver;
	private UITestHelper ui;

	@Parameters({ "serverHost", "serverPort" })
	@BeforeClass
	public void beforeClass(String serverHost, String serverPort) {
		ui = new UITestHelper();
		ui.setGridHub(serverHost, serverPort);
		ui.openProperties();
	}

	@Parameters({ "browser", "environmentTested" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String browser, String environmentTested) throws MalformedURLException {
		driver = new RemoteWebDriver(new URL(ui.getGridHub()), ui.getDesiredCapability(browser));
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ui.getProperty("generalWaitSeconds")),TimeUnit.SECONDS);

		// Go to site
		driver.navigate().to(environmentTested);
	}

	@Test
	public void testLoginCorrect() {
		// Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
    //    WebDriver driver = new FirefoxDriver();

        // And now use this to visit Google
      //  driver.get("http://www.google.com");
      //  driver.navigate().to(environmentTested);
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
	}	

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		if (!result.isSuccess()) { 
			ui.takeScreenShoot(driver, result.getMethod());
		}
		// Quit environment.
		driver.quit();
	}
	
	@AfterClass
	public void afterClass() {
		 ui.closeProperties();
	}
}
