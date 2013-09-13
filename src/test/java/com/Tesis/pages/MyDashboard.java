package com.Tesis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MyDashboard {
	
	final WebDriver driver;
	
	// Definimos los atributos de la clase = Elementos de la pagina

	@FindBy(how = How.XPATH, using = "//ul[@id='nav']/li/a/span")
	private WebElement menu1;
	@FindBy(how = How.XPATH, using = "//ul[@id='nav']/li[2]/a/span")
	private WebElement menu2;
	@FindBy(how = How.XPATH, using = "//ul[@id='nav']/li[3]/a/span")
	private WebElement menu3;
	
	@FindBy(how = How.LINK_TEXT, using = "Address Book")
	private WebElement AddressBook;

	@FindBy(how = How.CSS, using = "a[title='My Account']")
	private WebElement linkMyAccount;
	
	//******************************************************************
	
	//Constructor
	public MyDashboard(WebDriver driver) {
		this.driver = driver;
	}
	
	//Definimos los métodos = Acciones de la página
	public void goMenu1() {
		menu1.click();
	}
	public void goMenu2() {
		menu2.click();
	}
	public void goMenu3() {
		menu3.click();
	}
	
	public void goAddressBook(){
		AddressBook.click();
	}
	
	public void clickMyAccount() {
		linkMyAccount.click();
	}
		
	// Este metodo retorna la page class a la que vamos luego que esta se ejecute bien
	public ProductList irProductosFurniture() {
		goMenu1();
		
		Assert.assertEquals(driver.getTitle(),"Furniture");
		return PageFactory.initElements(driver, ProductList.class);
		
	}
	
	public ProductList irProductosElectronics() {
		goMenu2();
		
		Assert.assertEquals(driver.getTitle(),"Electronics");
		return PageFactory.initElements(driver, ProductList.class);
	}
	
	public ProductList irProductosApparel() {
		goMenu3();
		
		Assert.assertEquals(driver.getTitle(),"Apparel");
		return PageFactory.initElements(driver, ProductList.class);
	}
	
	public AddressPage  irACargarDirecion(){
		clickMyAccount();
		goAddressBook();
		
		Assert.assertEquals(driver.getTitle(), "Add New Address");
		return PageFactory.initElements(driver, AddressPage.class);
	}
	
	
}