package retail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Trie {
	public class TrieNode {
		Map<Character, TrieNode> children;
		char c;
		boolean isWord;

		public TrieNode(char c) {
			this.c = c;
			children = new HashMap<>();
		}

		public TrieNode() {
			children = new HashMap<>();
		}

		public void insert(String word, TrieNode root) {
			if (word == null || word.isEmpty())
				return;
			char firstChar = word.charAt(0);
			TrieNode child = children.get(firstChar);
			if (child == null) {
				child = new TrieNode(firstChar);
				children.put(firstChar, child);
			}

			if (word.length() > 1)
				child.insert(word.substring(1), child);
			else
				child.isWord = true;
		}
	}

	TrieNode root;

	public void insertWords(List<String> words) {
		if (root == null)
			root = new TrieNode();
		for (String word : words)
			root.insert(word, root);
	}

	public boolean find(String prefix, boolean exact) {
		TrieNode lastNode = root;
		for (char c : prefix.toCharArray()) {
			lastNode = lastNode.children.get(c);
			if (lastNode == null)
				return false;
		}
		return !exact || lastNode.isWord;
	}

	public boolean find(String prefix) {
		return find(prefix, false);
	}

	public void completeWordRecurse(TrieNode root, List<String> list, StringBuffer curr) {
		if (root.isWord) {
			list.add(curr.toString());
		}

		if (root.children == null || root.children.isEmpty())
			return;

		for (TrieNode child : root.children.values()) {
			completeWordRecurse(child, list, curr.append(child.c));
			curr.setLength(curr.length() - 1);
		}
	}

	public List<String> completeWord(String prefix) {
		List<String> list = new ArrayList<>();
		TrieNode lastNode = root;
		StringBuffer curr = new StringBuffer();
		for (char c : prefix.toCharArray()) {
			lastNode = lastNode.children.get(c);
			if (lastNode == null)
				return list;
			curr.append(c);
		}
		completeWordRecurse(lastNode, list, curr);
		return list;
	}

	public void printTrie(TrieNode root, StringBuffer curr) {
		if (root.isWord) {
			System.out.println(curr.toString());
		}

		if (root.children == null || root.children.isEmpty())
			return;

		for (TrieNode child : root.children.values()) {
			printTrie(child, curr.append(child.c));
			curr.setLength(curr.length() - 1);
		}
	}

	public void printTrie() {
		StringBuffer curr = new StringBuffer();
		printTrie(root, curr);
	}

}