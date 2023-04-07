package retail;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class PageRank {
    
	Map<String, Integer> propertiesScoreMap;
    PriorityQueue<WebPageRankDetails> priorityQueue = new PriorityQueue<WebPageRankDetails>();


    public Map<String, Integer> getPropertiesScoreMap() {
		return propertiesScoreMap;
	}

	public void setPropertiesScoreMap(Map<String, Integer> propertiesScoreMap) {
		this.propertiesScoreMap = propertiesScoreMap;
	}

	public PriorityQueue<WebPageRankDetails> getPriorityQueue() {
		return priorityQueue;
	}

	public void setPriorityQueue(PriorityQueue<WebPageRankDetails> priorityQueue) {
		this.priorityQueue = priorityQueue;
	}

	public List<String> top10Properties(int count) {
        List<String> propertyList = new LinkedList<>();
        for (int i=0;i<count;i++){
            if (priorityQueue.isEmpty()) {
                break;
            }
            WebPageRankDetails webPageDet = priorityQueue.remove();
            System.out.println("Property Address : " + webPageDet.getPropertyName() + " and Count is: " + webPageDet.getRank());
            propertyList.add(webPageDet.getPropertyName());
        }
        return propertyList;
    }
    
    public void orderPagesByRank() {
        for (String propertyName: propertiesScoreMap.keySet()) {
            int rank = propertiesScoreMap.get(propertyName);
            WebPageRankDetails webPage = new WebPageRankDetails(propertyName, rank);
            priorityQueue.add(webPage);
        }
    }
}