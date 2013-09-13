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
/*	
	@FindBy(how = How.XPATH, using = "//li/a/img")
	private WebElement item11;
	@FindBy(how = How.XPATH, using = "//li[2]/a/img")
	private WebElement item12;
	@FindBy(how = How.XPATH, using = "//li[3]/a/img")
	private WebElement item13;
	
	@FindBy(how = How.XPATH, using = "//ul[2]/li/a/img")
	private WebElement item21;
	@FindBy(how = How.XPATH, using = "//ul[2]/li[2]/a/img")
	private WebElement item22;
	@FindBy(how = How.XPATH, using = "//ul[2]/li[3]/a/img")
	private WebElement item23;
	
	@FindBy(how = How.XPATH, using = "//ul[3]/li/a/img")
	private WebElement item31;
	@FindBy(how = How.XPATH, using = "//ul[3]/li[2]/a/img")
	private WebElement item32;

	
	@FindBy(how = How.XPATH, using = "//ul[3]/li[3]/a/img")
	private WebElement item33;
*/	
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
//		System.out.println("path:"+path);
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