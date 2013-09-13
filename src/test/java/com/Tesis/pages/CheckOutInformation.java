package com.Tesis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutInformation {
	
	final WebDriver driver;
	
	// Definimos los atributos de la clase = Elementos de la pagina
	@FindBy(how = How.ID, using = "billing:use_for_shipping_yes")
	private WebElement billingAddressCmb;
	@FindBy(how = How.XPATH, using = "//div[@id='billing-buttons-container']/button")
	private WebElement continueBillingBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@id='shipping-method-buttons-container']/button")
	private WebElement continueShippingMBtn;
	
	@FindBy(how = How.CSS, using = "#p_method_checkmo")
	private WebElement paymentMoney;
	
	@FindBy(how = How.XPATH, using = "//div[@id='payment-buttons-container']/button")
	private WebElement continuePaymentBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@id='review-buttons-container']/button")
	private WebElement placeOrderBtn;
	

	//******************************************************************
	
	//Constructor
	public CheckOutInformation(WebDriver driver) {
		this.driver = driver;
	}
	
	//Definimos los métodos = Acciones de la página
	public void selectbilling() {
		//billingAddressCmb.
	}
	public void clickOnContinue(WebElement button) {
		button.click();
	}
	
	public void selectPayentMethod(){
		paymentMoney.click();
	}
	
	// Este metodo retorna la page class a la que vamos luego que esta se ejecute bien
	public ThanksPage seleccionarCheckOutInfo () {
		selectbilling();
		
		explicitWait(driver, "billing-buttons-container");
		clickOnContinue(continueBillingBtn);
		
		explicitWait(driver, "shipping-method-buttons-container");
		clickOnContinue(continueShippingMBtn);
		
		explicitWait(driver, "p_method_checkmo");
		selectPayentMethod();
		
		
		explicitWait(driver, "payment-buttons-container");
		clickOnContinue(continuePaymentBtn);
		
		explicitWait(driver, "review-buttons-container");
		clickOnContinue(placeOrderBtn);
		
		return PageFactory.initElements(driver, ThanksPage.class);
	}
	
	
	
	
	
	public WebElement explicitWait(WebDriver driver, String object){
		
		WebElement myDynamicElement = (new WebDriverWait(driver, 20))
				  .until(ExpectedConditions.visibilityOfElementLocated(By.id(object)));
		return myDynamicElement;
		
	}
	
}