package objects;

public class Position {
	
	private double latitude;
	private double longitude;
	
	public Position(double lat, double longitude) {
		this.latitude = lat;
		this.longitude = longitude;
	}
	
	public int getMercatorLatitude(int mapWidth, int mapHeight) {
		double latRadian = latitude * Math.PI/180.0;

		return (int) (mapHeight/(Math.PI * 2) * (Math.PI - Math.log(Math.tan(Math.PI/4 + latRadian/2))));
	}
	
	public int getMercatorLongitude(int mapWidth, int mapHeight) {
		double longRadians = longitude * Math.PI/180.0;
		return  (int) (mapWidth/(2*Math.PI) *(longRadians + Math.PI));
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String toString() {
		return "Lat: " + latitude + " Long: " + longitude;
	}
	
	

}
