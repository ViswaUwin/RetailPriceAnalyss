package retail;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordFrequency {

	//Declares and initializes a wordOccuranceMapOfProperties
	Map<String, Map<String, Integer>> wordOccuranceMapOfProperties = new HashMap<>();
	
	public Map<String, Map<String, Integer>> getWordOccuranceMapOfProperties() {
		return wordOccuranceMapOfProperties;
	}

	public void setWordOccuranceMapOfProperties(Map<String, Map<String, Integer>> wordOccuranceMapOfProperties) {
		this.wordOccuranceMapOfProperties = wordOccuranceMapOfProperties;
	}

	// Method to compute word occurrence for each property
	public void compute() {

		for (PropertyDetails prop : RetailPropertyDetails.getRetailPropertyList().values()) {
			String[] pageWords = prop.getPropertyPageWords();
			String propAdd = prop.getPropertyAddress();

			Map<String, Integer> wordOccMap = new HashMap<>();
			for (String pw : pageWords) {
				String word = pw.toLowerCase();
				if (wordOccMap.containsKey(word)) {
					wordOccMap.put(word, wordOccMap.get(word) + 1);
				} else {
					wordOccMap.put(word, 1);
				}
			}

			wordOccuranceMapOfProperties.put(propAdd, wordOccMap);
		}
	}

	// Method to get word counts for search words in a set of properties
	public Map<String, Integer> wordCountForPages(String[] searchWords, Set<String> propSet) {
		Map<String, Integer> wordCountForPageMap = new HashMap<>();
		for (String propName : propSet) {
			Map<String, Integer> wordCountMap = wordOccuranceMapOfProperties.get(propName);
			int count = 0;
			for (String eachWord : searchWords) {
				if (wordCountMap.containsKey(eachWord)) {
					count += wordCountMap.get(eachWord);
				}
			}
			wordCountForPageMap.put(propName, count);
		}
		return wordCountForPageMap;
	}
}
