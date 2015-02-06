package com.Tesis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductList {
	
	final WebDriver driver;
	
	// Definimos los atributos de la clase = Elementos de la pagina
	@FindBy(how = How.CSS, using = "div.box-collateral.box-description > h2")
	private WebElement Detalles;

	//******************************************************************
	//Constructor
	public ProductList(WebDriver driver) {
		this.driver = driver;
	}
	
	//Definimos los métodos = Acciones de la página
	public void clickOn(WebElement item) {
		item.click();
	}
	
	public WebElement convertRowColToElement(int row, int col){
		String path="//";
		
		if (row==1 && col==1) path+="li";
		if (row==1 && col>1  ) path+="li["+col+"]";
		if (row>1){
			path+="ul["+row+"]";
			if (col>1) path+="/li["+col+"]";
			else path+="/li";
		}
		
		path+="/a/img";
		WebElement item = driver.findElement(By.xpath(path));
		
		return item;
	}
	
	// Este metodo retorna la page class a la que vamos luego que esta se ejecute bien
	public ProductDescription comprarProducto(int row, int high) {
		WebElement item = convertRowColToElement(row,high);
		clickOn(item);
		
		Assert.assertEquals("Details", Detalles.getText());
		return PageFactory.initElements(driver, ProductDescription.class);
	}
}
