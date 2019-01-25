package com.saudhing.cucumber.zoho.steps;

import com.aventstack.extentreports.Status;
import com.saudhing.ExtentListeners.ExtentManager;
import com.saudhing.ExtentListeners.ExtentTestManager;
import com.saudhing.PageObjects.ZohoHomePage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ZohoSteps extends BaseSteps {

	public ZohoHomePage home;

	protected Scenario scenario;
	static String scenarioName;
	static int i = 0;

	@Before
	public void before(Scenario scenario) {

		i = i + 1;

		this.scenario = scenario;
		scenarioName = scenario.getName();
		ExtentTestManager.startTest("Scenario No : " + i + " : " + scenario.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Scenario Started : " + scenario.getName());
		setUpFramework();
		
	}

	@After
	public void after(Scenario scenario) {

		if (scenario.isFailed()) {
			ExtentTestManager.logFail("Scenarion Failed");
			ExtentTestManager.addScreenShotsOnFailure();
		} else {
			ExtentTestManager.logPass("Scenario Passed");
		}

		ExtentManager.getReporter().flush();

		quit();

	}
	
	@Given("^launch browser '(.*?)'$")
	public void launch_browser(String browserName) throws Throwable {
		
		openBrowser(browserName);
		ExtentTestManager.logInfo("Browser launched : "+ browserName);
	    
	}

	@When("^user navigates to the URL '(.*?)'$")
	public void user_navigates_to_the_URL(String URL) throws Throwable {
		
		home = new ZohoHomePage().open(URL);
	    
	}

	@Then("^user clicks on login$")
	public void user_clicks_on_login() throws Throwable {
		
		home.goToLogin();
	    
	}

}
