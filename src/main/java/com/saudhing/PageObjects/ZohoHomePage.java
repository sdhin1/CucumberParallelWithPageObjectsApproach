package com.saudhing.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.saudhing.utilities.DriverManager;

public class ZohoHomePage extends BasePage {
	
	@FindBy(xpath="//*[@class='zh-login']")
	public WebElement logIn;
	
	public ZohoHomePage open(String url) {
		
		DriverManager.getDriver().navigate().to(url);
		return (ZohoHomePage) openPage(ZohoHomePage.class);
		
	}
	
	public ZohoLoginPage goToLogin() {
		
		click(logIn, "Login link");
		return (ZohoLoginPage) openPage(ZohoLoginPage.class);
		
	}

	@Override
	public ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(logIn);
		
	}

}
