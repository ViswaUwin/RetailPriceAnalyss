package retail;

import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CrawlMultiWebsite {
	
	public void beginCrawling(Scanner sc, FilterDetails fd) throws IOException, InterruptedException {

        	System.setProperty(Constants.WEBDRIVER_CHROME_DRIVER, Constants.CHROME_DRIVER_PATH);
            WebDriver driver = new ChromeDriver();
            WebCrawler webCrawler = new WebCrawler();
            webCrawler.setSearchlocation(fd.getCity());
            webCrawler.setDriver(driver);
            webCrawler.run(sc,fd);
            System.out.println("List of Properties: ");
            for (String property : RetailPropertyDetails.getRetailPropertyList().keySet()) {
                System.out.println("Property:  "+ property);
            }
        System.out.println("\n........Crawling has been completed..........");
        
	}
}
