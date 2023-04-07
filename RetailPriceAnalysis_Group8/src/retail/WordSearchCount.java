package retail;

class WordSearchCount implements Comparable<WordSearchCount> {
    public WordSearchCount left;
    public WordSearchCount right;
    public WordSearchCount parent;
    private String word;
    private Integer count;

    public WordSearchCount(String word, int count) {
        this.word = word;
        this.count = count;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(WordSearchCount o) {
        return this.getWord().compareTo(o.getWord());
    }

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

    
    
}