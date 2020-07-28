package objects;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

public class Orbit {
	private double eccentricity;
	private double semiMajorAxis;
	private double inclination;
	private double longitudeOfAscendingNode;
	private double argumentOfPeriapsis;
	private static final LocalDateTime epochTime = LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0);
	
	public Orbit(double e, double a, double i, double longitudeOfAscendingNode, double argumentOfPeriapsis) {
		this.eccentricity = e;
		this.semiMajorAxis = a;
		this.inclination = i;
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
		
		Duration TimeDifference = Duration.between(epochTime, time);
		double difference = TimeDifference.toSeconds();
		
		double orbitalPeriod = Math.PI*2 * Math.sqrt(semiMajorAxis * semiMajorAxis * semiMajorAxis/AstroConstants.EARTH_GRAVITATIONAL_PARAMETER); // seconds

		double numOrbits = difference/orbitalPeriod;
		
		double timeSincePeriapsis = difference - ((int) numOrbits) * orbitalPeriod;

		
		double meanAnamoly = Math.PI * 2 * timeSincePeriapsis/orbitalPeriod;

		
		double EccentricAnamoly = NewtonsSolver.solve(this.eccentricity, meanAnamoly);

		
		double trueAnamoly = 2 * Math.atan(Math.sqrt((1+this.eccentricity)/(1-this.eccentricity))*Math.tan(EccentricAnamoly/2));
		
		if (trueAnamoly < 0) {
			return 2*Math.PI + trueAnamoly;
			
		}
		else {
			return trueAnamoly;
		}
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
	
	public static void main(String[] args) {
		Orbit orbit = new Orbit(0.37255, 15.3e6, 0,0,0);
		
		System.out.println(orbit.calculateTrueAnamoly(LocalDateTime.of(2020, Month.JANUARY, 1, 3, 0)));
	}
}