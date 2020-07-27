package objects;

import java.time.LocalDateTime;

public class Orbit {
	private double eccentricity;
	private double semiMajorAxis;
	private double inclination;
	private double longitudeOfAscendingNode;
	private double argumentOfPeriapsis;
	
	public Orbit(double e, double a, double i, double longitudeOfAscendingNode, double argumentOfPeriapsis) {
		this.eccentricity = e;
		this.semiMajorAxis = a;
		this.inclination = i ;
		this.longitudeOfAscendingNode = longitudeOfAscendingNode;
		this.argumentOfPeriapsis = argumentOfPeriapsis;
	}
	
	
	public double calcApogee() {
		return semiMajorAxis * (1 + this.eccentricity);
	}
	
	public double calcPerigee() {
		return semiMajorAxis * (1-this.eccentricity);
	}
	
	public double calculateTrueAnamoly( LocalDateTime time) {
		return 0;
	}

	public double getEccentricity() {
		return eccentricity;
	}

	public void setEccentricity(double eccentricity) {
		this.eccentricity = eccentricity;
	}

	public double getSemiMajorAxis() {
		return semiMajorAxis;
	}

	public void setSemiMajorAxis(double semiMajorAxis) {
		this.semiMajorAxis = semiMajorAxis;
	}

	public double getInclination() {
		return inclination;
	}

	public void setInclination(double inclination) {
		this.inclination = inclination;
	}

	public double getLongitudeOfAscendingNode() {
		return longitudeOfAscendingNode;
	}

	public void setLongitudeOfAscendingNode(double longitudeOfAscendingNode) {
		this.longitudeOfAscendingNode = longitudeOfAscendingNode;
	}

	public double getArgumentOfPeriapsis() {
		return argumentOfPeriapsis;
	}

	public void setArgumentOfPeriapsis(double argumentOfPeriapsis) {
		this.argumentOfPeriapsis = argumentOfPeriapsis;
	}
}
