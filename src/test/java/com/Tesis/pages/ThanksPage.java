package com.Tesis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ThanksPage {
	
	final WebDriver driver;
	
	// Definimos los atributos de la clase = Elementos de la pagina
	@FindBy(how = How.CSS, using = "h1")
	private WebElement messageOK;
	@FindBy(how = How.CSS, using = "a[title='Log Out']")
	private WebElement logOutBtn;

	
	//******************************************************************
	
	//Constructor
	public ThanksPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Definimos los métodos = Acciones de la página

	public void clickOnLogOut() {
		logOutBtn.click();
	}
	

	
	// Este metodo retorna la page class a la que vamos luego que esta se ejecute bien
	public HomePage salir () {
		
		clickOnLogOut();
	
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	
}