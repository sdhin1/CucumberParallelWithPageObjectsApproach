package com.saudhing.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saudhing.ExtentListeners.ExtentTestManager;
import com.saudhing.utilities.DriverManager;

public abstract class BasePage<T> {
	
	protected WebDriver driver;
	
	public BasePage() {
		
		this.driver = DriverManager.getDriver();
		
	}
	
	public T openPage(Class<T> clazz) {
		
		T page = null;
		driver = DriverManager.getDriver();
        AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, 10);
		page = PageFactory.initElements(driver, clazz);
		PageFactory.initElements(ajaxElemFactory, page);
		ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
		waitForPageToLoad(pageLoadCondition);
		return page;
		
		
		/*
		 * 
		 * 1. Initialize the Page Factory elements
		 * 2. Page Load Condition
		 * 
		 */
		
	}
	
	private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(pageLoadCondition);
		
	}
	
	public abstract ExpectedCondition getPageLoadCondition();
	
	public void click(WebElement element, String elementName) {

		element.click();
		ExtentTestManager.testReport.get().info("Clicking on an element " + elementName);

	}

	public void type(WebElement element, String value, String elementName) {

		element.sendKeys(value);
		ExtentTestManager.testReport.get().info("Typing in " + elementName+ " entered value as : "+ value);

	}
	

}
