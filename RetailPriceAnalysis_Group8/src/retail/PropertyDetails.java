package retail;

import java.io.Serializable;

public class PropertyDetails implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String propertySite;
	private String propertyPrice;
	private String propertyType;
    private String propertyLocation;
    private String propertyBedrooms;
    private String propertyBathrooms;
    private String propertyUrl;
    private String propertyAddress;
    private String propertyPageData;
    private String[] propertyPageWords;

    public PropertyDetails(String propertySite,String propertyPrice, String propertyLocation, String propertyUrl,
			String propertyAddress,String propertyBathrooms,String propertyBedrooms,String propertyType, String[] propertyPageWords) {
		super();
		this.propertySite = propertySite;
		this.propertyPrice = propertyPrice;
		this.propertyLocation = propertyLocation;
		this.propertyBedrooms = propertyBedrooms;
		this.propertyBathrooms = propertyBathrooms;
		this.propertyUrl = propertyUrl;
		this.propertyAddress = propertyAddress;
		this.propertyPageWords = propertyPageWords;
		this.propertyType = propertyType;
	}

	public String getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(String propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public String getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(String propertyLocation) {
		this.propertyLocation = propertyLocation;
	}

	public String getPropertyBedrooms() {
		return propertyBedrooms;
	}

	public void setPropertyBedrooms(String propertyBedrooms) {
		this.propertyBedrooms = propertyBedrooms;
	}

	public String getPropertyBathrooms() {
		return propertyBathrooms;
	}

	public void setPropertyBathrooms(String propertyBathrooms) {
		this.propertyBathrooms = propertyBathrooms;
	}

	public String getPropertyUrl() {
		return propertyUrl;
	}

	public void setPropertyUrl(String propertyUrl) {
		this.propertyUrl = propertyUrl;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getPropertyPageData() {
		return propertyPageData;
	}

	public void setPropertyPageData(String propertyPageData) {
		this.propertyPageData = propertyPageData;
	}

	public String[] getPropertyPageWords() {
		return propertyPageWords;
	}

	public void setPropertyPageWords(String[] propertyPageWords) {
		this.propertyPageWords = propertyPageWords;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertySite() {
		return propertySite;
	}

	public void setPropertySite(String propertySite) {
		this.propertySite = propertySite;
	}
    
}
