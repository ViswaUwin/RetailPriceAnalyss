package retail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationScraper {

	public static List<String> scraper() {
		System.setProperty( Constants.WEBDRIVER_CHROME_DRIVER, Constants.CHROME_DRIVER_PATH);

		WebDriver driver = new ChromeDriver();

		String url = "https://www.britannica.com/topic/list-of-cities-and-towns-in-Canada-2038873";
		driver.get(url);

		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));

		List<WebElement> elements = driver.findElements(By.className("topic-list"));
		ArrayList<String> cityList = new ArrayList<>();
		for (WebElement ele : elements) {
			List<WebElement> cs = ele.findElements(By.className("md-crosslink"));

			for (WebElement c : cs) {
				cityList.add(c.getText().toLowerCase());
			}
		}
		driver.quit();
		return cityList;

	}

}
