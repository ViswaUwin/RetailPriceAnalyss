package retail;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

public class RetailPropertyDetails {
	
	private static Map<String, PropertyDetails> retailPropertyList = new HashMap<>();
	private static Map<String, HashSet<String>> citiesMap = new HashMap<>();
	private static final String PRICE_VALIDATION_REGEX = "[+-]?([1-9]*[.])?[1-9]+";

	public static Map<String, PropertyDetails> getRetailPropertyList() {
		return retailPropertyList;
	}

	public static void setRetailPropertyList(Map<String, PropertyDetails> retailPropertyList) {
		RetailPropertyDetails.retailPropertyList = retailPropertyList;
	}

	public static Map<String, HashSet<String>> getCitiesMap() {
		return citiesMap;
	}

	public static void setCitiesMap(Map<String, HashSet<String>> citiesMap) {
		RetailPropertyDetails.citiesMap = citiesMap;
	}

	public void scrapingRentalsCASite(Document doc, WebDriver driver, FilterDetails fd) {
		Elements elements = doc.getElementsByClass("listing-card-container col-12");
		int count = 0;
		for (Element element : elements) {
			if (count == 3)
			 break;
			String price = element.getElementsByClass("listing-card__price").first().text();
			price = pricePatternMatcher(price.split("-")[0].trim().toString());
			if(!priceValidation(price)) {
				continue;
			}
			String beddetails = "";
			String bathdetails = "";
			
			Element x = element.getElementsByClass("listing-card__main-features").first();
			if (x != null) {
			    List<Element> liElements = x.getElementsByTag("li");
			    Element firstLiElement = liElements.get(0);
			    Element secondLiElement = liElements.get(1);
			    beddetails = firstLiElement.text();
			    bathdetails = secondLiElement.text();
			}
			String type = element.getElementsByClass("listing-card__type").first().text().toLowerCase();
			String name = element.getElementsByClass("listing-card__title").first().text().toLowerCase();
			String url = element.getElementsByClass("listing-card__details-link").first().attr("href");
			String text = fetchTextFromUrl(driver, url, name).toLowerCase();

			String[] words = text.split(" ");
			words = Arrays.asList(words).stream().map(String::toLowerCase).toArray(String[]::new);
			addToMap(citiesMap, fd.getCity(), name);
			
			retailPropertyList.put(name, new PropertyDetails(Constants.RENTALS_CA,price, fd.getCity(),url, 
					name,bathpattern(bathdetails),bedpattern(beddetails),type , words));
			
			count++;
		}
	}
	
	public String bedpattern(String bed) {
		String regex = "\\d+";
		Pattern ptrn = Pattern.compile(regex);
		Matcher matcher = ptrn.matcher(bed);
		int MAX = 0;
		while (matcher.find()) {
			int num = Integer.parseInt(matcher.group());
			if (num > MAX)
				MAX = num;
		}
	    return Integer.toString(MAX);
	}
	
	public String bathpattern(String bath) {
		String regex = "\\d+";
		Pattern ptrn = Pattern.compile(regex);
		Matcher matcher = ptrn.matcher(bath);
		int MAX = 0;
		while (matcher.find()) {
			int num = Integer.parseInt(matcher.group());
			if (num > MAX)
				MAX = num;
		}
	    return Integer.toString(MAX);
	}
	

	private static String pricePatternMatcher(String priceWithDoller) {
		String value = Constants.ZERO;
		Pattern pattern = Pattern.compile(Constants.PRICE_PATTERN_REGEX);
        Matcher matcher = pattern.matcher(priceWithDoller);
        if (matcher.find()) {
           value = matcher.group(1);
        }
		return value;
	}

	public static void addToMap(Map<String, HashSet<String>> map, String key, String hotelName) {
		if (map.containsKey(key)) {
			map.get(key).add(hotelName);
		} else {
			HashSet<String> set = new HashSet<>();
			set.add(hotelName);
			map.put(key, set);
		}
	}

