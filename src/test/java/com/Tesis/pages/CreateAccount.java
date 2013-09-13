package com.Tesis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CreateAccount {
	
	final WebDriver driver;
	
	// Definimos los atributos de la clase = Elementos de la pagina
	@FindBy(how = How.CSS, using = "li > span")
	@CacheLookup
	private WebElement mensajeExito;
	
	
	@FindBy(how = How.ID, using = "firstname")
	private WebElement firstname;
	@FindBy(how = How.ID, using = "lastname")
	private WebElement lastname;
	
	@FindBy(how = How.ID, using = "email_address")
	private WebElement emailAddress;
	
	@FindBy(how = How.ID, using = "password")
	private WebElement password;
	@FindBy(how = How.ID, using = "confirmation")
	private WebElement confirmation;
		
	@FindBy(how = How.XPATH, using = "//form[@id='form-validate']/div[3]/button")
	private WebElement submitBtn;

	
	
	//******************************************************************
	
	//Constructor
	public CreateAccount(WebDriver driver) {
		this.driver = driver;
	}
	
	//Definimos los métodos = Acciones de la página
	public void typeFirstname(String name) {
		firstname.clear();
		firstname.sendKeys(name);
	}
	
	public void typeLastname(String name) {
		lastname.clear();
		lastname.sendKeys(name);
	}
	
	public void typeEmail(String name) {
		emailAddress.clear();
		emailAddress.sendKeys(name);
	}
	
	public void typePassword (String passwordText) {
		password.clear();
		password.sendKeys(passwordText);
	}
	
	public void typePasswordConfirm (String passwordText) {
		confirmation.clear();
		confirmation.sendKeys(passwordText);
	}
	
	public void clickSubmitButton() {
		submitBtn.click();
	}
	

	
	// Este metodo retorna la page class a la que vamos luego que esta se ejecute bien
	public MyDashboard createAccount (String firstname, String lastname, String email, String password) {
		typeFirstname(firstname);
		typeLastname(lastname);
		typeEmail(email);
		typePassword(password);
		typePasswordConfirm(password);
		clickSubmitButton();
			
		Assert.assertEquals("Thank you for registering with Main Store.", mensajeExito.getText());
			
		return PageFactory.initElements(driver, MyDashboard.class);
	}
	
	
}