package objects;

import java.time.LocalDateTime;

public class TransformationOrbitalElementsToLLA {
	
	private static double DEGREES_TO_RADIANS = Math.PI/180;
	
	public static Position orbitalElementsToLLA(Orbit orbit, LocalDateTime time) {
		double P = calcP(orbit, orbit.calculateTrueAnamoly(time));
		double Q = calcQ(orbit, orbit.calculateTrueAnamoly(time));
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
//		System.out.println("X: " + X + " Y: " + Y + "Lat: " + lat + "Long: " + lon);
		return new Position(lat, lon);
	}
	
	
	private static double calcP(Orbit orbit, double trueAnamoly) {
		double eccentricity = orbit.getEccentricity();
		double a = orbit.getSemiMajorAxis();
		double v = trueAnamoly; //assumer this is in radians
		
		double p = ((a*(1-Math.pow(eccentricity, 2))) / (1 + eccentricity * Math.cos(v))) * Math.cos(v);
		return  p;
	}
	
	private static double calcQ(Orbit orbit, double trueAnamoly) {
		double eccentricity = orbit.getEccentricity();
		double a = orbit.getSemiMajorAxis();
		double v = trueAnamoly; //assumer this is in radians
		
		 double q = ((a*(1-Math.pow(eccentricity, 2))) / (1 + eccentricity * Math.cos(v))) * Math.sin(v);
		 return q;
	}
	
	private static double calcX(double Omega, double w, double i, double P, double Q) {
		double X = P*(Math.cos(Omega)* Math.cos(w) - Math.sin(Omega)*Math.cos(i)*Math.sin(w)) -
				Q*(Math.cos(Omega)*Math.sin(w) + Math.sin(Omega)*Math.cos(i)*Math.cos(w)  );
		
		return X;
		
	}
	
	private static double calcY(double Omega, double w, double i, double P, double Q) {
		double Y = P*(Math.sin(Omega)*Math.cos(w) + Math.cos(Omega)*Math.cos(i)*Math.sin(w)) -
				Q*(Math.sin(Omega)*Math.sin(w) - Math.cos(Omega)*Math.cos(i)*Math.cos(w)  );
		
		return Y;
	}
	
	private static double calcZ(double Omega, double w, double i, double P, double Q) {
		double Z = P*(Math.sin(i)*Math.sin(w)) + Q*(Math.sin(i)*Math.cos(w)); 
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
		
		if( Y > 0 && X < 0) {
			longitude = longitude + Math.PI;
			
		}
		
		if(Y < 0 && X < 0) {
			longitude = longitude - Math.PI;
		}

		if(Y > 0 && X == 0) {
			longitude = Math.PI/2;
		}
		
		if(Y<0 && X==0) {
			longitude = -Math.PI/2;
		}
		return longitude * 180/Math.PI;
	}
	
	
	public static void main(String[] args) {
		double i =  30;
		double Omega = 40;
		double w = 60;
		double theta = Math.toRadians(30);
		double a = -16.725186e6; //m
		double e = 1.4;
		
		Orbit orbit = new Orbit(e, a, i, Omega, w );
		
		double P = calcP(orbit, theta);
		double Q = calcQ(orbit, theta);
		double X = calcX(orbit.getLongitudeOfAscendingNodeRadians() ,
				orbit.getArgumentOfPeriapsisRadians(),
				orbit.getInclinationRadians(),
				P,
				Q
				);
		double Y = calcY(orbit.getLongitudeOfAscendingNodeRadians() ,
				orbit.getArgumentOfPeriapsisRadians(),
				orbit.getInclinationRadians(),
				P,
				Q
				);
		
		double Z = calcZ(orbit.getLongitudeOfAscendingNodeRadians() ,
				orbit.getArgumentOfPeriapsisRadians(),
				orbit.getInclinationRadians(),
				P,
				Q
				);
		
		System.out.println("P: " + P);
		System.out.println("Q: " + Q);
		
		System.out.println("X: " + X + "\n"
				+ "Y: " + Y +"\n"
				+ "Z: " + Z);
		
	}

}
