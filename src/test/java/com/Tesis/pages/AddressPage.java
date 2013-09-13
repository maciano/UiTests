package com.Tesis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class AddressPage {
	
	final WebDriver driver;
	
	// Definimos los atributos de la clase = Elementos de la pagina
	@FindBy(how = How.CSS, using = "li > span")
	private WebElement mensajeExito;
	
	@FindBy(how = How.ID, using = "street_1")
	private WebElement street_1;
	@FindBy(how = How.ID, using = "telephone")
	private WebElement telephone;
	@FindBy(how = How.ID, using = "city")
	private WebElement city;
	@FindBy(how = How.ID, using = "country")
	private WebElement country;
	@FindBy(how = How.ID, using = "zip")
	private WebElement zip;
	
	@FindBy(how = How.XPATH, using = "//form[@id='form-validate']/div[3]/button")
	private WebElement submitBtn;

	
	//******************************************************************
	
	//Constructor
	public AddressPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Definimos los métodos = Acciones de la página
	public void typeStreet(String name) {
		street_1.clear();
		street_1.sendKeys(name);
	}
	
	public void typeTel(String number) {
		telephone.clear();
		telephone.sendKeys(number);
	
	}
	
	public void typeCity(String name) {
		city.clear();
		city.sendKeys(name);
	}
	
	public void typeCountry(int countryName) {
		
		Select sele = new Select(country);
		sele.selectByIndex(10);
	}
	
	
	public void typeZIP(String zipCode) {
		zip.clear();
		zip.sendKeys(zipCode);
	}
	
	public void clickSubmitButton() {
		submitBtn.click();
	}
	
	// Este metodo retorna la page class a la que vamos luego que esta se ejecute bien
	public MyDashboard cargarDireccion (String streetName, String tele, String city, int country, String zipCode) {
		typeStreet(streetName);
		typeTel(tele);
		typeCity(city);
		typeCountry(country);
		typeZIP(zipCode);
		clickSubmitButton();
		
		Assert.assertEquals("The address has been saved.", mensajeExito.getText());
	
		return PageFactory.initElements(driver, MyDashboard.class);
	}
	
	
}