package retail;

public class FilterDetails {
	
	private String province;
	
	private String city;
	
	private String filter;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	@Override
	public String toString() {
		return "FilterDetails [province=" + province + ", city=" + city + ", filter=" + filter + "]";
	}

}