	public static String fetchTextFromUrl(WebDriver driver, String url, String name) {
		SaveAndFetchHtml sh = new SaveAndFetchHtml();
		String html = sh.fetchHtml(driver, url, name);
		Document doc = JsoupParser.parse(html);
		return doc.body().text();
	}
	
	public void scrapingAppartmentsCA(Document webSiteDOMObject, WebDriver driver, FilterDetails fd) throws IOException, InterruptedException {
		String url = "https://www.apartments.com/"+fd.getCity()+"-"+fd.getProvince()+"/";
		Connection connection = Jsoup.connect(url).userAgent("Chrome/58.0.3029.110");
		org.jsoup.nodes.Document doc = connection.get();
		Thread.sleep(5000);
		Elements elements = doc.getElementsByClass("placards placardsv2");
		System.out.println("\n");
		int count = 0;
		if (elements.size() == 0) {
			System.out.println("No rentals for the location: " + fd.getCity());

		} else {
			for (Element element : elements) {
				Elements i = element.getElementsByClass("mortar-wrapper");
				for (Element j : i) {
					if (count == 3)
						break;

					try {
						String price_final = "", type = "", bedDetails = "", bed = "", name = "", bath = "0";
						if (j.selectFirst("p").attr("class").equals("property-pricing")) {
							String price = j.getElementsByClass("property-pricing").text();
							type = "studio";
							name = j.getElementsByClass("property-address js-url").first().text();
							bedDetails = j.getElementsByClass("property-beds").first().text();
							bed = patternfornew(bedDetails);
							price_final = pricepatternapartmentsca(price);
							if(!priceValidation(price_final)) {
								continue;
							}
						} else {
							String price = j.getElementsByClass("property-rents").first().text();
							type = j.getElementsByClass("js-placardTitle title").first().text().toLowerCase();
							name = j.getElementsByClass("property-address js-url").text().toLowerCase();
							bedDetails = j.getElementsByClass("property-beds").first().text();
							bed = bedpatternapartmentsca(bedDetails);
							bath = bathpatternapartmentsca(bedDetails);
							price_final = pricepatternapartmentsca(price);
							if(!priceValidation(price_final)) {
								continue;
							}
						}
						String adURL = j.getElementsByClass("property-link").select("a").first().attr("href");
						String text = fetchTextFromUrl(driver, url, name).toLowerCase();
						String[] words = text.split(" ");
						words = Arrays.asList(words).stream().map(String::toLowerCase).toArray(String[]::new);
						addToMap(citiesMap, fd.getCity(), name);
						retailPropertyList.put(name,
								new PropertyDetails(Constants.APARTMENTS_CA, price_final, fd.getCity(),
										adURL, name, bath,
										bed, type, words));
						count++;
					} catch (NullPointerException e) {
						System.out.println("The entered city is not available" + e.getMessage());
					}
				}
			}
		}
	}
	
	public String pricepatternapartmentsca(String price) {
		String[] parts = price.split(" - ");
	    String smallValue = parts[0].replaceAll("[^0-9]", "");
		return smallValue;
	}
	
	public String bedpatternapartmentsca(String price) {
		Pattern pattern = Pattern.compile("(\\d+)\\s+Beds");
        Matcher matcher = pattern.matcher(price);
        String bed = "0";
        if (matcher.find()) {
            bed = (matcher.group(1));
        }
		return bed;
	}
	
	public String bathpatternapartmentsca(String price) {
		Pattern pattern = Pattern.compile("(\\d+)\\s+Baths");
        Matcher matcher = pattern.matcher(price);
        String bath = "0";
        if (matcher.find()) {
            bath = (matcher.group(1));
        }
		return bath;
	}
	
	public boolean priceValidation(String price) {
		String regex = PRICE_VALIDATION_REGEX;
		if(price.trim().matches(regex)) {
			return true;
		}
		return false;
	}
	
	public  String patternfornew(String text) {
		Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        
        // Find the first number in the string and convert it to an integer
        String bed = "0";
        if (matcher.find()) {
            bed = (matcher.group());
        }
		return bed;
	}
	
}
