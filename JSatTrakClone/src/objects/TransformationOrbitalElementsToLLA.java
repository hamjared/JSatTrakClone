package objects;

import java.time.Duration;
import java.time.LocalDateTime;

public class TransformationOrbitalElementsToLLA {
	
	private static final double DEGREES_TO_RADIANS = Math.PI/180;
	
	public static Position orbitalElementsToLLA(Orbit orbit, LocalDateTime time) {
		double P = calcP(orbit, orbit.calculateTrueAnamoly(time));
		double Q = calcQ(orbit, orbit.calculateTrueAnamoly(time));
		double X_ECI = calcXECI(orbit.getLongitudeOfAscendingNode() * DEGREES_TO_RADIANS,
				orbit.getArgumentOfPeriapsis() * DEGREES_TO_RADIANS,
				orbit.getInclination() * DEGREES_TO_RADIANS,
				P,
				Q
				);
		double Y_ECI = calcYECI(orbit.getLongitudeOfAscendingNode() * DEGREES_TO_RADIANS,
				orbit.getArgumentOfPeriapsis() * DEGREES_TO_RADIANS,
				orbit.getInclination() * DEGREES_TO_RADIANS,
				P,
				Q
				);
		
		double Z_ECI = calcZECI(orbit.getLongitudeOfAscendingNode() * DEGREES_TO_RADIANS,
				orbit.getArgumentOfPeriapsis() * DEGREES_TO_RADIANS,
				orbit.getInclination() * DEGREES_TO_RADIANS,
				P,
				Q
				);
		
		double theta = calculateGreenichSiderealAngle(time);
		double X = calcXECEF(X_ECI, Y_ECI, Z_ECI, theta);
		double Y = calcYECEF(X_ECI, Y_ECI, Z_ECI, theta);
		double Z = calcZECEF(X_ECI, Y_ECI, Z_ECI, theta);
		
		
		double lat = calcLatitude(X, Y, Z);
		double lon = calcLongitude(X,Y);
//		System.out.println("X: " + X + " Y: " + Y + "Lat: " + lat + "Long: " + lon);
		return new Position(lat, lon);
	}
	
	
	private static double calcP(Orbit orbit, double trueAnamoly) {
		double eccentricity = orbit.getEccentricity();
		double a = orbit.getSemiMajorAxis();
		double v = trueAnamoly; //assumer this is in radians
		
		double p = ((a*(1-(eccentricity * eccentricity))) / (1 + eccentricity * Math.cos(v))) * Math.cos(v);
		return  p;
	}
	
	private static double calcQ(Orbit orbit, double trueAnamoly) {
		double eccentricity = orbit.getEccentricity();
		double a = orbit.getSemiMajorAxis();
		double v = trueAnamoly; //assumer this is in radians
		
		double q = ((a*(1-Math.pow(eccentricity, 2))) / (1 + eccentricity * Math.cos(v))) * Math.sin(v);
		return q;
	}
	
	private static double calcXECI(double Omega, double w, double i, double P, double Q) {
		double X = P*(Math.cos(Omega)* Math.cos(w) - Math.sin(Omega)*Math.cos(i)*Math.sin(w)) -
				Q*(Math.cos(Omega)*Math.sin(w) + Math.sin(Omega)*Math.cos(i)*Math.cos(w)  );
		
		return X;
		
	}
	
	private static double calcYECI(double Omega, double w, double i, double P, double Q) {
		double Y = P*(Math.sin(Omega)*Math.cos(w) + Math.cos(Omega)*Math.cos(i)*Math.sin(w)) -
				Q*(Math.sin(Omega)*Math.sin(w) - Math.cos(Omega)*Math.cos(i)*Math.cos(w)  );
		
		return Y;
	}
	
	private static double calcZECI(double Omega, double w, double i, double P, double Q) {
		double Z = P*(Math.sin(i)*Math.sin(w)) + Q*(Math.sin(i)*Math.cos(w)); 
		return Z;
	}
	
	private static double calcXECEF(double X_ECI, double Y_ECI, double Z_ECI, double theta) {
		return X_ECI * Math.cos(theta) + Y_ECI * Math.sin(theta);
	}
	
	private static double calcYECEF(double X_ECI, double Y_ECI, double Z_ECI, double theta) {
		return Y_ECI * Math.cos(theta) - X_ECI * Math.sin(theta);
	}
	
	private static double calcZECEF(double X_ECI, double Y_ECI, double Z_ECI, double theta) {
		return Z_ECI;
	}
	
	private static double calcLatitude(double X, double Y, double Z) {
		double inner = (Z / Math.sqrt(X*X + Y*Y + Z*Z));
		double lattitude = Math.PI/2 -   Math.acos(inner);
		
		return lattitude * 180/Math.PI;
	}
	
	private static double calculateGreenichSiderealAngle(LocalDateTime time) {
		//http://www2.arnes.si/~gljsentvid10/sidereal.htm
		final LocalDateTime J2000 = LocalDateTime.of(2000, 1, 1, 0, 0);
		
		final double DEGREES_PER_MEAN_DAY = 360.98564736629;
		final double VERNAL_EQUINOX_LONGITUDE = 280.46061837;
		final double NANOS_TO_DAYS = 8.64e13;
		
		Duration TimeDifference = Duration.between(J2000, time);
		double difference = TimeDifference.toNanos();
		double daysSinceJ2000 = difference / NANOS_TO_DAYS;
		
		double totalDegrees = VERNAL_EQUINOX_LONGITUDE + DEGREES_PER_MEAN_DAY * daysSinceJ2000;
		
		double multipleOf360 = Math.abs(totalDegrees/360) ;
		

		return Math.toRadians(360 * (multipleOf360 - Math.floor(multipleOf360)));
		
		
	}
	
	private static double calcLongitude(double X, double Y) {
		double longitude = Math.atan(Y/X);
		
		if(Y > 0 && X < 0) {
			longitude = longitude + Math.PI;
		}
		
		if(Y < 0 && X < 0) {
			longitude = longitude - Math.PI;
		}

		if(Y > 0 && X == 0) {
			longitude = Math.PI/2;
		}
		
		if(Y < 0 && X == 0) {
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
		double X = calcXECI(orbit.getLongitudeOfAscendingNodeRadians() ,
				orbit.getArgumentOfPeriapsisRadians(),
				orbit.getInclinationRadians(),
				P,
				Q
				);
		double Y = calcYECI(orbit.getLongitudeOfAscendingNodeRadians() ,
				orbit.getArgumentOfPeriapsisRadians(),
				orbit.getInclinationRadians(),
				P,
				Q
				);
		
		double Z = calcZECI(orbit.getLongitudeOfAscendingNodeRadians() ,
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
		
		
		double sideRealTime = calculateGreenichSiderealAngle(LocalDateTime.of(1994, 6, 16, 18, 0));
		
		
		System.out.println(sideRealTime);
		
	}

}
