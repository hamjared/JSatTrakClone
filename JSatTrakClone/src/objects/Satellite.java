package objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Satellite {
	
	private Orbit orbit;
	private Orbit transferOrbit;
	private String name;
	private boolean orbitChanged;
	
	public boolean isOrbitChanged() {
		return orbitChanged;
	}

	public void setOrbitChanged(boolean orbitChanged) {
		this.orbitChanged = true;
		this.orbitChanged = orbitChanged;
	}

	public Satellite(String name, Orbit orbit) {
		this.orbit = orbit;
		this.name = name;
		this.orbitChanged = false;
	}
	
	public List<Position> groundTrack(LocalDateTime start, LocalDateTime end){
		List<Position> positions = new ArrayList<Position>();
		int stepSize = 30; //seconds
		LocalDateTime curTime = start;
		
		while(curTime.compareTo(end) < 0) {
			curTime = curTime.plusSeconds(stepSize);
			Position pos = TransformationOrbitalElementsToLLA.orbitalElementsToLLA(orbit, curTime);
			positions.add(pos);
		}
		
		return positions;
	}
	
	public String toString() {
		return name;
	}

	public Orbit getOrbit() {
		return orbit;
	}
	
	public Orbit getOrbit(LocalDateTime time) {
		if (orbit.calculateTrueAnamoly(time) == 180) {
			if(transferOrbit != null) {
				return transferOrbit;
			}
		}
		
		return orbit;
	}

	public void setOrbit(Orbit orbit) {
		this.orbit = orbit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}