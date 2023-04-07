package retail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public final class Menu {

	public String[] searchWords;
	private static Trie locationTrie = new Trie();
	private static List<String> locationList = new ArrayList<>();
	private static SplayTree splayTree = new SplayTree();

	public static Trie getLocationTrie() {
		return locationTrie;
	}

	public static List<String> getLocationList() {
		return locationList;
	}

	private void startApplication() {
		int caseValue = 0;
		Scanner sc = new Scanner(System.in);
		while (caseValue != 3) {
			try {
				System.out.println("      MENU\n" 
						+ "-------------------------\n" 
						+ "1. Search for properties for specific city \n"
						+ "2. Search for Word .\n" 
						+ "3. Exit.\n" 
						+ "\n"
						+ "Select an option from above: ");
				String next = sc.nextLine();
				caseValue = Integer.parseInt(next);
				switch (caseValue) {
				case 1:
					FilterDetails fd = new FilterDetails();
					provideOption(sc, fd);
					CrawlMultiWebsite cw = new CrawlMultiWebsite();
					cw.beginCrawling(sc, fd);
					filterAndShowResult(sc, fd);
					break;
				case 2:
					pageRankAndSearchFrequency(sc);
					break;
				case 3:
					System.out.println("Exiting...");
					System.exit(0);
					break;
				default:
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Invalid selection. Try again (Press Enter key to continue)");
				e.printStackTrace();
				sc.nextLine();
			}
		}
	}

	private void provideOption(Scanner sc, FilterDetails fd) {

		boolean locationCheck = false;

		while (!locationCheck)
			locationCheck = fetchLocationDetails(sc, fd);
	}

	private boolean fetchLocationDetails(Scanner sc, FilterDetails filterDetails) {

		EditDistance ed = new EditDistance();
		String selectedCity;
		do {
			System.out.println("Enter a City : ");
			selectedCity = sc.nextLine();
			selectedCity = ed.wordSuggestionsSpellCheck(sc, selectedCity.toLowerCase());
			if (!selectedCity.equals("")) {
				break;
			}
		} while ("".equalsIgnoreCase(selectedCity));

		filterDetails.setCity(selectedCity.toLowerCase());
		String proviceByCityName = getProviceByCityName(selectedCity.toLowerCase());
		filterDetails.setProvince(proviceByCityName);
		//System.out.println("City: " + selectedCity);
		//System.out.println("province: " + proviceByCityName);

		if (!"".equalsIgnoreCase(selectedCity) && !"".equalsIgnoreCase(proviceByCityName))
			return true;
		else
			return false;

	}

	private String getProviceByCityName(String cityLocation) {

		ProvinceStateMap pm = new ProvinceStateMap();

		for (Map.Entry<String, List<String>> entry : pm.getProvinceStateMap().entrySet()) {
			String province = entry.getKey();
			List<String> cities = entry.getValue();
			for (String city : cities) {
				if (city.equalsIgnoreCase(cityLocation))
					return province;
			}
		}
		System.out.println("City is not in the province list, Please enter the another city");
		return "";

	}

	void pageRankAndSearchFrequency(Scanner sc) {
		
		WordsInvertedIndex wordsPagesInvertedIndex = new WordsInvertedIndex(RetailPropertyDetails.getRetailPropertyList());
		WordFrequency wf = new WordFrequency();
		wordsPagesInvertedIndex.createIndex();
		wf.compute();
		
		fetchFrequencyOfSearchWord();

		searchWords = getSearchKeywords(sc);
		Set<String> propertyPagesSet = wordsPagesInvertedIndex.search(searchWords);
		Map<String, Integer> wordCountForPageMap = wf.wordCountForPages(searchWords, propertyPagesSet);

		performPageRanking(wordCountForPageMap);
	}

	private void performPageRanking(Map<String, Integer> wordCountForPageMap) {
		PageRank pagerank = new PageRank();
		pagerank.setPropertiesScoreMap(wordCountForPageMap);
		pagerank.orderPagesByRank();
		List<String> propertiesNamesList = pagerank.top10Properties(10);
		if (propertiesNamesList.isEmpty()) 
			System.out.println("Sorry! text you entered was not found.");
		System.out.print("\n");
	}

	private void fetchFrequencyOfSearchWord() {
		WordSearchCount wsc = splayTree.root;
		if (Objects.nonNull(wsc))
			System.out.println("Recent search word is '" + wsc.getWord() + "' with a frequency of "+ wsc.getCount() + "\n");
	}

	private void filterAndShowResult(Scanner sc, FilterDetails fd) {
		new FilterAndShowResult().finalResult(fd);
	}

	String[] getSearchKeywords(Scanner sc) {
		System.out.print("Enter the word you want to search for: ");
		String inputWord = sc.nextLine();
		insertInTree(inputWord);
		String[] words = inputWord.toLowerCase().split("\\s+");
		System.out.println(Arrays.toString(words));
		return words;
	}

	private void insertInTree(String inputWord) {

		WordSearchCount wsc = new WordSearchCount(inputWord, 1);
		WordSearchCount wordSearchCount = splayTree.search(wsc);
		if (wordSearchCount != null)
			wordSearchCount.setCount(wordSearchCount.getCount() + 1);
		else {
			splayTree.insert(wsc);
		}
	}

	public static void main(String[] args) {
		
		locationList = LocationScraper.scraper();
		locationTrie.insertWords(locationList);

		Menu menu = new Menu();
		menu.startApplication();
	}
}
