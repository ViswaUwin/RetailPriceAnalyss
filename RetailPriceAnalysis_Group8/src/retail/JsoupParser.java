package retail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupParser {

	public static Document parse(String file) {
		return Jsoup.parse(file);
	}
}
