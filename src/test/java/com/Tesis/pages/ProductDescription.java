package com.Tesis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductDescription {
	
	final WebDriver driver;
	
	// Definimos los atributos de la clase = Elementos de la pagina
	@FindBy(how = How.ID, using = "qty")
	private WebElement qty;
	@FindBy(how = How.XPATH, using = "//form[@id='product_addtocart_form']/div[2]/div[4]/div/button")
	private WebElement addToCartBtn;


	
	//******************************************************************
	
	//Constructor
	public ProductDescription(WebDriver driver) {
		this.driver = driver;
	}
	
	//Definimos los métodos = Acciones de la página
	public void typeQuantity(String quantity) {
		qty.clear();
		qty.sendKeys(quantity);
	}

	public void clickAddToCartBtn() {
		addToCartBtn.click();
	}
	
	// Este metodo retorna la page class a la que vamos luego que esta se ejecute bien
	public ShoppingCart elegirCantidad(String quantity) {
		typeQuantity(quantity);
		clickAddToCartBtn();

		return PageFactory.initElements(driver, ShoppingCart.class);
	}
}
