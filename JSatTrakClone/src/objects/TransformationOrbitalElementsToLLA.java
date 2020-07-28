package objects;

import java.time.LocalDateTime;

public class TransformationOrbitalElementsToLLA {
	
	private static double DEGREES_TO_RADIANS = Math.PI/180;
	
	public static Position orbitalElementsToLLA(Orbit orbit, LocalDateTime time) {
		double P = calcP(orbit, time);
		double Q = calcQ(orbit, time);
		double X = calcX(orbit.getLongitudeOfAscendingNode() * DEGREES_TO_RADIANS,
				orbit.getArgumentOfPeriapsis() * DEGREES_TO_RADIANS,
				orbit.getInclination() * DEGREES_TO_RADIANS,
				P,
				Q
				);
		double Y = calcY(orbit.getLongitudeOfAscendingNode() * DEGREES_TO_RADIANS,
				orbit.getArgumentOfPeriapsis() * DEGREES_TO_RADIANS,
				orbit.getInclination() * DEGREES_TO_RADIANS,
				P,
				Q
				);
		
		double Z = calcZ(orbit.getLongitudeOfAscendingNode() * DEGREES_TO_RADIANS,
				orbit.getArgumentOfPeriapsis() * DEGREES_TO_RADIANS,
				orbit.getInclination() * DEGREES_TO_RADIANS,
				P,
				Q
				);
		
		double lat = calcLatitude(X, Y, Z);
		double lon = calcLongitude(X,Y);
		
		return new Position(lat, lon);
	}
	
	
	private static double calcP(Orbit orbit, LocalDateTime time) {
		double eccentricity = orbit.getEccentricity();
		double a = orbit.getSemiMajorAxis();
		double v = orbit.calculateTrueAnamoly(time); //assumer this is in radians
		
		double p = ((a*(1-Math.pow(eccentricity, 2))) / (1 + eccentricity * Math.cos(v))) * v;
		return  p;
	}
	
	private static double calcQ(Orbit orbit, LocalDateTime time) {
		double eccentricity = orbit.getEccentricity();
		double a = orbit.getSemiMajorAxis();
		double v = orbit.calculateTrueAnamoly(time); //assumer this is in radians
		
		 double q = ((a*(1-Math.pow(eccentricity, 2))) / (1 + eccentricity * Math.sin(v))) * v;
		 return q;
	}
	
	private static double calcX(double LAN, double omega, double i, double P, double Q) {
		double X = P*(Math.cos(-LAN)* Math.cos(-omega) - Math.sin(-LAN)*Math.cos(-i)*Math.sin(-omega)) +
				Q*(-Math.sin(-LAN)*Math.cos(-i)*Math.cos(-omega) - Math.cos(-LAN)*Math.sin(-omega));
		
		return X;
		
	}
	
	private static double calcY(double LAN, double omega, double i, double P, double Q) {
		double Y = P*(Math.sin(-LAN)*Math.cos(-omega) + Math.cos(-LAN)*Math.cos(-i)*Math.sin(-omega)) +
				Q*(Math.cos(-LAN)*Math.cos(-i)*Math.cos(-omega) - Math.sin(-LAN)*Math.sin(-omega));
		
		return Y;
	}
	
	private static double calcZ(double LAN, double omega, double i, double P, double Q) {
		double Z = P*(Math.sin(-i)*Math.sin(-omega)) + Q*(Math.sin(-i)*Math.cos(-omega)); 
		return Z;
	}
	
	private static double calcLatitude(double X, double Y, double Z) {
		double inner = (Z / Math.sqrt(X*X + Y*Y + Z*Z));
		double magnitude = Math.sqrt(X*X + Y*Y + Z*Z);
		double lattitude = Math.PI/2 -   Math.acos(inner);
		
		return lattitude * 180/Math.PI;
	}
	
	private static double calcLongitude(double X, double Y) {
		double longitude = Math.atan(Y/X);
		
		return longitude * 180/Math.PI;
	}

}
