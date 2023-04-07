package retail;

public class Location implements Comparable<Location> {
	
	private String locationName;
	private int dist;
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public int getDist() {
		return dist;
	}
	public void setDist(int dist) {
		this.dist = dist;
	}
	
	@Override
    public int compareTo(Location o) {
		return this.dist - o.dist;
	}
}
