package retail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordsInvertedIndex {

    Map<String, HashSet<String>> propertiesListIndex = new HashMap<>();
    Map<String, PropertyDetails> propertiesMap;

    public WordsInvertedIndex(Map<String, PropertyDetails> propertiesMap) {
        this.propertiesMap = propertiesMap;
    }

    public void addToIndex(PropertyDetails prop) {
        String[] words = prop.getPropertyPageWords();
        String propName = prop.getPropertyAddress();

        for (String w : new HashSet<String>(Arrays.asList(words))) {
            String word = w.toLowerCase();
            HashSet<String> propertiesSet = propertiesListIndex.get(word);
            if (propertiesSet == null) {
            	propertiesSet = new HashSet<String>();
                propertiesListIndex.put(word, propertiesSet);
            }
            propertiesSet.add(propName);
        }
    }

    public void createIndex() {
        for (PropertyDetails property : propertiesMap.values()) {
            addToIndex(property);
        }
    }

    public Set<String> search(String[] words) {
        Set<String> propSet = new HashSet<>();
        for (String w : words) {
            String word = w.toLowerCase();
            HashSet<String> propertiesSet = propertiesListIndex.get(word);
            if (propertiesSet != null) {
                for (String prop : propertiesSet) {
                	propSet.add(prop);
                }
            }
        }
        return propSet;
    }

}