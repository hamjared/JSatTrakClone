package objects;

public class GroundStation {
	
	private String name;
	private Position position;
	private double elevation;
	private double azimuth;
	
	public GroundStation(String name, Position pos) {
		this.name = name;
		position = pos;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	public double getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(double azimuth) {
		this.azimuth = azimuth;
	}
	
	public String toString() {
		return this.name;
	}

}
