package com.Tesis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CustomerLoginPage {
	
	final WebDriver driver;
	
	// Definimos los atributos de la clase = Elementos de la pagina
	@FindBy(how = How.ID, using = "email")
	private WebElement email;
	@FindBy(how = How.ID, using = "pass")
	private WebElement passWord;
	@FindBy(how = How.ID, using = "send2")
	private WebElement loginBtn;
	
	@FindBy(how = How.CSS, using = "div.buttons-set > button.button")
	private WebElement createAccountBtn;

	
	//******************************************************************
	
	//Constructor
	public CustomerLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Definimos los métodos = Acciones de la página
	public void typeEmail(String name) {
		email.clear();
		email.sendKeys(name);
	}
	public void typePassword (String password) {
		passWord.clear();
		passWord.sendKeys(password);
	}
	public void clickLoginButton() {
		loginBtn.click();
	}
	
	public void clickCreateAccount(){
		createAccountBtn.click();
		
	}
	
	// Este metodo retorna la page class a la que vamos luego que esta se ejecute bien
	public MyDashboard loginCustomer(String email, String password) {
		typeEmail(email);
		typePassword(password);
		clickLoginButton();
		return PageFactory.initElements(driver, MyDashboard.class);
	}
	
	public CreateAccount createAccount(){
		clickCreateAccount();
		
		return PageFactory.initElements(driver, CreateAccount.class);
	}
	
	
}