package objects;

public class OrbitTransfer {

	public static Orbit hohmannTransfer(Orbit orbit1, Orbit orbit2) {
		double ra_1 = orbit1.getSemiMajorAxis() * (1 + orbit1.getEccentricity());
		double rp_1 = orbit1.getSemiMajorAxis() * (1 - orbit1.getEccentricity());
		
		double ra_2 = orbit2.getSemiMajorAxis() * (1 + orbit2.getEccentricity());
		double rp_2 = orbit2.getSemiMajorAxis() * (1 - orbit2.getEccentricity());
		
		double a = (rp_1 + ra_2)/2;
		double e = (ra_2 - rp_1)/(ra_2 + rp_1);
		
		return new Orbit(e, a, orbit2.getInclination(), orbit2.getLongitudeOfAscendingNode(), orbit2.getArgumentOfPeriapsis());
	}
	
	public static double hohmannTransferDeltaV(Orbit orbit1, Orbit orbit2) {
		double ra_1 = orbit1.getSemiMajorAxis() * (1 + orbit1.getEccentricity());
		double rp_1 = orbit1.getSemiMajorAxis() * (1 - orbit1.getEccentricity());
		
		double ra_2 = orbit2.getSemiMajorAxis() * (1 + orbit2.getEccentricity());
		double rp_2 = orbit2.getSemiMajorAxis() * (1 - orbit2.getEccentricity());
 		double deltaV1 = Math.sqrt(AstroConstants.EARTH_GRAVITATIONAL_PARAMETER/ rp_1) * (Math.sqrt(2*rp_2/(rp_1+rp_2)) - 1);
 		double deltaV2 = Math.sqrt(AstroConstants.EARTH_GRAVITATIONAL_PARAMETER/rp_2) * (1 - Math.sqrt(2*rp_1/(rp_1+rp_2)));
 		return deltaV1 + deltaV2;
	}
	
	public static double hohmannTransferTime(Orbit orbit1, Orbit orbit2) {
		double ra_1 = orbit1.getSemiMajorAxis() * (1 + orbit1.getEccentricity());
		double rp_1 = orbit1.getSemiMajorAxis() * (1 - orbit1.getEccentricity());
		
		double ra_2 = orbit2.getSemiMajorAxis() * (1 + orbit2.getEccentricity());
		double rp_2 = orbit2.getSemiMajorAxis() * (1 - orbit2.getEccentricity());
		
		double t = Math.PI * Math.sqrt(Math.pow((rp_1+rp_2), 3)/(8*AstroConstants.EARTH_GRAVITATIONAL_PARAMETER)); //seconds
		return t;
	}
	
	public static double hohmannTransferAngularAlignment(Orbit orbit1, Orbit orbit2) {
		double rp_2 = orbit2.getSemiMajorAxis() * (1 - orbit2.getEccentricity());
		double w_2 = Math.sqrt(AstroConstants.EARTH_GRAVITATIONAL_PARAMETER/Math.pow(rp_2, 3));
		
		return Math.toDegrees(Math.PI - w_2*hohmannTransferTime(orbit1, orbit2));
	}
	
	public static void main(String[] args) {
		Orbit orbit1 = new Orbit(0, 200e3 + AstroConstants.EARTH_RADIUS, 0, 0, 0);
		Orbit orbit2 = new Orbit(0, 350e3 + AstroConstants.EARTH_RADIUS, 0, 0, 0);
		
		System.out.println(hohmannTransferDeltaV(orbit1, orbit2));
		System.out.println(hohmannTransferAngularAlignment(orbit1, orbit2));
		System.out.println(hohmannTransfer(orbit1, orbit2));
	}
	
}
