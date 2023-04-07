package retail;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.absmartly.sdk.java.nio.charset.StandardCharsets;

public class SaveAndFetchHtml {

	public String fetchHtml(WebDriver d, String url, String propertyAddress) {
		d.get(url);
		WebDriverWait wait = new WebDriverWait(d, 10000);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return true;
			}
		});
		String s = d.getPageSource();

		String fileName = "webpages\\" + propertyAddress + ".html";
		Path path = Paths.get(fileName);

		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			writer.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return s;
	}
}
