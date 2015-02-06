package com.Tesis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCart {
	
	final WebDriver driver;
	
	// Definimos los atributos de la clase = Elementos de la pagina
	@FindBy(how = How.XPATH, using = "//div[2]/ul/li/button")
	private WebElement proceedToCheckOutBtn;

	//******************************************************************
	//Constructor
	public ShoppingCart(WebDriver driver) {
		this.driver = driver;
	}
	
	//Definimos los métodos = Acciones de la página
	public void clickOnProceedToCheckOutBtn() {
		proceedToCheckOutBtn.click();
	}
	
	// Este metodo retorna la page class a la que vamos luego que esta se ejecute bien
	public CheckOutInformation irAlCheckOut() {
		clickOnProceedToCheckOutBtn();
		return PageFactory.initElements(driver, CheckOutInformation.class);
	}
}
