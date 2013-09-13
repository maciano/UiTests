package com.Tesis.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestNGMethod;
import org.testng.Reporter;

public class UITestHelper {
	private Properties prop;
	private InputStream is;
	private String gridHub;

	public UITestHelper(){
	}
	
	public void openProperties() {
		prop = new Properties();
		try {
			String location=getClass().getResource("/com/tesis/configurations/objectsMap.properties").toString();
			is = new FileInputStream(location.substring(6, location.length()));
			prop.load(is);
		} catch (IOException ioe) {
			System.out.println("System is not finding the file: objectsMap.properties");
			ioe.printStackTrace();
		}
	}
	
	
	public void closeProperties() {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
	}	
	
	
	public String getProperty(String key) {
		return prop.getProperty(key);
	}

	
	public void setGridHub(String serverHost, String serverPort) {
		gridHub = "http://" + serverHost + ":" + serverPort + "/wd/hub";
	}
	
	public String getGridHub(){
		return gridHub;
	}
	
	
	public WebElement explicitWait(WebDriver driver, String object){
		
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.visibilityOfElementLocated(By.id(object)));
		return myDynamicElement;
		
	}
	
	public DesiredCapabilities getDesiredCapability(String browser) {
		DesiredCapabilities capability = null;

		if (browser.equalsIgnoreCase("firefox")) {
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}
		
		if (browser.equalsIgnoreCase("htmlunit")) {
			capability = DesiredCapabilities.htmlUnit();
			capability.setBrowserName("htmlunit");
			capability.setPlatform(Platform.ANY);
		}

		if (browser.equalsIgnoreCase("iexplore")) {
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("iexplore");
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capability
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
		}

		if (browser.equalsIgnoreCase("chrome")) {
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}
		
		if (browser.equalsIgnoreCase("opera")) {
			capability = DesiredCapabilities.opera();
			capability.setCapability("opera.binary", "C:\\Program Files (x86)\\Opera\\15.0.1147.141\\opera.exe");
			capability.setBrowserName("opera");
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}
		
		if (browser.equalsIgnoreCase("android")) {
			capability = DesiredCapabilities.android();
			capability.setBrowserName("android");
			capability.setPlatform(org.openqa.selenium.Platform.ANDROID);
		}
		
		capability.setJavascriptEnabled(true);
		return capability;
	}
	
	public List<String> listWindows(WebDriver driver) {
		// Obtain ids of windows.
		List<String> windows = new ArrayList<String>();

		windows.clear();
		Set<String> windowsId = driver.getWindowHandles();
		Iterator<String> it = windowsId.iterator();
		while (it.hasNext()) {
			windows.add(it.next());
		}
		return windows;
	}
	
	public void waitMoment(String milliseconds) throws NumberFormatException, InterruptedException{
		Thread.sleep(Long.parseLong(milliseconds));
	}
	
	
	public void takeScreenShoot(WebDriver driver, ITestNGMethod testMethod) throws Exception{
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		
		String nameScreenshot = testMethod.getXmlTest().getParameter("browser").toUpperCase()+"_"+testMethod.getTestClass().getRealClass().getSimpleName()+"_"+testMethod.getMethodName();
		String path = getPath(nameScreenshot);
		FileUtils.copyFile(screenshot, new File(path));
		Reporter.log("<a href=\""+path+"\">"+this.getFileName(nameScreenshot)+"</a>");
		//System.out.println("<a href=\""+path+"\">"+this.getFileName(nameScreenshot)+"</a>");
	}

	private String getFileName(String nameTest) throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		Date date = new Date();
//		InetAddress ownIP = InetAddress.getLocalHost();
		return dateFormat.format(date) + "_" +nameTest + ".png";
	}

	private String getPath(String nameTest) throws IOException{
		File directory = new File(".");
		String newFileNamePath = directory.getCanonicalPath() + "\\test-output\\screenShots\\" + getFileName(nameTest);
		return newFileNamePath;
	}
	
	
//	public static String generateData(int length, String type)
//	{
//		Random rng1 = new Random();
//		String characters ="";
//		
//		switch (type){
//			case "string":
//				  			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
//				  			break;
//			case "password":
//							characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ0123456789";
//							break;
//			case "address":
//							characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ0123456789_ #$%&/()";
//							break;
//			case "number":
//							characters = "0123456789";
//							break;
//			default:
//							characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ0123456789";
//							break;
//		}
//	    char[] text = new char[length];
//	    for (int i = 0; i < length; i++)
//	    {
//	        text[i] = characters.charAt(rng1.nextInt(characters.length()));
//	    }
//	    return new String(text);
//	}
	

	
	public static String generateEmail(int length)
	{
		Random rng1 = new Random();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ0123456789";
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng1.nextInt(characters.length()));
	    }
	    return new String(text)+"@email.com";
	}
	
	
	
	public static String generatePassword(int length)
	{
		Random rng1 = new Random();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ0123456789";
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng1.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
	public static String generateString(int length)
	{
		Random rng1 = new Random();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng1.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
	public static String generateAddress(int length)
	{
		Random rng1 = new Random();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ0123456789_ #$%&/()";
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng1.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
	public static String generateNumber(int length)
	{
		Random rng1 = new Random();
		String characters = "0123456789";
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng1.nextInt(characters.length()));
	    }
	    return new String(text);
	}
}
