package retail;

public class WebPageRankDetails implements Comparable<WebPageRankDetails>{
	
	    public int rank;
	    public String propertyName;
	    public WebPageRankDetails(String propertyName, int rank) {
	        this.rank = rank;
	        this.propertyName = propertyName;
	    }
	    
		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public String getPropertyName() {
			return propertyName;
		}

		public void setPropertyName(String propertyName) {
			this.propertyName = propertyName;
		}

		@Override
		public int compareTo(WebPageRankDetails webPage) {
	        if (this.rank < webPage.rank)
	            return 1;
	        else if (this.rank > webPage.rank)
	            return -1;
	        return 0;
	    
		}

		@Override
		public String toString() {
			return "WebPageRankDetails [rank=" + rank + ", propertyName=" + propertyName + "]";
		}

}
