package retail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class EditDistance {
	public int getDistance(String locationTyped, String locationFromSet) {
		int[][] index = new int[locationTyped.length() + 1][locationFromSet.length() + 1];

		for (int k = 0; k <= locationTyped.length(); k++)
			index[k][0] = k;

		for (int l = 0; l <= locationFromSet.length(); l++)
			index[0][l] = l;

		for (int i = 1; i <= locationTyped.length(); i++) {
			for (int j = 1; j <= locationFromSet.length(); j++) {
				int val = (locationTyped.charAt(i - 1) == locationFromSet.charAt(j - 1)) ? 0 : 1;
				index[i][j] = Math.min(Math.min(index[i - 1][j] + 1, index[i][j - 1] + 1), index[i - 1][j - 1] + val);
			}
		}

		return index[locationTyped.length()][locationFromSet.length()];
	}

	public String wordSuggestionsSpellCheck(Scanner sc, String location) {
		List<String> nearestLocationList = new ArrayList<>();

		List<Location> l = new ArrayList<>();
        for (String loc : Menu.getLocationList()) {
        	Location lo = new Location();
            int distance = getDistance(loc, location);
            lo.setDist(distance);
            lo.setLocationName(loc);
            l.add(lo);
        }
        
        Collections.sort(l);
        
        for (int i = 0; i < 3 ; i++) {
            nearestLocationList.add(l.get(i).getLocationName());
            System.out.println("Enter " + i + " for: " + nearestLocationList.get(i));
        }
        
		System.out.println("Type 4 to Complete the location");
		System.out.println("Type 5 to enter again");

		String userInput = sc.nextLine();
		int caseValue = Integer.valueOf(userInput);

		switch (caseValue) {
		case 4:
			return wordCompletion(sc, location);
		case 0:
			return nearestLocationList.get(0);
		case 1:
			return nearestLocationList.get(1);
		case 2:
			return nearestLocationList.get(2);
		case 5:
			System.out.println("Enter a Location: ");
			return "";
		}
		return "";

	}

	private String wordCompletion(Scanner sc, String location) {

		System.out.println("Word completion: ");
		Trie locationTrie = Menu.getLocationTrie();
		List<String> completedWords = locationTrie.completeWord(location);
		int count = 1;

		if (!completedWords.isEmpty()) {

			for (int i = 0; i < ((completedWords.size() < 5) ? completedWords.size() : 5); i++) {
				System.out.println("Enter " + count + " For the word: " + completedWords.get(i));
				count++;
			}
			String userInput = sc.nextLine();
			int caseValue = Integer.valueOf(userInput);
			switch (caseValue) {
			case 1:
				return completedWords.get(0);
			case 2:
				return completedWords.get(1);
			case 3:
				return completedWords.get(2);
			case 4:
				return completedWords.get(3);
			case 5:
				return completedWords.get(4);
			case 9:
				return "";
			}
		}
		else {
			System.out.println("\n No locations found for the entered word to complete it. Please try with new word");
			return "";
		}

		return location;
	}
}
