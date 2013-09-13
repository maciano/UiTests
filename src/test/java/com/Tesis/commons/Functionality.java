package com.Tesis.commons;

import java.io.IOException;
import javax.xml.parsers.*;
import org.openqa.selenium.WebElement;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Functionality {
	private UITestHelper ui;

	public Functionality() {
		ui = new UITestHelper();
		ui.openProperties();
	}

	public Element parseXML(String XMLextendedAttributes)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		InputSource is = new InputSource();
		Document doc = docBuilder.parse(is);
		Element XML = doc.getDocumentElement();
		return XML;
	}
	
	public void flashObjects(String id)
	{
		
		
	}

	public String getMessageDiv(WebElement divMessage) {
		System.out.println("Text: " + divMessage.getText());
		System.out.println("Text: " + divMessage.getAttribute("class"));
		System.out.println("Text: " + divMessage.getAttribute("div"));
		return "";
	}
}

	