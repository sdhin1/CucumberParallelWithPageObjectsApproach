package com.saudhing.cucumber.zoho.steps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saudhing.ExtentListeners.ExtentTestManager;
import com.saudhing.utilities.DriverFactory;
import com.saudhing.utilities.DriverManager;

public class BaseSteps {

	private WebDriver driver;
	private Properties Config = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(BaseSteps.class);
	public boolean grid = true;
	private String defaultUserName;
	private String defaultPassword;

	public String getDefaultUserName() {
		return defaultUserName;
	}

	public void setDefaultUserName(String defaultUserName) {
		this.defaultUserName = defaultUserName;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}


	public void setUpFramework() {

		configureLogging();
		DriverFactory.setGridPath("http://localhost:4444/wd/hub");
		DriverFactory.setConfigPropertyFile(
				System.getProperty("user.dir") + "//src//test//resources//properties/Config.properties");

		if (System.getProperty("os.name").contains("mac")) {
			DriverFactory.setChromeDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver");
			DriverFactory.setGeckoDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver");
		} else {
			DriverFactory.setChromeDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
			DriverFactory.setGeckoDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
		}

		try {
			fis = new FileInputStream(DriverFactory.getConfigPropertyFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Config.load(fis);
			log.info("Configuration file loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void logInfo(String message) {

		ExtentTestManager.testReport.get().info(message);

	}

	public void configureLogging() {

		String log4jConfigFile = System.getProperty("user.dir") + "//src//test//resources//properties/log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);

	}

	public void openBrowser(String browser) {

		if (System.getenv("ExecutionType") != null && System.getenv("ExecutionType").equals("Grid")) {
			grid = true;
		}

		DriverFactory.setRemote(grid);

		if (DriverFactory.isRemote()) {

			DesiredCapabilities cap = null;

			if (browser.equals("firefox")) {
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);
			} else if (browser.equals("chrome")) {
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
			} else if (browser.equals("ie")) {
				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplore");
				cap.setPlatform(Platform.WIN10);
			}

			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
				log.info("Starting the session on Grid !!!");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			if (browser.equals("chrome")) {
				System.out.println("Launching : " + browser);
				System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverExePath());
				driver = new ChromeDriver();
				log.info("Chrome browser launched !!!");
			} else if (browser.equals("firefox")) {
				System.out.println("Launching : " + browser);
				System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverExePath());
				driver = new FirefoxDriver();
				log.info("Firefox browser launched !!!");
			}

		}

		DriverManager.setWebDriver(driver);
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		setDefaultUserName(Config.getProperty("defaultUserName"));
		setDefaultPassword(Config.getProperty("defaultPassword"));

	}

	public void quit() {

		DriverManager.getDriver().quit();

	}

}
