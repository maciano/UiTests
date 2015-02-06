package com.Tesis.tests;

import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.Tesis.commons.DataTestHelper;
import com.Tesis.commons.UITestHelper;
import com.Tesis.pages.*;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class SmokeTestMagento {
	private WebDriver driver;
	private UITestHelper ui;
	private DataTestHelper dataSet;
	int numBrowser=0;

	@Parameters({ "serverHost", "serverPort" })
	@BeforeClass
	public void beforeClass(String serverHost, String serverPort) {
		ui = new UITestHelper();
		ui.setGridHub(serverHost, serverPort);
		ui.openProperties();
		
		dataSet= new DataTestHelper();
		dataSet.openProperties();
	}

	@Parameters({ "browser", "environmentTested" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String browser, String environmentTested) throws MalformedURLException {
		
		driver = new RemoteWebDriver(new URL(ui.getGridHub()), ui.getDesiredCapability(browser));
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ui.getProperty("generalWaitSeconds")),TimeUnit.SECONDS);

		//Maximizamos la ventana
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension screenResolution = new Dimension((int) t.getScreenSize().getWidth(), (int) t.getScreenSize().getHeight());
		driver.manage().window().setSize(screenResolution);
		driver.navigate().to(environmentTested);
		driver.manage().deleteAllCookies();
	}
	
	@Parameters({ "browser"})
	@Test
	public void testSmoke (String browser) {
		
		System.out.println("Magento-SmokeTest() in "+browser);
		
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);

		CustomerLoginPage customerPage=homepage.irCustomerLoginPage();	
		CreateAccount createNewUser = customerPage.createAccount();
		MyDashboard mydashboard=createNewUser.createAccount(ui.generateString(7),ui.generateString(7),ui.generateEmail(8),ui.generatePassword(8));
		
		AddressPage addAddress = mydashboard.irACargarDirecion();
		addAddress.cargarDireccion(ui.generateAddress(15), ui.generateNumber(8), ui.generateAddress(10), 10, ui.generateNumber(4));
		
		ProductList listaProd= mydashboard.irProductosFurniture();
		ProductDescription descripcionProd = listaProd.comprarProducto(1,3);
		ShoppingCart carrito = descripcionProd.elegirCantidad("3");
		CheckOutInformation checkoutInf = carrito.irAlCheckOut();
		ThanksPage paginaFinal = checkoutInf.seleccionarCheckOutInfo();
		HomePage home = paginaFinal.salir();
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
