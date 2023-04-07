package retail;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

public class WebCrawler {

	private String searchlocation;
	private WebDriver driver;

	public String getSearchlocation() {
		return searchlocation;
	}

	public void setSearchlocation(String searchlocation) {
		this.searchlocation = searchlocation;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void run(Scanner sc, FilterDetails fd) throws IOException, InterruptedException {

		String webSiteURL = "https://rentals.ca/" + searchlocation;
		String webSiteName = "initial";

		SaveAndFetchHtml sh = new SaveAndFetchHtml();
		String htmlAsString = sh.fetchHtml(driver, webSiteURL, webSiteName);

		Document webSiteDOMObject = JsoupParser.parse(htmlAsString);
		RetailPropertyDetails rpd = new RetailPropertyDetails();
		rpd.scrapingRentalsCASite(webSiteDOMObject, this.driver, fd);
		rpd.scrapingAppartmentsCA(webSiteDOMObject,this.driver, fd);

	}

}
