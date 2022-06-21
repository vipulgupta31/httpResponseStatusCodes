package test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestHttpStatusCodes extends BaseClass{

	@Test
	public void testHttpStatusCodes() throws MalformedURLException, IOException 
	{
		int successfulConnection = 0;
		int failedConnection = 0;
		
		System.out.println("Navigating to the URL");
		driver.get("https://www.lambdatest.com/selenium-playground");
		
		//fetch web element locator for all the form links on the page
		System.out.println("Fetching weblement for URLs on the page");
		List<WebElement> formHref = driver.findElements(By.xpath("//*[@class='pt-10']//a"));
		
		//traverse through each link and print the response status code.
		for(int i = 0; i<formHref.size(); i++)
		{
			//fetch the url link using href attribute
			String url = formHref.get(i).getAttribute("href");
			
			//condition to tamper URL to get non-2xx code
			if(i % 3 == 0)
			{
				url = url + "test";
			}
			
			System.out.println("\nConnecting to URL to fetch response status code");
			//URL connection
			HttpURLConnection httpURLConnection=(HttpURLConnection)new URL(url).openConnection();
			 
			// pass HEAD as parameter to setRequestMethod
			httpURLConnection.setRequestMethod("HEAD");
			 
			// make connection and obtain Response code
			httpURLConnection.connect();
			int responseCode = httpURLConnection.getResponseCode();
			
			if(responseCode == 200)
			{
				System.out.println("Connection successfully to URL : " + url);
				successfulConnection++;
			}
			else
			{
				System.out.println("Connection failed to URL : " + url + " with response code : " + responseCode);
				failedConnection++;
			}
		}
		
		System.out.println("Number of Successful connections : " + successfulConnection);
		System.out.println("Number of Failed connections : " + failedConnection);
			
	}
	
}
