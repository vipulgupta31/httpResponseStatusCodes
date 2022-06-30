package test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseClass {

	public RemoteWebDriver driver = null;
	String username = "<lambdatest_username>";
	String accessKey = "<lambdatest_accesskey>";

	@BeforeMethod
	public void setUp() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("version", "103.0");
		capabilities.setCapability("platform", "Windows 11");
		capabilities.setCapability("resolution", "1024x768");
		capabilities.setCapability("build", "Http Response Status Codes Using Selenium WebDriver");
		capabilities.setCapability("name", "Http Response Status Codes");

		try {
			driver = new RemoteWebDriver(
					new URL("https://" + username + ":" + accessKey
							+ "@hub.lambdatest.com/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		}

	}

	@AfterMethod
	public void closeDriver() {
		driver.quit();
	}
}
