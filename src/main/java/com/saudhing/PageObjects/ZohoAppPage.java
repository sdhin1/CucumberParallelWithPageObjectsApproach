package com.saudhing.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoAppPage extends BasePage{
	
	@FindBy(xpath="//*[text()='Cliq']")
	public WebElement cliq;
	
	@FindBy(xpath="//*[text()='Connect']")
	public WebElement connect;
	
	@FindBy(xpath="//*[text()='CRM']")
	public WebElement crm;
	
	
	@Override
	public ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(crm);
	}
	
	public ZohoCRMPage goToCRM() {
		
		click(crm, "CRM Link");
		return (ZohoCRMPage) openPage(ZohoCRMPage.class);
		
	}
	
	public ZohoCliqPage goToCliq() {
		
		click(cliq, "Cliq Link");
		return (ZohoCliqPage) openPage(ZohoCliqPage.class);
		
	}
	
	public ZohoConnectPage goToConnect() {
		
		click(connect, "Connect Link");
		return (ZohoConnectPage) openPage(ZohoConnectPage.class);
		
	}

}
