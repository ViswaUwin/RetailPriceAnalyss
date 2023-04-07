package retail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class FilterAndShowResult {
	

    public void finalResult(FilterDetails fd) {
    	
        Set<String> locationProperties = RetailPropertyDetails.getCitiesMap().get(fd.getCity());

        Map<String, PropertyDetails> propertiesList = RetailPropertyDetails.getRetailPropertyList();

        System.out.println("Results based on filters: ");
        System.out.println("---------------------------------------");
        if (!propertiesList.isEmpty()) {

				System.out.println("\n Properties based on Max no. of Beds:");
				List<String> sortedByMaxBeds = sortPropertiesByFilters(locationProperties,RetailPropertyDetails.getRetailPropertyList(), 1);
				for (String property : sortedByMaxBeds) {
					System.out.println("       WebSite: " + propertiesList.get(property).getPropertySite());
					System.out.println("       Address: " + propertiesList.get(property).getPropertyAddress());
					System.out.println("       Price: " + propertiesList.get(property).getPropertyPrice());
					System.out.println("       Location: " + propertiesList.get(property).getPropertyLocation());
					System.out.println("       Beds: " + propertiesList.get(property).getPropertyBedrooms());
					System.out.println("       Baths: " + propertiesList.get(property).getPropertyBathrooms());
					System.out.print("\n");
				}
				System.out.println("\n Properties based on Low to High prices:");
				List<String> sortedByMinPrice = sortPropertiesByFilters(locationProperties,RetailPropertyDetails.getRetailPropertyList(), 2);
				for (String property : sortedByMinPrice) {
					System.out.println("       WebSite: " + propertiesList.get(property).getPropertySite());
					System.out.println("       Address: " + propertiesList.get(property).getPropertyAddress());
					System.out.println("       Price: " + propertiesList.get(property).getPropertyPrice());
					System.out.println("       Location: " + propertiesList.get(property).getPropertyLocation());
					System.out.println("       Beds: " + propertiesList.get(property).getPropertyBedrooms());
					System.out.println("       Baths: " + propertiesList.get(property).getPropertyBathrooms());
					System.out.print("\n");
				}
        } else {
            System.out.println("No Properties found for the provided filters ");
        }

    }

    public class SortCriteria implements Comparable<SortCriteria> {
        private String key;
        private Float value;

        public SortCriteria(String key, float value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(SortCriteria other) {
            if (other.value > this.value)
                return -1;
            else
                return 1;
        }
    }

    private List<String> sortPropertiesByFilters(Set<String> locationProperties, Map<String, PropertyDetails> propertyLst,
            int filter) {

        PriorityQueue<SortCriteria> priortyQ;
        
        if(filter == 1) {
        	priortyQ = new PriorityQueue<>(Collections.reverseOrder());
        	
        }
        else {
        	priortyQ = new PriorityQueue<>();
        }
        
        for (String property : locationProperties) {
            PropertyDetails pd = propertyLst.get(property);
            String propertyBedrooms = pd.getPropertyBedrooms();
            String propertyPrice = pd.getPropertyPrice(); 
            float baths = Float.parseFloat(Objects.nonNull(propertyBedrooms) ?  propertyBedrooms : "0");
            float price = Float.parseFloat(Objects.nonNull(propertyPrice) ? propertyPrice : "999999");

            if (filter == 1) {
            	priortyQ.offer(new SortCriteria(property, baths));
            } else if (filter == 2) {
            	priortyQ.offer(new SortCriteria(property, price));
            }
        }

        List<String> result = new ArrayList<>();
        while (!priortyQ.isEmpty()) {
            result.add(priortyQ.poll().key);
        }
        return result;
    }

}
