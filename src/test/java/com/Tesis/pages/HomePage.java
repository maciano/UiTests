package com.Tesis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
	
	final WebDriver driver;
	
	
	// Definimos los atributos de la clase = Elementos de la pagina
	@FindBy(how = How.CSS, using = "a[title='Log In']")
	private WebElement linkLogIn;
	

	

	//******************************************************************
	//Definimos los métodos = Acciones de la página
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
//		if (driver.findElement(By.cssSelector("a[title='Log Out']")).isDisplayed()){
//			
//			driver.findElement(By.cssSelector("a[title='Log Out']")).click();
//			
//		}
	
	}
	public void clickLogIn() {
		linkLogIn.click();
	}
	
	

	
	public CustomerLoginPage irCustomerLoginPage() {
		
		
		clickLogIn();
		return PageFactory.initElements(driver, CustomerLoginPage.class);
}
}